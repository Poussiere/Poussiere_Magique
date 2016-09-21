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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS= 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View  conteneur;
    Intent i, j;
    TextView mContentView, aPropos;
    Animation animation, animation2,  animation3, animation4, animation5, animation6;
    AnimationSet animationSet;
    private final Runnable mHidePart2Runnable = new Runnable() {
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
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
          //  ActionBar actionBar = getSupportActionBar();
           // if (actionBar != null) {
            //    actionBar.show();
          //  }
          //  mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
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



        mVisible = true;


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

       mContentView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

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




       /* // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

       ;
       */
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(0);

    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
     /* ActionBar actionBar = getSupportActionBar();
       if (actionBar != null) {
          actionBar.hide();
        }*/
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);

      mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);

    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);


    }


    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

@Override
    public void onResume()
{


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



    super.onResume();
}


@Override
    public void onPause()
{super.onPause();
finish();}
}
