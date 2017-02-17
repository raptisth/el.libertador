package com.libertadorgames.el_libertador;

/**
 * Created by teo on 8/4/2016.
 */
import android.graphics.Canvas;
import java.util.ArrayList;


/**
 * Created by vteo on 10/2/15.
 * not to be used in multiple hash-maps unless design changes. Has serious flaw when trying to remove items permanently
 */

public class SpriteGroup extends ArrayList<Sprite> {

    public void render( Canvas g ) {

        for (int i =0 ; i < size() ; i++) {
            get(i).render(g);
        }

    }
}
