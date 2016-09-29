package com.poussiere_violette.poussieremagique;

import android.content.Context;
import android.media.Image;
import android.support.design.widget.TabLayout;

import java.util.Random;

/**
 * Chaque objet destin possede un texte spécifique pour le sixième fragment d'introduction, une image et un tableau de 10 String correspondant commentaire de la photo au (puis une musique)
 */
public class DESTIN {


    int frag5, frag6 ;
    int image;
    int[] nouvelles = new int [20] ;
    int sousTitresColor;
    int frag6Size;
    int tabSize;
    int timeSousTitre;
    int music;

    public DESTIN(int destinNumber, Context c) {

        switch (destinNumber) {
            case 1:
                frag5=R.string.t5;
                frag6=R.string.t6;
                frag6Size=100;
                timeSousTitre=8000;

                image=R.drawable.lasmall;
                sousTitresColor=R.color.gris_la;
                tabSize=11;

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

                music=R.raw.qqq;
                break;

            case 2:
                image=R.drawable.hotline;
                frag5=R.string.t5;
                frag6=R.string.t7;
                frag6Size=100;
                timeSousTitre=3500;
                tabSize=11;
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

                music=R.raw.pinto_marisol;
                break;

            case 3:
                image=R.drawable.lofoten;
                frag5=R.string.t5;
                frag6=R.string.t8;
                timeSousTitre=7000;
                tabSize=14;
                sousTitresColor=R.color.gris_la;
                frag6Size=60;
                nouvelles[0]= R.string.o1;
                nouvelles[1]= R.string.o2;
                nouvelles[2]= R.string.o3;
                nouvelles[3]= R.string.o4;
                nouvelles[4]= R.string.o5;
                nouvelles[5]= R.string.o6;
                nouvelles[6]= R.string.o7;
                nouvelles[7]= R.string.o8;
                nouvelles[8]= R.string.o9;
                nouvelles[9]= R.string.o10;
                nouvelles[10]=R.string.o11;
                nouvelles[11]=R.string.o12;
                nouvelles[12]=R.string.blank;

                music=R.raw.lofosong;
                break;


            case 4:
                image=R.drawable.tunnel;
                frag5=R.string.t5bis;
                frag6=R.string.t9;

                timeSousTitre=8000;
                tabSize=14;
                sousTitresColor=R.color.blanc;
                frag6Size=60;
                nouvelles[0]= R.string.p1;
                nouvelles[1]= R.string.p2;
                nouvelles[2]= R.string.p3;
                nouvelles[3]= R.string.p4;
                nouvelles[4]= R.string.p5;
                nouvelles[5]= R.string.p6;
                nouvelles[6]= R.string.p7;
                nouvelles[7]= R.string.p8;
                nouvelles[8]= R.string.p9;
                nouvelles[9]= R.string.p10;
                nouvelles[10]= R.string.p11;
                nouvelles[11]= R.string.p12;
                nouvelles[12]= R.string.p13;
                nouvelles[13]=R.string.blank;

                music=R.raw.corona_radiata;
                break;
        }
    }

    public int getFrag5()
    {return frag5;}

    public int getFrag6()
    {return frag6;}

    public int getImage ()
    {return image;}

    public int getTabSize()
    {return tabSize;}

    public int getTimeSousTitre()
    {return timeSousTitre;}

    public int [] getNouvelles()
    {return nouvelles;}

    public int getSousTitresColor()
    {return sousTitresColor;}

    public int getFrag6Size()
    {return frag6Size;}

    public int getMusic()
    {return music;}


}