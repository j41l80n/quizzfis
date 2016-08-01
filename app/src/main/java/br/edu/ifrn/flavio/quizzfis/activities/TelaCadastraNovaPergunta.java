package br.edu.ifrn.flavio.quizzfis.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifrn.flavio.quizzfis.R;

public class TelaCadastraNovaPergunta  extends Activity
{
    private static final String DATABASE_NAME = "banco_quizzfis";
    private static final String TB_PERGUNTAS = "tb_perguntas";
    private EditText etCadastraNovaPergunta;
    private EditText etCadastraNovaResposta;
    private EditText etCadastraNovaOpcaoA;
    private EditText etCadastraNovaOpcaoB;
    private EditText etCadastraNovaOpcaoC;
    private EditText etCadastraNovaOpcaoD;
    private EditText etCadastraNovoNivelPergunta;
    private Button btCadastraNovaPergunta;
    private Bundle bundleTelaCadastraNovaPergunta;
    private Intent intentTelaCadastraNovaPergunta;
    private SQLiteDatabase bancoDados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadasrar_nova_pergunta);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etCadastraNovaPergunta = (EditText) findViewById(R.id.et_cadastra_nova_pergunta);
        etCadastraNovaResposta = (EditText) findViewById(R.id.et_cadastra_nova_resposta);
        etCadastraNovaOpcaoA = (EditText) findViewById(R.id.et_cadastra_nova_opcao_a);
        etCadastraNovaOpcaoB = (EditText) findViewById(R.id.et_cadastra_nova_opcao_b);
        etCadastraNovaOpcaoC = (EditText) findViewById(R.id.et_cadastra_nova_opcao_c);
        etCadastraNovaOpcaoD = (EditText) findViewById(R.id.et_cadastra_nova_opcao_d);
        etCadastraNovoNivelPergunta = (EditText) findViewById(R.id.et_cadastra_novo_nivel_pergunta);
        btCadastraNovaPergunta = (Button) findViewById(R.id.bt_cadastra_nova_pergunta);

        setEnabledFalse();
        t01();
        t02();
        t03();
        t04();
        t05();
        t06();
        t07();
        cadastraNovaPergunta();
    }

    private void cadastraNovaPergunta()
    {
        btCadastraNovaPergunta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    String sql = "INSERT INTO " + TB_PERGUNTAS + "(pergunta, resposta, assertiva_a, assertiva_b, assertiva_c, assertiva_d, nivel_pergunta) VALUES('"+etCadastraNovaPergunta.getText().toString()+"', '"+etCadastraNovaResposta.getText().toString()+"', '"+etCadastraNovaOpcaoA.getText().toString()+"', '"+etCadastraNovaOpcaoB.getText().toString()+"', '"+etCadastraNovaOpcaoC.getText().toString()+"', '"+etCadastraNovaOpcaoD.getText().toString()+"', "+etCadastraNovoNivelPergunta.getText().toString()+")";
                    bancoDados.execSQL(sql);
                    bundleTelaCadastraNovaPergunta = new Bundle();
                    bundleTelaCadastraNovaPergunta.putInt("chave", 2);
                    intentTelaCadastraNovaPergunta = new Intent();
                    intentTelaCadastraNovaPergunta.putExtras(bundleTelaCadastraNovaPergunta);
                    setResult(RESULT_OK, intentTelaCadastraNovaPergunta);
                    finish();
                }
                catch (Exception e)
                {
                    mensagemAlerta("", e.toString());
                    setResult(RESULT_CANCELED);
                }
                finally
                {
                    bancoDados.close();
                }
            }
        });
    }

    private void t01()
    {
        etCadastraNovaPergunta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etCadastraNovaPergunta.getText().toString().equals("")) {
                    etCadastraNovaResposta.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void t02()
    {
        etCadastraNovaResposta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etCadastraNovaResposta.getText().toString().equals("")) {
                    etCadastraNovaOpcaoA.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void t03()
    {
        etCadastraNovaOpcaoA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etCadastraNovaOpcaoA.getText().toString().equals("")) {
                    etCadastraNovaOpcaoB.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void t04()
    {
        etCadastraNovaOpcaoB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etCadastraNovaOpcaoB.getText().toString().equals("")) {
                    etCadastraNovaOpcaoC.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void t05()
    {
        etCadastraNovaOpcaoC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etCadastraNovaOpcaoC.getText().toString().equals("")) {
                    etCadastraNovaOpcaoD.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void t06()
    {
        etCadastraNovaOpcaoD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etCadastraNovaOpcaoD.getText().toString().equals("")) {
                    etCadastraNovoNivelPergunta.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void t07()
    {
        etCadastraNovoNivelPergunta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etCadastraNovoNivelPergunta.getText().toString().equals("")) {
                    btCadastraNovaPergunta.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setEnabledFalse()
    {
        etCadastraNovaResposta.setEnabled(false);
        etCadastraNovaOpcaoA.setEnabled(false);
        etCadastraNovaOpcaoB.setEnabled(false);
        etCadastraNovaOpcaoC.setEnabled(false);
        etCadastraNovaOpcaoD.setEnabled(false);
        etCadastraNovoNivelPergunta.setEnabled(false);
        btCadastraNovaPergunta.setEnabled(false);
    }

    private void mensagemAlerta(String titulo, String mensagem)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(TelaCadastraNovaPergunta.this);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }
}
