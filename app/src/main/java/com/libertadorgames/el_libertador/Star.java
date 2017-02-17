package com.libertadorgames.el_libertador;

/**
 * Created by teo on 8/4/2016.
 */

import android.graphics.Bitmap;

/**
 * Created by teo on 7/11/2015.
 */
public class Star extends Sprite {

    int parallax_layer;
    int ht;

    Star(Bitmap image,  int h, int parallax) {
        super(image);
        ht=h;
        parallax_layer=parallax;
    }

    @Override
    public void setposition(int pos_x, int pos_y) {
        if ( pos_y>ht ) {
            pos_y-=ht;
        }

        super.setposition(pos_x, pos_y);
    }

    @Override
    public void pos_relative(int dx, int dy) {
        dx*=parallax_layer;
        dy*=parallax_layer;

        super.pos_relative(dx, dy);
    }

    public void scroll(int dy) {
        pos_relative(0,dy);
    }


}
