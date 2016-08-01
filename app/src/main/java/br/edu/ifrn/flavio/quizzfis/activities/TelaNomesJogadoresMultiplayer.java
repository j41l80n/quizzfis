package br.edu.ifrn.flavio.quizzfis.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifrn.flavio.quizzfis.R;

public class TelaNomesJogadoresMultiplayer extends Activity
{
    private EditText etEstudant1;
    private EditText etEstudant2;
    private Button btIniciarMultiplayer;
    private Intent intent;
    private Bundle bundle;
    private int quantidadePartidas = 0;
    private RadioGroup rgQuantidadePartidas;
    private int jogadasSelecionadas = 3;
    private RadioButton rbJogadasSelecionadas3;
    private RadioButton rbJogadasSelecionadas5;
    private RadioButton rbJogadasSelecionadas7;
    private TextView tvQuantidadeJogadas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_nomes_jogadores_multiplayer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        configuraEditText();
        configuraButton();
        configuraRadioGroup();
        configuraRadioButton();
        verificajogadasSelecionadas();
        configuraTextView();
        iniciarJogo();
        t01();
        t02();
        configuraJanela();
    }

    private void configuraTextView()
    {
        tvQuantidadeJogadas = (TextView) findViewById(R.id.tv_quantidade_jogadas);
        tvQuantidadeJogadas.setTextColor(getResources().getColor(R.color.branco));
        tvQuantidadeJogadas.setShadowLayer(0.02f, 0, 0, getResources().getColor(R.color.amarelo_ouro));
    }

    private void configuraButton()
    {
        btIniciarMultiplayer = (Button) findViewById(R.id.bt_iniciar_multiplayer);
    }

    private void configuraRadioGroup()
    {
        rgQuantidadePartidas = (RadioGroup) findViewById(R.id.rg_quantidade_jogadas);
    }

    private void verificajogadasSelecionadas()
    {
        rgQuantidadePartidas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (rbJogadasSelecionadas3.isChecked())
                {
                    jogadasSelecionadas = 3;
                }
                else if (rbJogadasSelecionadas5.isChecked())
                {
                    jogadasSelecionadas = 5;
                }
                else if (rbJogadasSelecionadas7.isChecked())
                {
                    jogadasSelecionadas = 7;
                }
            }
        });
    }

    private void configuraRadioButton()
    {
        rbJogadasSelecionadas3 = (RadioButton) findViewById(R.id.rb_quantidades_partidas_03);
        rbJogadasSelecionadas5 = (RadioButton) findViewById(R.id.rb_quantidades_partidas_05);
        rbJogadasSelecionadas7 = (RadioButton) findViewById(R.id.rb_quantidades_partidas_07);
    }

    private void configuraEditText()
    {
        etEstudant1 = (EditText) findViewById(R.id.et_estudant1);
        etEstudant2 = (EditText) findViewById(R.id.et_estudant2);
    }

    private void iniciarJogo()
    {
        btIniciarMultiplayer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String verificaEstudante1 = etEstudant1.getText().toString();
                String verificaEstudante2 = etEstudant2.getText().toString();

            if(verificaEstudante1.trim().isEmpty())
            {
                etEstudant1.setError("Campo vazio");
                etEstudant1.requestFocus();
            }
            else if (verificaEstudante2.trim().isEmpty())
            {
                etEstudant2.setError("Campo vazio");
                etEstudant1.setError(null);
                etEstudant2.requestFocus();
            }
            else
            {
                try
                {
                    bundle = new Bundle();
                    bundle.putString("jogador1", etEstudant1.getText().toString());
                    bundle.putString("jogador2", etEstudant2.getText().toString());
                    bundle.putInt("chave", 33);
                    bundle.putInt("jogadasSelecionadas", jogadasSelecionadas);
                    intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    toast("toque na tela para iniciar");
                    finish();
                }
                catch (Exception e)
                {
                    toast("(iniciarJogo) " + e.toString());
                }
            }
           }
       });
    }

    private void t01()
    {
        etEstudant1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etEstudant1.getText().toString().equals("")) {
                    etEstudant1.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void t02()
    {
        etEstudant2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etEstudant2.getText().toString().equals("")) {
                    etEstudant2.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void configuraJanela()
    {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        this.getWindow().setAttributes(params);
    }

    private void toast(String mensagem)
    {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed()
    {

    }
}
