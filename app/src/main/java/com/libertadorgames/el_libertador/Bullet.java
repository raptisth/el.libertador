package com.libertadorgames.el_libertador;

/**
 * Created by teo on 8/4/2016.
 */

import android.graphics.Bitmap;

/**
 * Created by teo on 10/15/15.
 */
public class Bullet extends Sprite implements HP {

    private boolean friendly;
    private int hp;


    Bullet(Bitmap img) {
        super(img);
    }

    Bullet(Bitmap [] imgs) {
        super(imgs);
    }

    Bullet(Bullet other) {
        super(other);
        friendly = true;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean fr) {
        friendly = fr;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }
}
