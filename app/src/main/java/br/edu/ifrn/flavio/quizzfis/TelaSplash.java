package br.edu.ifrn.flavio.quizzfis;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class TelaSplash extends Activity implements Runnable
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_splash);

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    public void run()
    {
        startActivity(new Intent(this, MyActivity.class));
        finish();
    }
}