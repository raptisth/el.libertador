package com.libertadorgames.el_libertador;

/**
 * Created by teo on 8/4/2016.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;



/**
 * Created by teo on 10/22/15.
 */
public class TransientSprite extends Sprite {

    boolean completed;

    TransientSprite(Sprite o) {
        super(o);
        completed=false;
    }
    TransientSprite(Bitmap img) {
        super(img);
        completed = false;
    }

    TransientSprite(Bitmap[] imgs) {
        super(imgs);
        completed = false;
    }

    @Override
    public void render(Canvas g) {

        if (!completed)
            super.render(g);
    }

    @Override
    protected void calcNextFrame() {
        if (++current_frame >= totalFrames-1){
            completed = true;
        }
    }

    public boolean isCompleted() {
        return completed;
    }

}
