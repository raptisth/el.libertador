package com.libertadorgames.el_libertador;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by teo on 21/5/2016.
 */
public class SurfaceButton extends Sprite implements SurfaceControl {


    protected boolean sticky;
    protected boolean pressed;
    View.OnTouchListener clickListener;

    public SurfaceButton(Bitmap bitmap) {
        super  (bitmap);
        pressed = false;
        sticky = false;
    }

    public SurfaceButton(Bitmap[] bitmaps) {
        super (bitmaps );
        pressed = false;
        sticky = false;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public void setClickListener(View.OnTouchListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public Rect getDrawingRect() {

        int left = x, top = y, right, bottom;

        if (anchor_position == anchor.CENTER) {
            left = x - wd / 2;
            top  = y - ht / 2;
        } else if (anchor_position == anchor.UPRIGHT) {
            left = x - wd;
        } else if (anchor_position == anchor.DOWNRIGHT) {
            left = x - wd;
            top = y - ht;
        } else if (anchor_position == anchor.DOWNLEFT) {
            top = y - ht;
        }

        right = wd + left;
        bottom = ht + top;
        Rect r = new Rect(left, top, right, bottom);

        return r;

    }

    @Override
    public void propagateEvent(View v, MotionEvent event) {

        event.getX();
        event.getY();
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            if (!sticky) {pressed = true;} else {pressed = !pressed;};
            clickListener.onTouch(v,event);
        }

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
            if (!sticky) pressed = false;
            clickListener.onTouch(v,event);
        }

    }




    protected void calcNextFrame() {
        current_frame = (pressed)? 1:0;
    }




}
