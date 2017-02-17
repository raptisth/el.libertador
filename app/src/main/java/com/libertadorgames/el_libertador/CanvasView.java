package com.libertadorgames.el_libertador;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by teo on 4/4/2016.
 */
public class CanvasView extends View {


    private Context context;
    private Bitmap mBitmap;
    float new_x, new_y;

    public  CanvasView(Context c) {
        super(c);
        context = c;
        new_x=5;
        new_y=5;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

    }

    public  CanvasView(Context c, AttributeSet attrbs) {
        super(c, attrbs);
        context = c;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        // your Canvas will draw onto the defined Bitmap

    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawBitmap(mBitmap, new_x-mBitmap.getWidth()/2,new_y-mBitmap.getHeight()/2,null);




    }

    public void clearCanvas() {

        invalidate();

    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {

        new_x = event.getX();

        new_y = event.getY();
        invalidate();
        return true;

    }



}
