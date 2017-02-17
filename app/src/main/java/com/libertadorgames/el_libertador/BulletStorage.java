package com.libertadorgames.el_libertador;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by teo on 23/4/2016.
 */
public class BulletStorage {


    private class Magazin {

         int type;
         int count;

         Magazin (int t, int c) {
            type = t;
            count =c;
        }


    }

    protected HashMap<Integer,Magazin> magazins;

    public boolean fire(int type) {

        Magazin active = magazins.get(type);

        if (active==null)
            return false;

        if (active.count<=0)
            return false;
        else{
            active.count--;
            return true;
        }


    }

    public int getBulletCount(int type) {

        Magazin active = magazins.get(type);

        if (active==null)
            return 0;


        else {

            return active.count;
        }
    }

    public void push( int type, int c) {
        Magazin m = magazins.get(type);
        m.count += c;
    }

    public BulletStorage() {
        magazins = new HashMap<Integer,Magazin>();
        Magazin simple = new Magazin(0,0);
        Magazin dual = new Magazin(1,0);
        Magazin triple = new Magazin(2,0);
        Magazin laz1 = new Magazin(3,0);
        Magazin laz2 = new Magazin(4,0);
        magazins.put(0,simple);
        magazins.put(1,dual);
        magazins.put(2,triple);
        magazins.put(3,laz1);
        magazins.put(4,laz2);


    }
}
