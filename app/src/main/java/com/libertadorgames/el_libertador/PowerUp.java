package com.libertadorgames.el_libertador;

/**
 * Created by teo on 23/4/2016.
 */
public class PowerUp extends Sprite {

    int type;
    int amount;

    public PowerUp (Sprite proto, int powertype, int amo) {

        super(proto);

        type = powertype;
        amount = amo;
    }
}
