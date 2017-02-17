package com.libertadorgames.el_libertador;

/**
 * Created by teo on 8/4/2016.
 */
import android.graphics.Bitmap;


/**
 * Created by teo on 10/15/15.
 */
public class Enemy extends Sprite implements HP {

    int hp;

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }


    Enemy( Bitmap img) {
        super(img);

    }

    Enemy(Bitmap[] imgs) {
        super(imgs);

    }

    Enemy(Enemy proto) {
        super(proto);
    }
}
