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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.poussiere_violette.poussieremagique.AnnexActivities.APropos;


public class MainActivity extends Activity {

    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View  conteneur;
    private Intent i, j;
    private TextView mContentView, aPropos;
    private Animation animation, animation2,  animation3, animation4, animation5, animation6;
    private AnimationSet animationSet;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {

            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animation3=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink_no_repeat);
        animation3.setDuration(1500);
        setContentView(R.layout.activity_main);
        mContentView = (TextView) findViewById(R.id.fullscreen_content);

        conteneur=(View)findViewById(R.id.conteneur_du_main);

        aPropos=(TextView) findViewById(R.id.propos);



        i=new Intent(MainActivity.this, MainActivity2.class);
        j=new Intent(MainActivity.this,APropos.class);

        aPropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(j);
            }
        });


        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zominlent2);
        animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        animation2.setDuration(2000);
        animation4=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.inverse_blink_no_repeat);
        animation5=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        animation6=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink_no_repeat);

        animation.setDuration(2000);
        animation4.setDuration(2000);
        animation5.setDuration(2000);
        animation6.setDuration(900);

        animationSet=new AnimationSet(true);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(animation5);

        mContentView.startAnimation(animation2);
        aPropos.startAnimation(animation2);

    }


    private void hide() {


      mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);

    }


@Override
    public void onResume()
{

hide();
    conteneur.startAnimation(animation3);

    animation3.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mContentView.setText(R.string.des);

            aPropos.setText(R.string.a_propos);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });

    mContentView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mContentView.setOnClickListener(null);
            conteneur.startAnimation(animation4);
            mContentView.startAnimation(animationSet);



            animation6.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(i);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });



            animation4.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {


                    conteneur.startAnimation(animation6);
                    mContentView.setText("");
                    aPropos.setText("");



                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


        }
    });

    super.onResume();
}


@Override
    public void onPause()
{super.onPause();}
}
