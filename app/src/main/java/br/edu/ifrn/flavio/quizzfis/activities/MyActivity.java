package br.edu.ifrn.flavio.quizzfis.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import br.edu.ifrn.flavio.quizzfis.R;
import br.edu.ifrn.flavio.quizzfis.helpers.BancoDadosPerguntas;
import br.edu.ifrn.flavio.quizzfis.helpers.Questao;

public class MyActivity extends Activity
{
    private Bundle bundleMyActivity;
    private Intent intentMyActivity;
    private SQLiteDatabase bancoDados;
    private static final String DATABASE_NAME = "banco_quizzfis";
    private static final String TB_PERGUNTAS = "tb_perguntas";
    private static final String TB_RECORDES = "tb_recordes";
    int opcaoSelecionada;
    private Button btJogar;
    private Button btOpcoes;
    private List<Questao> listaQuestoes;
    BancoDadosPerguntas bancoDadosPerguntas = new BancoDadosPerguntas(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        listaQuestoes = bancoDadosPerguntas.pegarTodasQuestoes();

        configuraButtons();
        criarBanco();
        chamaTelaPerguntas();
        chamaTelaOpcoes();
    }

    private void configuraButtons()
    {
        btJogar = (Button) findViewById(R.id.bt_jogar);
        btOpcoes = (Button) findViewById(R.id.bt_opcoes);
    }

    private void chamaTelaPerguntas()
    {
        btJogar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                String SQL = "SELECT * FROM " + TB_PERGUNTAS + ";";
                Cursor cursor = bancoDados.rawQuery(SQL, null);
                try
                {
                    if (cursor.getCount() > 5)
                    {
                        bundleMyActivity = new Bundle();
                        bundleMyActivity.putInt("chave", opcaoSelecionada);
                        intentMyActivity = new Intent(getApplicationContext(), TelaPerguntas.class);
                        intentMyActivity.putExtras(bundleMyActivity);
                        startActivity(intentMyActivity);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Quantidade de perguntas insuficiente\nCadastre novas peruntas", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                finally
                {
                    bancoDados.close();
                    cursor.close();
                }
            }
        });
    }

    private void chamaTelaOpcoes()
    {
        btOpcoes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                intentMyActivity = new Intent(getApplicationContext(), TelaOpcoes.class);
                startActivityForResult(intentMyActivity, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode == RESULT_CANCELED)
        {

        }
        else if (resultCode == RESULT_OK)
        {
            bundleMyActivity = intent.getExtras();
            int chave = bundleMyActivity.getInt("chave");

            if (chave == 0)
            {
                opcaoSelecionada = 0;
            }
            else if (chave == 1)
            {
                opcaoSelecionada = 1;
            }
            else if (chave == 2)
            {
                //LinearLayout ll = (LinearLayout) findViewById(R.id.ll_activity_my);
                //ll.setBackgroundColor(getResources().getColor(R.color.laranja));
                opcaoSelecionada = 2;
            }
        }
    }

    public void criarBanco()
    {
        try
        {
            bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            String sql = "CREATE TABLE IF NOT EXISTS " + TB_RECORDES + "(codigo_recorde INTEGER PRIMARY KEY AUTOINCREMENT, nome_jogador VARCHAR(10), data_jogo VARCHAR(100), pontuacao INTEGER, maior_nivel INTEGER);";
            bancoDados.execSQL(sql);
        }
        catch (Exception erro)
        {
        }
        finally
        {
            bancoDados.close();
        }
    }

    public void tocarSon(View view)
    {
        MediaPlayer mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.clicou);
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
}