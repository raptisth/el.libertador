package com.libertadorgames.el_libertador;

/**
 * Created by teo on 23/4/2016.
 */
public class Ship extends Sprite implements HP {

    private int hp =0 ;

    public Ship(Sprite spaceship_proto) {
        super(spaceship_proto);
        hp = 0 ;
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
