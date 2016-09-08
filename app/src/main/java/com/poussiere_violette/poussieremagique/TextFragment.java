package com.poussiere_violette.poussieremagique;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;


public class TextFragment extends Fragment {



    int fragVal;
    Animation animation, animation2, animation3, seq1;
    TextView tv;
    View v;
    View grandConteneur;
    DESTIN destin;

    int destinNumber;

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
                    {Intent i = new Intent(getActivity(), destine.class);
                        i.putExtra("num", destinNumber);
                        tv.setTextColor(00000000);
                        startActivity(i);

                    }
                });



                break;}

    }

}
