package br.edu.ifrn.flavio.quizzfis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class TelaEditarPerguntaIndividual extends Activity
{
    private static final String DATABASE_NAME = "banco_quizzfis";
    private static final String TB_PERGUNTAS = "tb_perguntas";
    private EditText etEditarPergunta;
    private EditText etEditarResposta;
    private EditText etEditarOpcaoA;
    private EditText etEditarOpcaoB;
    private EditText etEditarOpcaoC;
    private EditText etEditarOpcaoD;
    private EditText etEditarNivelPergunta;
    private Button btAtualizarPergunta;
    private Bundle bundleTelaEditarPerguntaIndiviidual;
    private Intent intentTelaEditarPerguntaIndiviidual;
    private SQLiteDatabase bancoDados = null;
    private String perunta;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar_pergunta_individual);

        bundleTelaEditarPerguntaIndiviidual = getIntent().getExtras();
        perunta = bundleTelaEditarPerguntaIndiviidual.getString("pergunta");

        etEditarPergunta = (EditText) findViewById(R.id.et_editar_pergunta);
        etEditarResposta = (EditText) findViewById(R.id.et_editar_resposta);
        etEditarOpcaoA = (EditText) findViewById(R.id.et_editar_opcao_a);
        etEditarOpcaoB = (EditText) findViewById(R.id.et_editar_opcao_b);
        etEditarOpcaoC = (EditText) findViewById(R.id.et_editar_opcao_c);
        etEditarOpcaoD = (EditText) findViewById(R.id.et_editar_opcao_d);
        etEditarNivelPergunta = (EditText) findViewById(R.id.et_editar_nivel_pergunta);
        btAtualizarPergunta = (Button) findViewById(R.id.bt_atualizar_pergunta);

        preencherCamposEditar(perunta);
        atualizaPergunta(perunta);
    }

    private void atualizaPergunta(final String perunta)
    {
        btAtualizarPergunta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    String sql = "UPDATE " + TB_PERGUNTAS + " SET pergunta = '" + etEditarPergunta.getText().toString() + "', resposta = '" + etEditarResposta.getText().toString() + "', assertiva_a = '" + etEditarOpcaoA.getText().toString() + "', assertiva_b = '" + etEditarOpcaoB.getText().toString() + "', assertiva_c = '" + etEditarOpcaoC.getText().toString() + "', assertiva_d = '" + etEditarOpcaoD.getText().toString() + "', nivel_pergunta = '" + etEditarNivelPergunta.getText().toString() + "' WHERE pergunta = '" + perunta + "';";
                    bancoDados.execSQL(sql);
                    bundleTelaEditarPerguntaIndiviidual = new Bundle();
                    bundleTelaEditarPerguntaIndiviidual.putInt("chave", 1);
                    intentTelaEditarPerguntaIndiviidual = new Intent();
                    intentTelaEditarPerguntaIndiviidual.putExtras(bundleTelaEditarPerguntaIndiviidual);
                    setResult(RESULT_OK, intentTelaEditarPerguntaIndiviidual);
                }
                catch (Exception e)
                {
                    mensagemAlerta("", e.toString());
                    setResult(RESULT_CANCELED);
                }
                finally
                {
                    bancoDados.close();
                    finish();
                }
            }
        });
    }

    private void mensagemAlerta(String titulo, String mensagem)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(TelaEditarPerguntaIndividual.this);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }

    private void preencherCamposEditar(String pergunta)
    {
        bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String SQL = "SELECT * FROM " + TB_PERGUNTAS + " WHERE pergunta = '" + pergunta + "'";
        cursor = bancoDados.rawQuery(SQL, null);
        try
        {
            while (cursor.moveToNext())
            {
                etEditarPergunta.setText(cursor.getString(cursor.getColumnIndex("pergunta")));
                etEditarResposta.setText(cursor.getString(cursor.getColumnIndex("resposta")));
                etEditarOpcaoA.setText(cursor.getString(cursor.getColumnIndex("assertiva_a")));
                etEditarOpcaoB.setText(cursor.getString(cursor.getColumnIndex("assertiva_b")));
                etEditarOpcaoC.setText(cursor.getString(cursor.getColumnIndex("assertiva_c")));
                etEditarOpcaoD.setText(cursor.getString(cursor.getColumnIndex("assertiva_d")));
                etEditarNivelPergunta.setText(cursor.getString(cursor.getColumnIndex("nivel_pergunta")));
            }
        }
        catch (Exception e)
        {
            mensagemAlerta("preencherCamposEditar", e.toString());
        }
        finally
        {
            bancoDados.close();
            cursor.close();
        }
    }
}
