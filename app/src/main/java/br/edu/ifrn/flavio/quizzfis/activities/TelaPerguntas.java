package br.edu.ifrn.flavio.quizzfis.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import br.edu.ifrn.flavio.quizzfis.R;
import br.edu.ifrn.flavio.quizzfis.helpers.BancoDadosPerguntas;
import br.edu.ifrn.flavio.quizzfis.helpers.Questao;

public class TelaPerguntas extends Activity
{
    private MediaPlayer mediaPlayer = null;
    List<Questao> arrayListQuestao;
    private int idQuestao;
    private RelativeLayout rlPrincipal;
    private int pontuacao;
    private Questao questaoAtual;
    private Button btPular;
    private RadioGroup rgQuiz;
    private RadioButton rbAssertivaA;
    private RadioButton rbAssertivaB;
    private RadioButton rbAssertivaC;
    private RadioButton rbAssertivaD;
    BancoDadosPerguntas bancoDadosPerguntas = new BancoDadosPerguntas(this);
    private Intent intentTelaPerguntas;
    private Bundle bundle;
    private TextView tvPontuacao;
    private TextView tvPergunta;
    private TextView tvContagemRegressiva;
    private TextView tvPlayer;
    private CountDownTimer contador;
    private int vezJogador = 1;
    private int pontuacaoPlayer1 = 0;
    private int pontuacaoPlayer2 = 0;
    private RadioButton answer;
    private int quantidadeRodada = 1;
    private int quantidadePulos = 3;
    private String jogador1;
    private String jogador2;
    ScrollView scrollView;
    private TextView tvRodada;
    private int chaveRecebidaMyActivity = 0;
    private int quantidadePerguntasCadastradas = 0;
    private LinearLayout linearLayout;
    private int quantidadeJogadasSelecionadas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_perguntas);

        bundle = getIntent().getExtras();
        chaveRecebidaMyActivity = bundle.getInt("chave");

        configuraRelativeLayout();
        configuraScrollView();
        configuraLinearLayout();
        configuraTextView();
        configuraButton();
        configuraRadioButton();
        verificaAssertiva();
        modoDeJogo(bundle.getInt("chave"));
        pularQuestao();
        iniciarQuis();
    }

    private void configuraRelativeLayout()
    {
        rlPrincipal = (RelativeLayout) findViewById(R.id.rl_principal);
    }

    private void configuraLinearLayout()
    {
        linearLayout = (LinearLayout) findViewById(R.id.ll_amostragem);
    }

    private void configuraScrollView()
    {
        scrollView = (ScrollView) findViewById(R.id.sv_quis);
        //
    }

    private void iniciarQuis()
    {
        rlPrincipal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Resources res = getResources(); //resource handle
                Drawable drawable = res.getDrawable(R.drawable.bg_tela_perguntas);
                rlPrincipal.setBackground(drawable);
                mostrarComponentes();
            }
        });
    }

    private void mostrarComponentes()
    {
        scrollView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        tvPergunta.setVisibility(View.VISIBLE);

        quantidadePerguntasCadastradas = bancoDadosPerguntas.quantidadePerguntas();
        arrayListQuestao = bancoDadosPerguntas.pegarTodasQuestoes();
        questaoAtual = arrayListQuestao.get(0);
        setarNovaQuestao();

        rlPrincipal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void esconderLayout()
    {
        scrollView.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        tvPergunta.setVisibility(View.GONE);
        //
    }

    private void configuraTextView()
    {
        tvContagemRegressiva = (TextView) findViewById(R.id.tv_contagem_regressiva);
        tvPergunta = (TextView) findViewById(R.id.tela_quiz_tv_pergunta);
        tvPlayer = (TextView) findViewById(R.id.tv_player);
        tvPontuacao = (TextView) findViewById(R.id.tv_pontuacao);
        tvPontuacao.setText("Pontuação: " + pontuacao);
        tvRodada = (TextView) findViewById(R.id.tv_rodada);
        tvRodada.setText("Jogada " + quantidadeRodada + "\t\t");

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/caviar_negrito.ttf");
        tvPergunta.setTypeface(font);
    }

    private void configuraButton()
    {
        btPular = (Button) findViewById(R.id.bt_pular);
        btPular.setText("Pular 3x");
        btPular.setTextColor(getResources().getColor(R.color.branco));
    }

    private void verificaAssertiva()
    {
        rgQuiz.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (rbAssertivaA.isChecked() || rbAssertivaB.isChecked() || rbAssertivaC.isChecked() || rbAssertivaD.isChecked())
                {
                    bundle = new Bundle();
                    bundle.putInt("chave", chaveRecebidaMyActivity);
                    intentTelaPerguntas = new Intent(getApplicationContext(), TelaPerguntasConfirmaResposta.class);
                    intentTelaPerguntas.putExtras(bundle);
                    startActivityForResult(intentTelaPerguntas, 0);
                }
            }
        });
    }

    private void configuraRadioButton()
    {
        rgQuiz = (RadioGroup)findViewById(R.id.rg_quiz);
        rbAssertivaA = (RadioButton) findViewById(R.id.rb_01);
        rbAssertivaB = (RadioButton) findViewById(R.id.rb_02);
        rbAssertivaC = (RadioButton) findViewById(R.id.rb_03);
        rbAssertivaD = (RadioButton) findViewById(R.id.rb_04);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/caviar_negrito.ttf");
        rbAssertivaA.setTypeface(font);
        rbAssertivaB.setTypeface(font);
        rbAssertivaC.setTypeface(font);
        rbAssertivaD.setTypeface(font);

        rbAssertivaA.setTextColor(getResources().getColor(R.color.preto));
        //rbAssertivaA.setShadowLayer(3, 0, 0, getResources().getColor(R.color.DimGray));

        rbAssertivaB.setTextColor(getResources().getColor(R.color.preto));
        //rbAssertivaB.setShadowLayer(3, 0, 0, getResources().getColor(R.color.DimGray));

        rbAssertivaC.setTextColor(getResources().getColor(R.color.preto));
        //rbAssertivaC.setShadowLayer(3, 0, 0, getResources().getColor(R.color.DimGray));

        rbAssertivaD.setTextColor(getResources().getColor(R.color.preto));
        //rbAssertivaD.setShadowLayer(3, 0, 0, getResources().getColor(R.color.DimGray));
    }

    private void contadorRegressivo()
    {
        contador =  new CountDownTimer(60000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                tvContagemRegressiva.setText("TEMPO RESTANTE: " + millisUntilFinished / 1000);
            }
            public void onFinish()
            {
                cadastrarRecord();
            }
        };
        contador.start();
    }

    private void setarNovaQuestao()
    {
        tvPergunta.setText(Html.fromHtml(questaoAtual.getQuestao()));
        rbAssertivaA.setText(Html.fromHtml(questaoAtual.getQuestaoAssertivaA()));
        rbAssertivaB.setText(Html.fromHtml(questaoAtual.getQuestaoAssertivaB()));
        rbAssertivaC.setText(Html.fromHtml(questaoAtual.getQuestaoAssertivaC()));
        rbAssertivaD.setText(Html.fromHtml(questaoAtual.getQuestaoAssertivaD()));
    }

    private void pularQuestao()
    {
        btPular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if((quantidadePulos > 0) && (idQuestao < bancoDadosPerguntas.quantidadePerguntas()))
                {
                    arrayListQuestao.remove(0);
                    Collections.shuffle(arrayListQuestao);
                    questaoAtual = arrayListQuestao.get(0);
                    setarNovaQuestao();
                    quantidadePulos--;
                    if(quantidadePulos == 0)
                    {
                        btPular.setEnabled(false);
                        btPular.setText("Pular " + quantidadePulos + "x");
                        // btPular.setTextColor(getResources().getColor(R.color.amarelo_ouro));
                    }
                    btPular.setText("Pular " + quantidadePulos + "x");
                }
            }
        });
    }

    private void modoDeJogo(int modoDeJogo)
    {
        if(modoDeJogo == 0)
        {
            toast("toque na tela para iniciar");
            arrayListQuestao = bancoDadosPerguntas.pegarTodasQuestoes();
            questaoAtual = arrayListQuestao.get(0);
            setarNovaQuestao();

            tvPlayer.setVisibility(View.GONE);
            btPular.setVisibility(View.VISIBLE);
            tvContagemRegressiva.setVisibility(View.GONE);
            tvPontuacao.setVisibility(View.VISIBLE);
            tvRodada.setVisibility(View.GONE);
        }
        else if(modoDeJogo == 1)
        {
            toast("toque na tela para iniciar");
            arrayListQuestao = bancoDadosPerguntas.pegarTodasQuestoes();
            questaoAtual = arrayListQuestao.get(0);
            setarNovaQuestao();

            tvPlayer.setVisibility(View.GONE);
            btPular.setVisibility(View.GONE);
            contadorRegressivo();
            tvContagemRegressiva.setVisibility(View.VISIBLE);
            tvPontuacao.setVisibility(View.VISIBLE);
            tvRodada.setVisibility(View.GONE);
        }
        else if(modoDeJogo == 2)
        {
            btPular.setVisibility(View.GONE);
            tvContagemRegressiva.setVisibility(View.GONE);
            tvPontuacao.setVisibility(View.GONE);
            tvPlayer.setVisibility(View.VISIBLE);
            nomesJogadoresMultiplayer();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        rgQuiz = (RadioGroup)findViewById(R.id.rg_quiz);
        if (resultCode == RESULT_CANCELED)
        {
            rgQuiz.clearCheck();
        }
        else if(resultCode == RESULT_OK)
        {
            try
            {
                bundle = intent.getExtras();
                answer = (RadioButton) findViewById(rgQuiz.getCheckedRadioButtonId());

                if(bundle.getInt("chave") == 99)
                {
                    modoMultiplayer();
                }
                else if(bundle.getInt("chave") == 0)
                {
                    String resposta = answer.getText().toString();
                    String verificadorQuestao = questaoAtual.getQuestaoResposta();

                    if (!resposta.equals(verificadorQuestao))
                    {
                        if (contador != null)
                        {
                            contador.cancel();
                        }
                        toast("você errou");
                        esconderLayout();
                        cadastrarRecord();
                    }
                    if (resposta.equals(verificadorQuestao))
                    {
                        pontuacao++;
                        tvPontuacao.setText("Pontuação: " + pontuacao);
                        if (contador != null)
                        {
                            contador.cancel();
                            contadorRegressivo();
                        }
                        toast("você acertou");
                        //tocarSon();
                    }
                    if (idQuestao < bancoDadosPerguntas.quantidadePerguntas())
                    {
                        arrayListQuestao.remove(0);
                        Collections.shuffle(arrayListQuestao);
                        questaoAtual = arrayListQuestao.get(0);
                        setarNovaQuestao();
                    }
                    else if (idQuestao == bancoDadosPerguntas.quantidadePerguntas())
                    {
                        esconderLayout();
                        cadastrarRecord();
                    }
                }
                else if (bundle.getInt("chave") == 1)
                {
                    finish();
                }
                else if (bundle.getInt("chave") == 33)
                {
                    quantidadeJogadasSelecionadas = bundle.getInt("jogadasSelecionadas");
                    jogador2 =  bundle.getString("jogador2");
                    jogador1 =  bundle.getString("jogador1");
                    tvPlayer.setText("Jogador - " + jogador1);

                    quantidadePerguntasCadastradas = bancoDadosPerguntas.quantidadePerguntas();
                    arrayListQuestao = bancoDadosPerguntas.pegarTodasQuestoes();
                    questaoAtual = arrayListQuestao.get(0);
                    setarNovaQuestao();
                }
                else
                {
                    mensagemAlerta("onActivityResult", "RESULT_OK");
                }
            }
            catch (Exception e)
            {
                toast("(onActivityResult) " + e.toString());
            }
            finally
            {
                rgQuiz.clearCheck();
            }
        }
    }

    private void modoMultiplayer()
    {
        String resposta = answer.getText().toString();
        String verificadorQuestao = questaoAtual.getQuestaoResposta();

        if(resposta.equals(verificadorQuestao))
        {
            if((vezJogador % 2) == 0)
            {
                pontuacaoPlayer2++;
                quantidadeRodada++;
                vezJogador++;
                tvRodada.setText("Jogada " + quantidadeRodada + "\t\t");
            }
            else
            {
                pontuacaoPlayer1++;
                vezJogador++;
                tvRodada.setText("Jogada " + quantidadeRodada + "\t\t");
            }
            toast("você acertou");
        }
        else if (!resposta.equals(verificadorQuestao))
        {
            if((vezJogador % 2) == 0)
            {
                quantidadeRodada++;
            }
            vezJogador++;
            tvRodada.setText("Jogada " + quantidadeRodada + "\t\t");
            toast("você errou");
        }
        if(quantidadeRodada == quantidadeJogadasSelecionadas + 1)
        {
            tvRodada.setText("");
            tvPlayer.setText("");
            bundle = new Bundle();
            bundle.putInt("pontuacao1", pontuacaoPlayer1);
            bundle.putInt("pontuacao2", pontuacaoPlayer2);
            intentTelaPerguntas = new Intent(getApplicationContext(), TelaMultiplayerResultado.class);
            intentTelaPerguntas.putExtras(bundle);
            esconderLayout();
            startActivityForResult(intentTelaPerguntas, 1);
        }
        if(idQuestao < bancoDadosPerguntas.quantidadePerguntas())
        {
            arrayListQuestao.remove(0);
            Collections.shuffle(arrayListQuestao);
            questaoAtual = arrayListQuestao.get(0);
            setarNovaQuestao();
        }
        else if(idQuestao == bancoDadosPerguntas.quantidadePerguntas())
        {
            arrayListQuestao.remove(0);
            bundle = new Bundle();
            bundle.putInt("pontuacao1", pontuacaoPlayer1);
            bundle.putInt("pontuacao2", pontuacaoPlayer2);
            intentTelaPerguntas = new Intent(getApplicationContext(), TelaMultiplayerResultado.class);
            intentTelaPerguntas.putExtras(bundle);
            esconderLayout();
            startActivityForResult(intentTelaPerguntas, 1);
        }

        if((vezJogador % 2) == 0)
        {
            tvPlayer.setText("Jogador - " + jogador2);
        }
        else
        {
            tvPlayer.setText("Jogador - " + jogador1);
        }

        if(vezJogador > 2)
        {
            vezJogador = 1;
        }
    }

    private void cadastrarRecord()
    {
        bundle = new Bundle();
        bundle.putInt("pontuacao", pontuacao);
        intentTelaPerguntas = new Intent(getApplicationContext(), TelaCadastroRecord.class);
        intentTelaPerguntas.putExtras(bundle);
        startActivityForResult(intentTelaPerguntas, 1);
    }

    public void tocarSon()
    {
        mediaPlayer = MediaPlayer.create(this, R.raw.acertou);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            public void onCompletion(MediaPlayer mp)
            {
                mp.stop();
                mp.release();
                mp = null;
            }
        });
        mediaPlayer.start();
    }

    public void mensagemAlerta(String titulo, String mensagem)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(TelaPerguntas.this);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setIcon(R.drawable.ic_icone_exclamacao);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();
    }

    @Override
    public void onBackPressed()
    {
        if(contador!=null)
        {
            contador.cancel();
        }
        mensagemAlerta("Abandonar", "Deseja abandonar a partida?");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //
    }

    protected void nomesJogadoresMultiplayer()
    {
        intentTelaPerguntas = new Intent(getApplicationContext(), TelaNomesJogadoresMultiplayer.class);
        startActivityForResult(intentTelaPerguntas, 76);
    }

    private void toast(String mensagem)
    {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
    }
}