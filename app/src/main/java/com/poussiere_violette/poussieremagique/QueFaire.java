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
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class QueFaire extends Activity {

    private View conteneur;
    private TextView tv1, tv2, tv3, tv4;
    private Intent i, j , k, startMain;
    private Animation fondu, sortie;
    private int destinNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_que_faire);

        tv1=(TextView)findViewById(R.id.que_faire);
        tv2=(TextView)findViewById(R.id.retour_destin);
        tv3=(TextView)findViewById(R.id.relancer_roue);
        tv4=(TextView)findViewById(R.id.quitter);
        conteneur = (View)findViewById(R.id.conteneur_du_que_faire);
        fondu=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        sortie=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        fondu.setDuration(500);
        sortie.setDuration(500);
        i=new Intent(QueFaire.this, destine.class);
        j=new Intent(QueFaire.this, MainActivity.class);
        startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        k = getIntent();
        destinNumber = k.getIntExtra("num", 1);


        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fondu.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        i.putExtra("num", destinNumber);
                        startActivity(i);
                        overridePendingTransition(R.anim.blink_no_repeat, R.anim.inverse_blink_no_repeat);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                tv1.startAnimation(fondu);
                tv4.startAnimation(fondu);
                tv3.startAnimation(fondu);




            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fondu.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(j);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                tv1.startAnimation(fondu);
                tv2.startAnimation(fondu);
                tv4.startAnimation(fondu);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fondu.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(startMain);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                tv1.startAnimation(fondu);
                tv2.startAnimation(fondu);
                tv3.startAnimation(fondu);

            }
        });

    }

    @Override
    public void onResume()
    {

        conteneur.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
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
    super.onPause();
    finish();}
}

