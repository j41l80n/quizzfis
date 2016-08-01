package br.edu.ifrn.flavio.quizzfis.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import br.edu.ifrn.flavio.quizzfis.R;

public class TelaOpcoes extends Activity
{
    private static final String DATABASE_NAME = "banco_quizzfis";
    private static final String TB_RECORDES = "tb_recordes";
    Bundle bundleTelaOpcoes;
    Intent intentTelaOpcoes;
    private SQLiteDatabase bancoDados;
    private TableLayout tlRecordes;
    private Button btEditar;
    private LinearLayout llPrincipalTelaOpcoes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_opcoes);

        btEditar = (Button) findViewById(R.id.bt_editar);
        btEditar.setVisibility(View.GONE);

        mostrarRecordes();
        mostrarEditar();
        carregaRecordes();
        apagarRecordes();
        editarPerguntas();
        mostraModoSelecionado();
    }

    @Override
    public void onBackPressed()
    {
        int chave = 0;
        RadioGroup rgNiveisDificuldade = (RadioGroup) findViewById(R.id.rg_niveis_dificuldade);

        switch (rgNiveisDificuldade.getCheckedRadioButtonId())
        {
            case R.id.rb_dificuldade_normal:
                chave = 0;
                //Toast.makeText(getApplicationContext(), "Modo Normal selecionado!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_dificuldade_survivor:
                chave = 1;
                //Toast.makeText(getApplicationContext(), "Modo Survivor selecionado!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_dificuldade_multiplayer:
                //Toast.makeText(getApplicationContext(), "Modo Multiplayer Selecionado!", Toast.LENGTH_SHORT).show();
                chave = 2;
                break;
        }
        intentTelaOpcoes = new Intent();
        bundleTelaOpcoes = new Bundle();
        bundleTelaOpcoes.putInt("chave", chave);
        intentTelaOpcoes.putExtras(bundleTelaOpcoes);
        setResult(RESULT_OK, intentTelaOpcoes);
        finish();
    }

    private void mostraModoSelecionado()
    {
        final RadioGroup rgNiveisDificuldade = (RadioGroup) findViewById(R.id.rg_niveis_dificuldade);
        rgNiveisDificuldade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (rgNiveisDificuldade.getCheckedRadioButtonId())
                {
                    case R.id.rb_dificuldade_normal:
                        Toast.makeText(getApplicationContext(), "Modo Normal selecionado!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_dificuldade_survivor:
                        Toast.makeText(getApplicationContext(), "Modo Survivor selecionado!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_dificuldade_multiplayer:
                        Toast.makeText(getApplicationContext(), "Modo Multiplayer Selecionado!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void mostrarEditar()
    {
        llPrincipalTelaOpcoes = (LinearLayout) findViewById(R.id.ll_principal_tela_opcoes);
        llPrincipalTelaOpcoes.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                btEditar.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    private void mostrarRecordes()
    {
        tlRecordes = (TableLayout) findViewById(R.id.tl_recordes);
        tlRecordes.setVisibility(View.GONE);
        tlRecordes.setStretchAllColumns(true);
        tlRecordes.setShrinkAllColumns(true);
        final ToggleButton tbRecordes = (ToggleButton) findViewById(R.id.tb_recordes);

        tbRecordes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(tbRecordes.isChecked())
                {
                    tlRecordes.setVisibility(View.VISIBLE);
                }
                else
                {
                    tlRecordes.setVisibility(View.GONE);
                }
            }
        });
    }

    private void carregaRecordes()
    {
        bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String SQL = "SELECT * FROM " + TB_RECORDES + ";";
        Cursor cursor = bancoDados.rawQuery(SQL, null);

        try
        {
            while (cursor.moveToNext())
            {
                TableRow trLinhas = new TableRow(this);
                trLinhas.setGravity(Gravity.CENTER_HORIZONTAL);

                TextView pontuacao = new TextView(this);
                TextView dataJogo = new TextView(this);
                TextView maiorNivel = new TextView(this);
                TextView jogador = new TextView(this);

                pontuacao.setGravity(Gravity.CENTER);
                dataJogo.setGravity(Gravity.CENTER);
                maiorNivel.setGravity(Gravity.CENTER);
                jogador.setGravity(Gravity.CENTER);

                jogador.setText(cursor.getString(cursor.getColumnIndex("nome_jogador")));
                jogador.setTextColor(Color.WHITE);
                trLinhas.addView(jogador);// add the column to the table row here


                pontuacao.setText(cursor.getString(cursor.getColumnIndex("pontuacao"))); // set the text for the header
                pontuacao.setTextColor(Color.WHITE); // set the color
                trLinhas.addView(pontuacao); // add the column to the table row here

                dataJogo.setText(cursor.getString(cursor.getColumnIndex("data_jogo"))); // set the text for the header
                dataJogo.setTextColor(Color.WHITE); // set the color
                trLinhas.addView(dataJogo); // add the column to the table row here

                maiorNivel.setText(cursor.getString(cursor.getColumnIndex("maior_nivel"))); // set the text for the header
                maiorNivel.setTextColor(Color.WHITE); // set the color
                trLinhas.addView(maiorNivel); // add the column to the table row here
                tlRecordes.addView(trLinhas);
            }
        }
        catch (Exception e)
        {
            mensagemAlerta("", e.toString());
        }
        finally
        {
            cursor.close();
            bancoDados.close();
        }

    }

    private void editarPerguntas()
    {
        btEditar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                intentTelaOpcoes = new Intent(getApplicationContext(), TelaEditarPerguntas.class);
                startActivity(intentTelaOpcoes);
            }
        });
    }

    private void mensagemAlerta(String titulo, String mensagem)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(TelaOpcoes.this);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }

    private void apagarRecordes()
    {
        tlRecordes = (TableLayout) findViewById(R.id.tl_recordes);
        final ToggleButton tbRecordes = (ToggleButton) findViewById(R.id.tb_recordes);

        tbRecordes.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                try
                {
                    bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    String SQL = "DELETE FROM " + TB_RECORDES + ";";
                    bancoDados.execSQL(SQL);

                    while (tlRecordes.getChildCount() > 1)
                    {
                        TableRow row =  (TableRow)tlRecordes.getChildAt(1);
                        tlRecordes.removeView(row);
                    }
                    carregaRecordes();
                }
                catch (Exception e)
                {
                    mensagemAlerta("", e.toString());
                }
                finally
                {
                    vibrar();
                    bancoDados.close();
                }
                return false;
            }
        });
    }

    private void vibrar()
    {
        Vibrator vibrar = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milliseconds = 40;
        vibrar.vibrate(milliseconds);
    }
}
