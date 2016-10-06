/*Copyright (C) <2016>  <Nicolas BOUTIN>

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/

        contact us : von.linnasta@gmail.com

*/

package com.poussiere_violette.poussieremagique;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


public class destine extends Activity  {


    private View conteneurDuDestin;
    private Intent j;
    private int destinNumber;
    private DESTIN destin;
    private ImageView imagee;
    private boolean isThreadRunning;
    private Handler handler;
    private TextView tv;
    private  String sousTitre;
    private int sousTitreColor;
    private Thread thread;
    private int tabSize;
    private int bc;
    private int timeSoutitre;
    private MediaPlayer mPlayer;
    private boolean clickToLeave =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Forcer l'ecran à rester allumé
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

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
        //On cree un handler qui va executer du code dans l'IU thread à chaque fois qu'il recevra un message, meme vide
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
                            overridePendingTransition(R.anim.blink_no_repeat, R.anim.inverse_blink_no_repeat);
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
                 mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Intent in=new Intent(destine.this, QueFaire.class);
                            in.putExtra("num", destinNumber);
                            startActivity(in);
                            overridePendingTransition(R.anim.blink_no_repeat, R.anim.inverse_blink_no_repeat);
                        }
                    });


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
