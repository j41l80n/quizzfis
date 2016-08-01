package br.edu.ifrn.flavio.quizzfis;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaMultiplayerResultado extends Activity
{
    TextView p1;
    TextView p2;
    private Bundle bundleTelaMultiplayerResultado;
    private Intent intentTelaMultiplayerResultado;
    ImageView ivResultadoMultiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //remove a barra de titulos
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_multiplayer_resultado);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bundleTelaMultiplayerResultado = getIntent().getExtras();
        ivResultadoMultiplayer = (ImageView) findViewById(R.id.iv_resultado_multiplayer);
        p1 = (TextView) findViewById(R.id.player1);
        p2 = (TextView) findViewById(R.id.player2);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/caviar_negrito.ttf");
        p1.setTypeface(font);
        p2.setTypeface(font);

        if(bundleTelaMultiplayerResultado.getInt("pontuacao1") > bundleTelaMultiplayerResultado.getInt("pontuacao2"))
        {
            ivResultadoMultiplayer.setBackground(getResources().getDrawable(R.drawable.bg_player1_vencedor640x360));
        }
        else if(bundleTelaMultiplayerResultado.getInt("pontuacao1") < bundleTelaMultiplayerResultado.getInt("pontuacao2"))
        {
            ivResultadoMultiplayer.setBackground(getResources().getDrawable(R.drawable.bg_player2_vencedor640x360));
        }
        else
        {
            ivResultadoMultiplayer.setBackground(getResources().getDrawable(R.drawable.bg_miltyplayer_empate_640x360));
        }

        p1.setText("" + bundleTelaMultiplayerResultado.getInt("pontuacao1"));
        p2.setText("" + bundleTelaMultiplayerResultado.getInt("pontuacao2"));
    }

    @Override
    public void onBackPressed()
    {
        bundleTelaMultiplayerResultado = new Bundle();
        bundleTelaMultiplayerResultado.putInt("chave", 1);
        intentTelaMultiplayerResultado = new Intent();
        intentTelaMultiplayerResultado.putExtras(bundleTelaMultiplayerResultado);
        setResult(RESULT_OK, intentTelaMultiplayerResultado);
        finish();
    }
}
