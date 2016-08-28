package com.poussiere_violette.poussieremagique;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class destine extends Activity {

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
    int bc;
   /* private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    */


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
        j=getIntent();
        destinNumber=j.getIntExtra("num",1);
        destin=new DESTIN(destinNumber,getApplicationContext());

        conteneurDuDestin = findViewById(R.id.conteneur_du_destin);
        imagee=(ImageView)findViewById(R.id.fullscreen_content);
        tv=(TextView) findViewById(R.id.sous_titres);
        imagee.setImageResource(destin.getImage());
        sousTitreColor=destin.getSousTitresColor();
        tv.setTextColor(getResources().getColor(R.color.blanc));

        isThreadRunning=true;
        //On cree un handler qui va executer du code dans l'IU thread à chaque fois qu'il recevra un message, m�me vide
        handler=new Handler(){

            public void handleMessage(Message msg){
                super.handleMessage(msg);
                tv.setText(sousTitre);

            }

        };

        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                isThreadRunning=true;
                int []text=destin.getNouvelles();
                while (isThreadRunning)
                { try {thread.sleep(1000);}
                catch (InterruptedException ie) {}

                    for (bc =0; bc<11 ; bc++)
                    {sousTitre= getResources().getString(text[bc]);
                        handler.sendEmptyMessage(0);

                        try {thread.sleep(4000);}
                        catch (InterruptedException ie) {}

                        if (bc==8)
                        {sousTitre=getResources().getString(R.string.blank);
                            handler.sendEmptyMessage(0);

                        try {thread.sleep(1000);}
                        catch (InterruptedException ie) {}}

                      //  sousTitre=getResources().getString(R.string.blank);
                      //  handler.sendEmptyMessage(0);

                     //   try {thread.sleep(2000);}
                     //   catch (InterruptedException ie) {}

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
        super.onPause();
    }


    /*
    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompatApi23.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }
       */

}
