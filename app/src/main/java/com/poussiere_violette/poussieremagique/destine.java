package com.poussiere_violette.poussieremagique;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class destine extends Activity  {

    private final Handler mHideHandler = new Handler();
    private View conteneurDuDestin;
    private ImageView myImage;
    Intent j;
    int destinNumber;
    DESTIN destin;
    ImageView imagee;
    boolean isThreadRunning;
    Handler handler;
    TextView tv;
    String sousTitre;
    int sousTitreColor;
    Thread thread;
    int tabSize;
    int bc;
    int timeSoutitre;
    MediaPlayer mPlayer;
    boolean clickToLeave =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Forcer l'ecran à rester allumé
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // FOrce l'activité à etre en plein ecran
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_destine2);
        j = getIntent();
        destinNumber = j.getIntExtra("num", 1);
        destin = new DESTIN(destinNumber, getApplicationContext());

        conteneurDuDestin = findViewById(R.id.conteneur_du_destin);
        imagee = (ImageView) findViewById(R.id.fullscreen_content);
        tv = (TextView) findViewById(R.id.sous_titres);
        imagee.setImageResource(destin.getImage());
        sousTitreColor = destin.getSousTitresColor();
        tv.setTextColor(ContextCompat.getColor(getApplicationContext(), sousTitreColor));


        isThreadRunning=true;
        //On cree un handler qui va executer du code dans l'IU thread à chaque fois qu'il recevra un message, m�me vide
        handler=new Handler(){

            public void handleMessage(Message msg){
                super.handleMessage(msg);
                tv.setText(sousTitre);
                if (clickToLeave)
                {
                    imagee.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        Intent in=new Intent(destine.this, QueFaire.class);
                            in.putExtra("num", destinNumber);
                            startActivity(in);
                        }
                    });
                }

            }

        };




        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                isThreadRunning=true;

                if (destin.getMusic() != 0) {
                    mPlayer = MediaPlayer.create(destine.this, destin.getMusic());
                    mPlayer.start();

                }


                int []text=destin.getNouvelles();
                tabSize=destin.getTabSize();
                timeSoutitre=destin.getTimeSousTitre();
                while (isThreadRunning)
                { try {thread.sleep(1000);}
                catch (InterruptedException ie) {}

                    for (bc =0; bc<tabSize ; bc++)
                    {sousTitre= getResources().getString(text[bc]);

                        if (bc==(tabSize-1)) clickToLeave=true;
                        handler.sendEmptyMessage(0);


                        try {thread.sleep(timeSoutitre);}
                        catch (InterruptedException ie) {}


                    }

                isThreadRunning=false;

                }
            }
        });
        thread.start();




    }

    @Override
public void onResume()
    {

        conteneurDuDestin.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        super.onResume();


    }




    @Override
    public void onPause()
    {
        isThreadRunning=false;

        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.reset();
            mPlayer.release();
            mPlayer = null;
            Log.i("ACT2", "mediaplayer stoppé");
        }
        super.onStop();


        super.onPause();
        finish();


    }


    @Override
    public void onBackPressed() {
        // ne fais rien. Le destin ne connait pas le mot retour
    }




}
