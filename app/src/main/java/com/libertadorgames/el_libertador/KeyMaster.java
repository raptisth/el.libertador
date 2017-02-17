package com.libertadorgames.el_libertador;


import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by teo on 8/4/2016.
 */
public class KeyMaster implements View.OnTouchListener{

    protected float x=0;
    protected float y=0;
    protected boolean generic_switch = false;
    protected ArrayList<SurfaceControl> controls;




    public KeyMaster () {
        controls = new ArrayList<SurfaceControl>();
    }




    public void addControl (SurfaceControl a_control) {
        controls.add(a_control);
    }


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {

        boolean consumed = false;


        int pointerCount = event.getPointerCount();

        for (int p = 0; p < pointerCount; p++) {


            float x1 = event.getX(p);
            float y1 = event.getY(p);


            for (SurfaceControl c : controls) {
                Rect bounds = c.getDrawingRect();
                boolean inside = bounds.contains((int) x1, (int) y1);

                if (inside) {
                    c.propagateEvent(v, event);
                    consumed = true;
                }
            }
        }

        if (!consumed) {
            x = event.getX();
            y = event.getY();
        }

        generic_switch = true;
        return true;
    }


    public boolean get_switch() {
        if (generic_switch==true) {
            generic_switch = false;
            return true;
        } else {
            return false;
        }

    }



    public float[] get_coords() {
        float [] vector = new float[2];
        vector[0] = x;
        vector[1] = y;
        return vector;
    }

    public void saveState(Bundle a_saveState) {
        a_saveState.putFloat("keys.x",x);
        a_saveState.putFloat("keys.y",y);
    }
}
