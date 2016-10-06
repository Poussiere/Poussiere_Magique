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


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Random;


public class MainActivity2 extends FragmentActivity {

    public static int ITEMS = 6;
    private MyFragmentAdapter mAdapter ;
    private ViewPager vPager;
    private View grandConteneur;
    private Random ran;
    private  int destinNumber;
    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        grandConteneur=(View) findViewById(R.id.main_content);
        vPager = (ViewPager)findViewById(R.id.container);
       vPager.startAnimation(animation);

        ran=new Random();
        destinNumber= ran.nextInt(4 - 1 + 1) + 1;
        mAdapter = new MyFragmentAdapter(getSupportFragmentManager());

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


    @Override
    public void onPause()
    {super.onPause();
    finish();}

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(MainActivity2.this, MainActivity.class));}

    public int getDestinNumber()
    {return this.destinNumber;}

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





















