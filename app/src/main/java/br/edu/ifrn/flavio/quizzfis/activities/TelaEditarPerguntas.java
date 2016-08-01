package br.edu.ifrn.flavio.quizzfis.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifrn.flavio.quizzfis.R;

public class TelaEditarPerguntas extends Activity
{
    private static final String DATABASE_NAME = "banco_quizzfis";
    private static final String TB_PERGUNTAS = "tb_perguntas";
    private List<Map<String, String>> arrayMap = new ArrayList<>();
    private Cursor cursor;
    private SQLiteDatabase bancoDados = null;
    private ListView lvPerguntas;
    private SimpleAdapter simpleAdapter;
    private TextView text1;
    private TextView text2;
    private TextView tvTotalPerguntasCadastradas;
    private View minhaView;
    private Button btEditar;
    private Button btDeletar;
    private Button btNovaPergunta;
    private Intent intentTelaEditarPerguntas;
    private Bundle bundleTelaEditarPergunta;
    private TableLayout tlRecordes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar_perguntas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lvPerguntas = (ListView) findViewById(R.id.lv_perguntas);
        tvTotalPerguntasCadastradas = (TextView) findViewById(R.id.tv_total_perguntas_cadastradas);
        btNovaPergunta = (Button) findViewById(R.id.bt_cadastra_nova_pergunta);
        tlRecordes = (TableLayout) findViewById(R.id.tl_recordes);
        preencheListviewPerguntas();
        cadastraNovaPergunta();
    }

    private void cadastraNovaPergunta()
    {
        btNovaPergunta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                intentTelaEditarPerguntas = new Intent(getApplicationContext(), TelaCadastraNovaPergunta.class);
                startActivityForResult(intentTelaEditarPerguntas, 2);
            }
        });
    }

    private void preencheListviewPerguntas()
    {
        Map<String, String> mapString;
        bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String SQL = "SELECT * FROM " + TB_PERGUNTAS + ";";
        cursor = bancoDados.rawQuery(SQL, null);
        try
        {
            if (cursor.getCount() > 0)
            {
                tvTotalPerguntasCadastradas.setText("Total de perguntas cadastradas: " + cursor.getCount());
                tlRecordes.setColumnStretchable(0, true);
                while (cursor.moveToNext())
                {
                    mapString = new HashMap<>();
                    mapString.put("pergunta", cursor.getString(cursor.getColumnIndex("pergunta")));
                    mapString.put("nivel", "Nível " +  cursor.getInt(cursor.getColumnIndex("nivel_pergunta")));
                    arrayMap.add(mapString);
                }
            }
            else if (cursor.getCount() <= 0)
            {
                tvTotalPerguntasCadastradas.setText("Nenhuma pergunta cadastrada");
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

        String[] de = {"pergunta", "texto"};

        int[] para = {R.id.tv_testeteste};

        simpleAdapter = new SimpleAdapter(this, arrayMap, R.layout.lv_personalizado_tela_editar_perguntas, de, para)
        {
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                text1 = (TextView) view.findViewById(R.id.tv_testeteste);
                btEditar = (Button) view.findViewById(R.id.bt_editar_tela_editar_perguntas);
                btDeletar = (Button) view.findViewById(R.id.bt_deletar);
                minhaView = super.getView(position, convertView, parent);
                editarPergunta(text1.getText().toString());
                deletarPergunta(text1.getText().toString(), position);
                return view;
            }
        };
        lvPerguntas.setAdapter(simpleAdapter);
    }

    private void editarPergunta(final String pergunta)
    {
        btEditar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bundleTelaEditarPergunta = new Bundle();
                bundleTelaEditarPergunta.putString("pergunta", pergunta);
                bundleTelaEditarPergunta.putInt("percodigo", 99);
                intentTelaEditarPerguntas = new Intent(getApplicationContext(), TelaEditarPerguntaIndividual.class);
                intentTelaEditarPerguntas.putExtras(bundleTelaEditarPergunta);
                startActivityForResult(intentTelaEditarPerguntas, 1);
            }
        });
    }

    private void deletarPergunta(final String item, final int posicao)
    {
        btDeletar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mensagemAlerta("Deletar", "Deseja realmente deletar pergunta?", item, posicao);

            }
        });
    }

    private void mensagemAlerta(String titulo, String mensagem, final String item, final int posicao)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(TelaEditarPerguntas.this);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setIcon(R.drawable.ic_icone_exclamacao);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                excluirItem(item);
                arrayMap.remove(posicao);
                simpleAdapter.notifyDataSetChanged();
                bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                String SQL = "SELECT * FROM " + TB_PERGUNTAS + " ORDER BY pergunta";
                cursor = bancoDados.rawQuery(SQL, null);

                try
                {
                    if (cursor.getCount() > 0)
                    {
                        tvTotalPerguntasCadastradas.setText("Total de perguntas cadastradas: " + cursor.getCount());
                    }
                    else if (cursor.getCount() <= 0)
                    {
                        tvTotalPerguntasCadastradas.setText("Nenhuma pergunta cadastrada");
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
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        alerta.show();
    }

    private void excluirItem(String item)
    {
        try
        {

            bancoDados = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            String SQL = "DELETE FROM " + TB_PERGUNTAS + " WHERE pergunta = '" + item + "'; ";
            bancoDados.execSQL(SQL);
            Toast.makeText(getApplicationContext(), "Item excluido", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

        }
        finally
        {
            bancoDados.close();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode == RESULT_CANCELED)
        {
        }
        else if(resultCode == RESULT_OK)
        {
            bundleTelaEditarPergunta = intent.getExtras();
            int chave = bundleTelaEditarPergunta.getInt("chave");
            if (chave == 1)
            {
                Toast.makeText(getApplicationContext(), "Pergunta atualizada", Toast.LENGTH_LONG).show();
                arrayMap.clear();
                preencheListviewPerguntas();
            }
            if (chave == 2)
            {
                Toast.makeText(getApplicationContext(), "Pergunta cadastrada", Toast.LENGTH_LONG).show();
                arrayMap.clear();
                preencheListviewPerguntas();
            }
        }
    }
}
