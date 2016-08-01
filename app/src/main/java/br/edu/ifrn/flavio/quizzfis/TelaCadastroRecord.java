package br.edu.ifrn.flavio.quizzfis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TelaCadastroRecord extends Activity
{
    private SQLiteDatabase bancoDados = null;
    private static final String DATABASE_NAME = "banco_quizzfis";
    private static final String TB_RECORDES = "tb_recordes";
    int pontuacao = 0;
    int maiorNivel = 0;
    private Intent intentTelaCadastroRecord;
    private Bundle bundleTelaCadastroRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastar_record);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        configuraTela();
        cadastrarRecord();
    }

    private void cadastrarRecord()
    {
        bundleTelaCadastroRecord = getIntent().getExtras();
        pontuacao = bundleTelaCadastroRecord.getInt("pontuacao");

        final EditText etNomeJogador = (EditText) findViewById(R.id.et_nome_jogador);
        Button btCadastraRecorde = (Button) findViewById(R.id.bt_cadastra_recorde);

        btCadastraRecorde.setOnClickListener(new View.OnClickListener()
        {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = sdf.format(c.getTime());

            @Override
            public void onClick(View v)
            {
                String verificaCampo = etNomeJogador.getText().toString();

                if (verificaCampo.trim().isEmpty())
                {
                    etNomeJogador.setError("Campo vazio");
                    etNomeJogador.requestFocus();
                }
                else
                {
                    try
                    {
                        bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                        String sql = "INSERT INTO " + TB_RECORDES + " (nome_jogador, data_jogo, pontuacao, maior_nivel) VALUES('" + etNomeJogador.getText().toString().trim() + "', '" + data + "', " + pontuacao + ", " + maiorNivel + ");";
                        bancoDados.execSQL(sql);
                        bundleTelaCadastroRecord = new Bundle();
                        bundleTelaCadastroRecord.putInt("chave", 1);
                        intentTelaCadastroRecord = new Intent();
                        intentTelaCadastroRecord.putExtras(bundleTelaCadastroRecord);
                        setResult(RESULT_OK, intentTelaCadastroRecord);
                    } catch (Exception e) {
                        mensagemAlerta("", e.toString());
                        setResult(RESULT_CANCELED);
                    } finally {
                        bancoDados.close();
                        finish();
                    }
                }
            }
        });
    }

    private void mensagemAlerta(String titulo, String mensagem)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(TelaCadastroRecord.this);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }

    @Override
    public void onBackPressed()
    {

    }

    private void configuraTela()
    {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        this.getWindow().setAttributes(params);
    }
}
