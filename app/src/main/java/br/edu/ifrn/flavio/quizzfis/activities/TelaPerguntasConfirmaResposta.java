package br.edu.ifrn.flavio.quizzfis.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.edu.ifrn.flavio.quizzfis.R;

public class TelaPerguntasConfirmaResposta extends Activity
{
    private Bundle bundle;
    private Intent intent;
    private Button btCancelaResposta;
    private Button btConfirmaResposta;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_perguntas_confirma_resposta);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btConfirmaResposta = (Button) findViewById(R.id.bt_confirma_resposta);
        btCancelaResposta = (Button) findViewById(R.id.bt_cancela_resposta);

        confirmarResposta();
        cancelarResposta();
        configurarJanela();
    }

    private void confirmarResposta()
    {
        btConfirmaResposta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bundle = getIntent().getExtras();

                if(bundle.getInt("chave") == 2)
                {
                    bundle = new Bundle();
                    bundle.putInt("chave", 99);
                    intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                }
                else if(bundle.getInt("chave") == 1 || bundle.getInt("chave") == 0)
                {
                    bundle = new Bundle();
                    bundle.putInt("chave", 0);
                    intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }

    private void cancelarResposta()
    {
        btCancelaResposta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void configurarJanela()
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
}
