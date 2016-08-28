package com.poussiere_violette.poussieremagique;

import android.content.Context;
import android.media.Image;
import android.support.design.widget.TabLayout;

import java.util.Random;

/**
 * Chaque objet destin possede un texte spécifique pour le sixième fragment d'introduction, une image et un tableau de 10 String correspondant commentaire de la photo au (puis une musique)
 */
public class DESTIN {

    String frag6;
    int image;
    int[] nouvelles = new int [11] ;
    int sousTitresColor;

    public DESTIN(int destinNumber, Context c) {

        switch (destinNumber) {
            case 1:
                String x = "ȇ";
                frag6="r"+x+"ve";
                image=R.drawable.lasmall;
                sousTitresColor=R.color.gris_la;
                nouvelles[0]= R.string.n1;
                nouvelles[1]= R.string.n2;
                nouvelles[2]= R.string.n3;
                nouvelles[3]= R.string.n4;
                nouvelles[4]= R.string.n5;
                nouvelles[5]= R.string.n6;
                nouvelles[6]= R.string.n7;
                nouvelles[7]= R.string.n8;
                nouvelles[8]= R.string.n9;
                nouvelles[9]= R.string.n10;
                nouvelles[10]=R.string.blank;
                break;

            case 2:
                image=R.drawable.hotline;
                frag6=c.getResources().getString(R.string.t7);
                sousTitresColor=R.color.blanc;
                nouvelles[0]= R.string.m1;
                nouvelles[1]= R.string.m2;
                nouvelles[2]= R.string.m3;
                nouvelles[3]= R.string.m4;
                nouvelles[4]= R.string.m5;
                nouvelles[5]= R.string.m6;
                nouvelles[6]= R.string.m7;
                nouvelles[7]= R.string.m8;
                nouvelles[8]= R.string.m9;
                nouvelles[9]= R.string.m10;
                nouvelles[10]=R.string.blank;
                break;

        }
    }
    
    public String getFrag6()
    {return frag6;}

    public int getImage ()
    {return image;}

    public int [] getNouvelles()
    {return nouvelles;}

    public int getSousTitresColor()
    {return sousTitresColor;}


}