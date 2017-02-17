package com.libertadorgames.el_libertador;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by teo on 21/5/2016.
 */
public interface SurfaceControl {


    Rect getDrawingRect();
    void propagateEvent(View v, MotionEvent event);
}
