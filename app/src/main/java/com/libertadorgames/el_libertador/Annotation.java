package com.libertadorgames.el_libertador;

/**
 * Created by teo on 8/4/2016.
 */


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by teo on 5/2/2016.
 */
public class Annotation extends Sprite {

    protected String text_data;
    protected Paint paint;

    Annotation(String text, double scale) {
        text_data = text;
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize((float)(  10.0 * scale));


    }

    public void setText(String text) {
        text_data = text;
    }


    @Override
    public void render(Canvas g) {
        switch (anchor_position) {
            case anchor.CENTER: paint.setTextAlign(Paint.Align.CENTER);
                break;
            case anchor.DOWNLEFT:
            case anchor.UPLEFT:
                paint.setTextAlign(Paint.Align.LEFT);
                break;
            case anchor.DOWNRIGHT:
            case anchor.UPRIGHT:
                paint.setTextAlign(Paint.Align.RIGHT);
                break;

        }
        g.drawText(text_data, x, y, paint);
    }
}
