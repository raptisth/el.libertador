package com.libertadorgames.el_libertador;

import android.graphics.Bitmap;
import android.graphics.Canvas;


import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by teo on 8/4/2016.
 */
public class SceneDirector {


    protected SpriteGroup Sprites;
    protected SpriteGroup background;
    protected  HashMap<String, Sprite> hsmap;
    protected  HashMap<Sprite,Interpolator> animations;
    protected  SpriteGroup Annotations;

    //protected HashMap<String, SpriteGroup> groups;


    public void add_animation(Sprite sp, Interpolator i) {
        animations.put(sp, i);
    }

    public void calc_animations() {
        Iterator<Sprite>  i = animations.keySet().iterator();
        while (i.hasNext()) {
            Sprite sp = i.next();
            Interpolator ip = animations.get(sp);
            if (ip.isCompleted()) {
                animations.remove(sp);
                return;
            }
            int p[] = animations.get(sp).next();
            sp.setposition(p[0],p[1]);
        }

    }

    public void destroy_all_sprites() {
        Sprites.clear();
        hsmap.clear();
        animations.clear();
    }

    public void destroySprite( Sprite o) {
        Boolean a;

        a = Sprites.remove(o);
        animations.remove(o);
        hsmap.remove(o.name);




    }

    public String reportSprites() {
        int total = Sprites.size();
        int bullets =0;
        int enemies =0;
        int transients = 0;
        int other =0;
        for (int i=0;i<total;i++) {
            Sprite o = get_Sprite(i);
            if (o instanceof TransientSprite) {
                transients++;
            } else if (o instanceof Enemy) {
                enemies++;
            } else if (o instanceof Bullet) {
                bullets++;
            } else {
                other++;
            }
        }

        String output = "Total: "+ total;
        output += " Bullets" + bullets;
        output += " Enemies" + enemies;
        output += " Transients" + transients;
        output += " Other" + other;

        return output;






    }

    public void add_Annotation (Annotation o) {

        Annotations.add(o);
    }


    public void add_background( Bitmap img){
        Sprite a = new Sprite(img);
        a.anchor_position = Sprite.anchor.CENTER;
        background.add(a);

    }

    public void add_background(  Sprite o ){

        //o.anchor_position = Sprite.anchor.CENTER;
        background.add(o);


    }

    public Sprite add_sprite(Sprite a) {

        Sprites.add(a);
        return  a;
    }


    public void render_all (Canvas g) {
        background.render(g);
        Sprites.render(g);
        Annotations.render(g);


    }

    SceneDirector() {
        Sprites = new SpriteGroup();
        background = new SpriteGroup();
        hsmap = new HashMap<String, Sprite>();
        animations = new HashMap<Sprite, Interpolator>();
        Annotations = new SpriteGroup();
        //  groups = new HashMap<String, SpriteGroup>();
    }


    public Sprite get_Sprite(int index) {
        return Sprites.get(index);
    }


    public Sprite get_Background(int index) {
        return background.get(index);
    }

    public Sprite get_Annotation(int index) { return Annotations.get(index); }


    public Sprite getbyName (String name) {
        return hsmap.get(name);

    }

    protected void tag (String name, Sprite sp) {


        hsmap.put(name, sp);
        sp.name = name;

    }

    public Sprite add_sprite(Sprite a, String name) {
        Sprite p = add_sprite(a);
        tag(name,p);
        return p;

    }



}
