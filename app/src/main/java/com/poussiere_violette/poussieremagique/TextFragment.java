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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;


public class TextFragment extends Fragment {



    private int fragVal;
    private Animation animation, animation2, animation3, seq1;
    private TextView tv;
    private View v;
    private View grandConteneur;
    private DESTIN destin;
    private int destinNumber;

    static TextFragment init(int val) {
        TextFragment tf = new TextFragment();
        Bundle args = new Bundle();
        args.putInt("val", val);
        tf.setArguments(args);
        return tf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_text, container,
                false);
        tv = (TextView) layoutView.findViewById(R.id.tasty);
        v =(View)layoutView.findViewById(R.id.conteneur);
        grandConteneur=(View) getActivity().findViewById(R.id.main_content);


        destinNumber=((MainActivity2)this.getActivity()).getDestinNumber();
        destin=new DESTIN(destinNumber, getContext());


        animation = AnimationUtils.loadAnimation(getContext(),R.anim.zoomout);
        animation2=AnimationUtils.loadAnimation(getContext(),R.anim.blink);
        seq1=AnimationUtils.loadAnimation(getContext(),R.anim.seq1);
        animation3=AnimationUtils.loadAnimation(getContext(),R.anim.blink_no_repeat);



        return layoutView;

    }

    @Override
    public void onPause()
    {tv.clearAnimation();
    v.clearAnimation();
    super.onPause();}


    @Override
    public void onResume()
    { super.onResume();

        switch (fragVal) {
            case 0:
                tv.setText(R.string.t1);

                // animation2.setDuration(1000);
                tv.setAnimation(animation);
                //   v.setAnimation(animation2);
                break;
            case 1:
                tv.setText(R.string.t2);
                animation2.setDuration(300);
                tv.setAnimation(animation);
                v.setAnimation(animation2);
                break;
            case 2:
                tv.setText(R.string.t3);
                animation2.setDuration(200);
                tv.setAnimation(animation);
                v.setAnimation(animation2);
                break;
            case 3:
                tv.setText(R.string.t4);
                animation2.setDuration(100);
                tv.setAnimation(animation);
                v.setAnimation(animation2);
                break;
            case 4:
                tv.setText(destin.getFrag5());
                animation2.setDuration(50);
                tv.setAnimation(animation);
                v.setAnimation(animation2);
                break;
            case 5:

                tv.setAnimation(seq1);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, destin.getFrag6Size());
                tv.setText(destin.getFrag6());
                v.setAnimation(animation3);

                tv.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        tv.setTextColor(00000000);
                        Intent i = new Intent(getActivity(), destine.class);
                        i.putExtra("num", destinNumber);
                        startActivity(i);




                    }
                });



                break;}

    }

}
