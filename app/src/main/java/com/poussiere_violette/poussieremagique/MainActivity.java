package com.poussiere_violette.poussieremagique;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;


public class MainActivity extends FragmentActivity {

    public static int ITEMS = 6;
    MyFragmentAdapter mAdapter ;
    ViewPager vPager;
    View grandConteneur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // FOrce l'activité à etre en plein ecran
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);




        setContentView(R.layout.activity_main);
        grandConteneur=(View) findViewById(R.id.main_content);
        mAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        vPager = (ViewPager)findViewById(R.id.container);
        vPager.setAdapter(mAdapter);

    }

    @Override
    public void onResume ()
    {

        grandConteneur.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        super.onResume();

    }

    public static class MyFragmentAdapter extends FragmentStatePagerAdapter
    {

        public MyFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }



        @Override
        public int getCount() {
            return ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return TextFragment.init(position);
                case 1: return TextFragment.init(position);
                default:return TextFragment.init(position);

            }
        }

    }

}

