package com.libertadorgames.el_libertador;

/**
 * Created by teo on 8/4/2016.
 */

import android.content.Context;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;



/**
 * Created by teo on 10/15/15.
 */
public class GameEngine {

    /* A class to incorporate all the game logic, and decouple gamelogic from
        other functions like rendering etc...
    * */
    static final int new_moves_cooldown = 25;
    static final int guncooldown = 10;
    static final int ship_hp_max = 5;
    static final int message_countdown = 50;
    static final int max_v=15;
    static final int max_lives = 3;
    static final int proto_width = 696;

    double scale;


    SceneDirector director;
    KeyMaster keys;
    int n = 10;


    int new_moves_cooldown_c;
    int lives;
    int guncooldown_c;
    int score;
    int tot_bullets=0;

    int message_countdown_c;
    int lvl=0;
    int sub_lvl=0;


    float vx=(float) 0.0;
    float vy = (float) 0.0;



    Sprite spaceship_proto;
    Enemy enemy_proto;
    Enemy enemy_proto_lvl2;
    Enemy enemy_proto_lvl3;
    Enemy enemy_proto_lvl4;
    Bullet bullet_proto;
    Bullet enemy_bullet_proto;
    Bullet bullet_proto_1 ;
    Bullet bullet_proto_2 ;
    Bullet bullet_proto_3 ;
    Bullet bullet_proto_4 ;
    Sprite win_proto;
    Sprite los_proto;
    TransientSprite explode_proto;
    TransientSprite mini_explode_proto;
    SoundTrack music_soundtrack;
    int depthcharge_effect;
    int explosion1_effect;
    int explosion2_effect;
    int explosion3_effect;
    int gunshot_effect;
    int hit1_effect;
    int laser1_effect;
    int laser2_effect;
    int laser3_effect;
    int lasermachinegun_effect;
    int loose_effect;
    int powerup_effect;
    int scifi004_effect;
    int shot1_effect;
    int wawa_effect;


    final int weapon_dual = 1;
    final int weapon_triple =2;
    final int weapon_laz_single = 3;
    final int weapon_laz_dual = 4;

    final int powerup_life = 5;
    final int powerup_hp = 6;



    Annotation infotext;
    private Resources myres;
    private int view_width;
    private int view_height;
    private Enemy enemy_proto_lvl15;
    private Enemy enemy_proto_lvl16;
    private Enemy enemy_proto_lvl5;
    private Enemy enemy_proto_lvl6;
    private Enemy enemy_proto_lvl7;
    private Enemy enemy_proto_lvl8;
    private Context context;
    private Boolean initialized = false;
    private Enemy enemy_proto_lvl17;
    private Enemy enemy_proto_lvl18;
    private Enemy enemy_proto_lvl19;
    private Enemy enemy_proto_lvl20;
    private Enemy enemy_proto_lvl21;
    private Enemy enemy_proto_lvl22;
    private Enemy enemy_proto_lvl23;

    private Sprite power1_proto;
    private Sprite power2_proto;
    private Sprite power3_proto;
    private Sprite power4_proto;
    private Sprite power5_proto;
    private Sprite power6_proto;

    private BulletStorage bulletstorage;
    private Sprite button101;
    private Sprite button102;

    boolean fire_swich;
    int active_weapon = 0;
    private SoundEffectMixer mixer;


    public void ini_engine() {

        //audio resources always require initialization even if already
        // initialized because they are released
        //upon thread interruption
        // shot_effect = new SoundTrack(R.raw.scifi004,context);
        music_soundtrack = new SoundTrack(
                R.raw.music_creative_commons_podington_bear_electronic_chrome_muffler, context);
        music_soundtrack.play();
        mixer = new SoundEffectMixer(context,10);

        depthcharge_effect = mixer.addEffect(R.raw.depthcharge);
        explosion1_effect = mixer.addEffect(R.raw.explosion1);
        explosion2_effect = mixer.addEffect(R.raw.explosion2);
        explosion3_effect = mixer.addEffect(R.raw.explosion3);
        gunshot_effect = mixer.addEffect(R.raw.gunshot);
        hit1_effect = mixer.addEffect(R.raw.hit1);
        laser1_effect = mixer.addEffect(R.raw.laser1);
        laser2_effect = mixer.addEffect(R.raw.laser2);
        laser3_effect = mixer.addEffect(R.raw.laser3);
        lasermachinegun_effect = mixer.addEffect(R.raw.lasermachinegun);
        loose_effect = mixer.addEffect(R.raw.loose);
        powerup_effect = mixer.addEffect(R.raw.powerup);
        scifi004_effect = mixer.addEffect(R.raw.scifi004);
        shot1_effect = mixer.addEffect(R.raw.shot1);
        wawa_effect = mixer.addEffect(R.raw.wawa);


        if (initialized)
            return; //if previously initialized no need to do it again.

        scale = 1.4*view_width / proto_width;



        infotext = new Annotation("",scale);
        infotext.anchor_position = Sprite.anchor.CENTER;
        infotext.setposition(view_width /2, (int) (5*scale)+5);
        director.add_Annotation(infotext);

        Bitmap mbit =getBitmap(R.drawable.spaceship_blue);

        spaceship_proto   = new Sprite(mbit);
        Bitmap mbit1 =                 getBitmap(R.drawable.l0_spaceship0011);
        Bitmap mbit2 =                getBitmap(R.drawable.l0_spaceship0012);
        Bitmap mbit3 =               getBitmap(R.drawable.l0_spaceship0013);
        Bitmap mbit4 =               getBitmap(R.drawable.l0_spaceship0014);
        Bitmap mbit5 =               getBitmap(R.drawable.l0_spaceship0015);
        Bitmap mbit6 =               getBitmap(R.drawable.l0_spaceship0016);

        Bitmap [] mbit_enemy = {mbit1, mbit2, mbit3, mbit4, mbit5, mbit6};



        enemy_proto   = new Enemy(mbit_enemy);
        enemy_proto_lvl3   = new Enemy(getBitmap(R.drawable.spaceshipdrakir1));
        enemy_proto_lvl2   = new Enemy(getBitmap(R.drawable.spaceshipdrakir2));
        enemy_proto_lvl4   = new Enemy(getBitmap(R.drawable.spaceshipdrakir3));
        enemy_proto_lvl5   = new Enemy(getBitmap(R.drawable.spaceshipdrakir4));
        enemy_proto_lvl6   = new Enemy(getBitmap(R.drawable.spaceshipdrakir5));
        enemy_proto_lvl7   = new Enemy(getBitmap(R.drawable.spaceshipdrakir6));
        enemy_proto_lvl8   = new Enemy(getBitmap(R.drawable.spaceshipdrakir7));


        Bitmap spritesheet1 = BitmapFactory.decodeResource(getResources(), R.drawable.bugs_invaders);

        Bitmap inv1 = grabfromSpriteSheet(spritesheet1, 8, 6, 22, 22);
        Bitmap inv2 = grabfromSpriteSheet(spritesheet1, 8, 29, 22, 22);
        Bitmap [] inv = { inv1, inv1, inv1, inv2, inv2, inv2};

        enemy_proto_lvl15 = new Enemy(inv);

         inv1 = grabfromSpriteSheet(spritesheet1, 6, 56, 22, 22);
         inv2 = grabfromSpriteSheet(spritesheet1, 6, 55, 22, 22);
        Bitmap [] inv01 = { inv1, inv1, inv1, inv2, inv2, inv2};

        enemy_proto_lvl16 = new Enemy(inv01);

        inv1 = grabfromSpriteSheet(spritesheet1, 33, 6, 22, 22);
        inv2 = grabfromSpriteSheet(spritesheet1, 33, 29, 22, 22);
        Bitmap [] inv02 = { inv1, inv1, inv1, inv2, inv2, inv2};
        enemy_proto_lvl17 = new Enemy(inv02);

        inv1 = grabfromSpriteSheet(spritesheet1, 85, 7, 22, 22);
        inv2 = grabfromSpriteSheet(spritesheet1, 85, 28, 22, 22);
        Bitmap [] inv03 = { inv1, inv1, inv1, inv2, inv2, inv2};
        enemy_proto_lvl18 = new Enemy(inv03);

        inv1 = grabfromSpriteSheet(spritesheet1, 32, 56, 22, 22);
        inv2 = grabfromSpriteSheet(spritesheet1, 32, 78, 22, 22);
        Bitmap [] inv04 = { inv1, inv1, inv1, inv2, inv2, inv2};
        enemy_proto_lvl19 = new Enemy(inv04);

        inv1 = grabfromSpriteSheet(spritesheet1, 57, 8, 22, 22);
        inv2 = grabfromSpriteSheet(spritesheet1, 56, 27, 22, 22);
        Bitmap [] inv05 = { inv1, inv1, inv1, inv2, inv2, inv2};
        enemy_proto_lvl20 = new Enemy(inv05);

        inv1 = grabfromSpriteSheet(spritesheet1, 59, 55, 22, 22);
        inv2 = grabfromSpriteSheet(spritesheet1, 59, 77, 22, 22);
        Bitmap [] inv06 = { inv1, inv1, inv1, inv2, inv2, inv2};
        enemy_proto_lvl21 = new Enemy(inv06);

        inv1 = grabfromSpriteSheet(spritesheet1, 59, 55, 22, 22);
        inv2 = grabfromSpriteSheet(spritesheet1, 59, 77, 22, 22);
        Bitmap [] inv07 = { inv1, inv1, inv1, inv2, inv2, inv2};
        enemy_proto_lvl22 = new Enemy(inv07);



        Bitmap spritesheet2 = BitmapFactory.decodeResource(getResources(), R.drawable.m48_bullets);
        Bitmap bul22 = grabfromSpriteSheet(spritesheet2, 217, 111, 13, 13);
        Bitmap bul11 = grabfromSpriteSheet(spritesheet2, 11, 12, 10, 10);
        Bitmap bul12 = grabfromSpriteSheet(spritesheet2, 21, 12, 10, 10);
        Bitmap bul13 = grabfromSpriteSheet(spritesheet2, 32, 12, 10, 10);
        Bitmap bul14 = grabfromSpriteSheet(spritesheet2, 43, 12, 10, 10);
        Bitmap[] bul_v = {bul11,bul12,bul13,bul14};
        Bitmap Bul21 = grabfromSpriteSheet(spritesheet2, 412, 8, 15, 24);
        Bitmap Bul22 = grabfromSpriteSheet(spritesheet2, 272, 300, 22, 22);
        Bitmap Bul23 = grabfromSpriteSheet(spritesheet2, 128, 152, 14, 14);
        Bitmap Bul24 = grabfromSpriteSheet(spritesheet2, 465, 214, 12, 17);
        Bitmap Bul25 = grabfromSpriteSheet(spritesheet2, 465, 252, 12, 21);
        Bitmap [] Bul24_5 = {Bul24, Bul24 ,Bul25 , Bul25};


        enemy_bullet_proto  = new Bullet(bul22);
        bullet_proto_1 = new Bullet( Bul21 );
        bullet_proto_2 = new Bullet( Bul22) ;
        bullet_proto_3 = new Bullet( Bul23) ;
        bullet_proto_4 = new Bullet( Bul24_5) ;
        bullet_proto = new Bullet (bul_v);


        win_proto = new Sprite( getBitmap( R.drawable.you_win));
        los_proto = new Sprite( getBitmap( R.drawable.static_gameover));


        Bitmap spritesheet3 = BitmapFactory.decodeResource(getResources(), R.drawable.explosion17);
        int[] e_x = {   1, 65, 129, 193, 256  };
        int [] e_y = {    1, 65, 129, 193, 256};
        Bitmap[] ex_b = new Bitmap[25];
        for (int i=0; i<5;i++) {
            for (int j=0; j<5; j++) {
                ex_b[j*5+i] = grabfromSpriteSheet(spritesheet3, e_x[i], e_y[j], 64, 64);
            }
        }
        explode_proto =  new TransientSprite(ex_b);




        //explode_proto = new TransientSprite(BitmapFactory.decodeResource(
        //                      getResources(), R.drawable.explode));

        Bitmap[] mini_b = {ex_b[0], ex_b[1]};
        mini_explode_proto = new TransientSprite( mini_b );


        Bitmap spritesheet4 = BitmapFactory.decodeResource(getResources(), R.drawable.powerups);
        //life
        Bitmap power3_proto_bt = grabfromSpriteSheet(spritesheet4, 1, 1, 39, 39);
        //circle
        Bitmap power4_proto_bt = grabfromSpriteSheet(spritesheet4, 80, 40, 39, 39);
        power3_proto = new Sprite(power3_proto_bt);
        power4_proto = new Sprite(power4_proto_bt);

        Bitmap spritesheet4_1 = BitmapFactory.decodeResource(
                                    getResources(), R.drawable.powerups_color);
        Bitmap power1_proto_bt = grabfromSpriteSheet(spritesheet4_1, 1, 1, 39, 39);
        Bitmap power2_proto_bt = grabfromSpriteSheet(spritesheet4_1, 40, 1, 39, 39);
        Bitmap power5_proto_bt = grabfromSpriteSheet(spritesheet4_1, 80, 1, 39, 39);
        Bitmap power6_proto_bt = grabfromSpriteSheet(spritesheet4_1, 1, 40, 39, 39);
        power1_proto = new Sprite(power1_proto_bt);
        power2_proto = new Sprite(power2_proto_bt);
        power5_proto = new Sprite(power5_proto_bt);
        power6_proto = new Sprite(power6_proto_bt);


         button101 = new Sprite (getBitmap(R.drawable.wire) );
         button102 = new Sprite( getBitmap(R.drawable.missile) );

        Bitmap b1 = BitmapFactory.decodeResource(
                                    getResources(),  R.drawable.bigbackground );
        Bitmap b2 = Bitmap.createScaledBitmap(b1,(int ) (b1.getWidth()*scale/1.4),
                                    (int) (b1.getHeight()*scale/1.4),false);

        director.add_background(  b2 );
        Sprite back = director.get_Background(0);
        Bitmap img_star = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_star);
        for (int i = 0 ; i<5; i++)    {
            for (int j=0;j<10;j++) {
                Star st = new Star(img_star,view_height,i+1);
                st.setposition( (int)(view_width * Math.random()),(int) (view_height* Math.random()));
                director.add_background( st );
            }
        }
        back.setposition( (int) (view_width/2.00),(int) (view_height/2.00));
        score = 0;
        lives = max_lives;
        lvl++;
        sub_lvl++;
        bulletstorage = new BulletStorage();
        initialized = true;

        Bitmap spritesheet5 = BitmapFactory.decodeResource(getResources(), R.drawable.arcade_red);



        Bitmap but1_b1 = grabfromSpriteSheet(spritesheet5,1,1,49,49);
        Bitmap but1_b2 = grabfromSpriteSheet(spritesheet5,57,1,49,49);
        Bitmap [] but1_b_red = {but1_b1 , but1_b2};
        SurfaceButton but1 = new SurfaceButton (  but1_b_red  );
        but1.anchor_position  = Sprite.anchor.DOWNLEFT;
        but1.setposition(1,view_height);


        /*SurfaceButton but1_2 = new SurfaceButton (  but1_b_red );
        but1_2.anchor_position  = Sprite.anchor.DOWNLEFT;
        but1_2.setposition(1,view_height-but1.getHeight());*/


        Bitmap spritesheet6 = BitmapFactory.decodeResource(getResources(), R.drawable.arcade_green);
        Bitmap but2_b1 = grabfromSpriteSheet(spritesheet6, 1, 1, 49, 49);
        Bitmap but2_b2 = grabfromSpriteSheet(spritesheet6,57, 1, 49, 49);
        Bitmap [] b2__b_green = {but2_b1, but2_b2};
        SurfaceButton but2 = new SurfaceButton( b2__b_green );
        but2.anchor_position  = Sprite.anchor.DOWNRIGHT;
        but2.setposition(view_width, view_height);

        Bitmap spritesheet7 = BitmapFactory.decodeResource(getResources(), R.drawable.arcade_yellow);
        Bitmap but3_b1 = grabfromSpriteSheet(spritesheet7, 1, 1, 49, 49);
        Bitmap but3_b2 = grabfromSpriteSheet(spritesheet7,57, 1, 49, 49);
        Bitmap [] b3__b_yellow = {but3_b1, but3_b2};
        SurfaceButton but3 = new SurfaceButton( b3__b_yellow );
        but3.anchor_position  = Sprite.anchor.DOWNRIGHT;
        but3.setposition(view_width, view_height - but2.getHeight());

        Bitmap spritesheet8 = BitmapFactory.decodeResource(getResources(), R.drawable.arcade_blue);
        Bitmap but4_b1 = grabfromSpriteSheet(spritesheet8, 1, 1, 49, 49);
        Bitmap but4_b2 = grabfromSpriteSheet(spritesheet8,57, 1, 49, 49);
        Bitmap [] b4__b_yellow = {but4_b1, but4_b2};
        SurfaceButton but4 = new SurfaceButton( b4__b_yellow );
        but4.anchor_position  = Sprite.anchor.DOWNRIGHT;
        but4.setposition(view_width, view_height - 2*but3.getHeight());

        Bitmap spritesheet9 = BitmapFactory.decodeResource(getResources(), R.drawable.arcade_orange);
        Bitmap but5_b1 = grabfromSpriteSheet(spritesheet9, 1, 1, 49, 49);
        Bitmap but5_b2 = grabfromSpriteSheet(spritesheet9,57, 1, 49, 49);
        Bitmap [] b5__b_yellow = {but5_b1, but5_b2};
        SurfaceButton but5 = new SurfaceButton( b5__b_yellow);
        but5.anchor_position  = Sprite.anchor.DOWNRIGHT;
        but5.setposition(view_width, view_height - 3*but4.getHeight());


        //first add keys to keymaster
        keys.addControl(but1);
        keys.addControl(but2);
        keys.addControl(but3);
        keys.addControl(but4);
        keys.addControl(but5);
      //  keys.addControl(but1_2);
        //then add keys to director for rendering
        director.add_background(but1);
        director.add_background(but2);
        director.add_background(but3);
        director.add_background(but4);
        director.add_background(but5);
     //   director.add_background(but1_2);

        but1.setSticky(true);

        but1.setClickListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
                    fire_swich = !fire_swich;
                return true;
            }

        });

      /*  but1_2.setClickListener( new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
                {
                    bulletstorage.push(weapon_dual,50);
                    bulletstorage.push(weapon_triple,50);
                    bulletstorage.push(weapon_laz_single,50);
                    bulletstorage.push(weapon_laz_dual,50);

                }
                return true;


            }
        });*/

        but3.setClickListener( new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
                    active_weapon=1;
                return true;
            }


        });
        but4.setClickListener( new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
                    active_weapon=2;
                return true;
            }


        });

        but5.setClickListener( new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
                    active_weapon=4;
                return true;
            }


        });


        but2.setClickListener( new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
                    active_weapon=3;
                return true;
            }


        });






        createNewStage();

    }



    protected Bitmap getBitmap(int res_id) {
        Bitmap mbit = BitmapFactory.decodeResource(getResources(), res_id);
        Bitmap mbit2 = Bitmap.createScaledBitmap(
                mbit,(int ) (mbit.getWidth()*scale),(int) (mbit.getHeight()*scale),false);
        return mbit2;
    }

    protected Bitmap grabfromSpriteSheet(Bitmap spritesheet, int x, int y, int w, int h) {
        Bitmap inv1 = Bitmap.createBitmap(spritesheet, x, y, w, h);
        Bitmap inv2 = Bitmap.createScaledBitmap(
                inv1,(int) (inv1.getWidth() * scale), (int) (inv1.getHeight() * scale), false);
        return inv2;
    }


    public void setRecources(Resources res) {
        myres = res;
    }

    public Resources getResources() {
        return myres;
    }

    private void createNewStage() {

        Sprite m_sprite = director.getbyName("spaceship");
        if (m_sprite==null) {
            Ship spaceship   = new Ship(spaceship_proto);
            spaceship.setHp(ship_hp_max);
            spaceship.setposition(view_width / 2, view_height * 4 / 5);
            keys.setX(view_width / 2);
            keys.setY(view_height*4/5);

            director.add_sprite(spaceship,"spaceship");
        }
        //director.add_sprite(new Bullet (bullet_proto),"bullet");

        for (int i=0; i<n+sub_lvl; i++) {
            Enemy proto1;
            if (lvl==1) {
                proto1 = enemy_proto_lvl15;

            }
            else if (lvl==2) {

                proto1 = enemy_proto_lvl16;

            } else if (lvl==3) {
                proto1 = enemy_proto_lvl17;

            } else if (lvl==4) {
                proto1 = (enemy_proto_lvl18);
            } else if (lvl==5) {
                proto1 = (enemy_proto_lvl19);
            } else if (lvl==6) {
                proto1 = (enemy_proto_lvl20);
            } else if (lvl==7) {
                proto1 = (enemy_proto_lvl21);
            } else if (lvl==5) {
                proto1 = (enemy_proto_lvl22);
            }else {
                proto1 = (enemy_proto_lvl7);
            }

            spawnEnemy(proto1,lvl,"enemy"+i);


        }

        //bosses
        if (sub_lvl>=5)
        {
            if (lvl==2) {
                spawnEnemy(enemy_proto_lvl3,lvl*10,"enemy101");

            }else if (lvl==1) {
                spawnEnemy(enemy_proto_lvl2, lvl * 10, "enemy101");

            } else if (lvl==3) {
                spawnEnemy(enemy_proto_lvl4, lvl * 10, "enemy101");
                spawnEnemy(enemy_proto_lvl4, lvl * 10, "enemy101");

            } else if (lvl==4) {
                spawnEnemy(enemy_proto_lvl5, lvl * 10, "enemy101");
            } else if (lvl==5) {
                spawnEnemy(enemy_proto_lvl5, lvl * 10, "enemy101");
                spawnEnemy(enemy_proto_lvl5, lvl * 10, "enemy102");
                spawnEnemy(enemy_proto_lvl5, lvl * 10, "enemy103");

            } else if (lvl>5) {
                spawnEnemy(enemy_proto_lvl8,lvl*30,"enemy101");
                spawnEnemy(enemy_proto_lvl8,lvl*30,"enemy102");
            }
        }





        new_moves_cooldown_c = new_moves_cooldown ;
        guncooldown_c = guncooldown;


        message_countdown_c = message_countdown;


    }

    private void spawnEnemy(Enemy prototype, int hitpoints, String name) {
        Enemy o = new Enemy(prototype);
        double r_d= Math.random() - 0.5;
        o.x=(int)(view_width*(0.5+r_d*2));
        o.y=-30;
        o.setHp(hitpoints);
        director.add_sprite(o,name);
        new_enemy_Move(o);
    }

    private void new_enemy_Move(Sprite o) {

        /*float phase = (float)Math.random()*100;
        int x0[] = new int[360];
        int y0[] = new int[360];
        int s0[] = new int[360];
        for (int t=0; t<360; t+=1){
            float x1 = 200*(float)Math.cos(3.14*t+phase)*t+400;
            float y1 = 100*(float)Math.sin(2*t+phase)*t+300;
            x0[t] = (int) x1;
            y0[t] = (int) y1;
            s0[t] = 20;
        }*/

        int x0[] = {o.x,
                (int ) ((Math.random()*view_width)),
                (int ) ((Math.random()*view_width)),
                (int ) ((Math.random()*view_width)),};
        int y0[] = {o.y,
                (int ) (Math.random()*view_height/3+50),
                (int ) (Math.random()*view_height/3+50),
                (int ) ((Math.random()*view_height/3)),};
        int s0[] = {(int ) (Math.random()*10+90-lvl),
                (int ) (Math.random()*10+90-lvl),
                (int ) (Math.random()*1000+50),
                (int ) (Math.random()*100+50),
                (int ) (Math.random()*10+50),};

        Interpolator aa3 = new Interpolator(x0,y0,s0);
        director.add_animation(o, aa3);
    }


    public void setDirector(SceneDirector director_o) {
        director = director_o;
    }

    public void setKeys(KeyMaster key_o) {
        keys = key_o;
    }

    public void do_logic() {

        guncooldown_c++;
        new_moves_cooldown_c--;
        Ship spaceship = (Ship) director.getbyName("spaceship");
        int ship_hp1=0;
        if (spaceship!=null) ship_hp1 = spaceship.getHp();

        String it1 = "Stage " +lvl+ "     HP "
                + ship_hp1+ "     Lives "
                + lives + "     Score " + score +" weapon "
                +bulletstorage.getBulletCount( active_weapon )/* +director.reportSprites()*/;
        infotext.setText(it1);

        if (EvaluateLoss()) {


            playLoss();
            message_countdown_c--;

           if (keys.get_switch() && message_countdown_c<=0) {
                director.destroy_all_sprites();
                lives--;
                if (lives ==0)
                {
                    score = 0;
                    lives = max_lives;
                    setlvl(1);
                    sub_lvl = 1;
                    bulletstorage = new BulletStorage();
                }
                createNewStage();
            }

            return;
        }

        if (EvaluateVictory())
        {
            playVictory();
            message_countdown_c--;

            if (keys.get_switch() && message_countdown_c<=0) {
                director.destroy_all_sprites();
                lvl++;
                sub_lvl=1;
                createNewStage();
            }
            return;


        }


        if (EvaluateSublevel())
        {

            message_countdown_c--;

            if ( message_countdown_c<=0 ) {

                sub_lvl++;
                createNewStage();
            }



        }

        Sprite m_sprite = moveShip();

        trytoFire(m_sprite);

        perform_Collitions1();  //bullet enemies collitions
        perform_Collitions2();  //enemy ship collitions
        perform_Collitions3(); //ship with powerups
        cleanup_Bullets();

        get_new_moves_for_enemies();

        for (int i = 1; i<51 ; i++) {
            Star a = (Star) director.background.get(i);
            a.scroll(2);
        }


    }



    private void playLoss() {

        Sprite o1 = director.getbyName("spaceship");
        if (o1 != null) {
            //Sprite o1 = director.getbyName("spaceship");
            director.destroySprite(o1);
            TransientSprite explotion = new TransientSprite(explode_proto);
            explotion.x = (int)(o1.x+ (-0.5+Math.random())*(double)o1.getWidth());
            explotion.y = (int)(o1.y+ (-0.5+Math.random())*(double)o1.getHeight());
            director.add_sprite(explotion);
            los_proto.x = view_width/2;
            los_proto.y = view_height/2;
            if (lives<=1) {
                director.add_sprite(los_proto, "loss");
                mixer.playSound(loose_effect);
            }
        }


    }

    private boolean EvaluateVictory() {
        int enemies =0;
        for (int i=0; i<director.Sprites.size(); i++) {
            if ( (director.get_Sprite(i) instanceof Enemy)
                    || (director.get_Sprite(i) instanceof PowerUp) ) {
                enemies ++ ;
            }
        }
        if (sub_lvl>=5 && enemies<=0) {
            return true;
        }
        else return false;
    }

    private boolean EvaluateSublevel() {
        int enemies =0;
        for (int i=0; i<director.Sprites.size(); i++) {
            if (director.get_Sprite(i) instanceof Enemy )  {
                enemies ++ ;
            }
        }
        if (sub_lvl<5 && enemies<=0) {
            return true;
        }
        else return false;
    }

    private void playVictory() {

        Sprite o = director.getbyName("victory");
        if (o == null) {
            win_proto.x = view_width/2;
            win_proto.y = view_height/2;
            director.add_sprite(win_proto, "victory");
            mixer.playSound(wawa_effect);
        }
    }

    private boolean EvaluateLoss() {
        Ship spaceship = (Ship) director.getbyName("spaceship");
        if (spaceship==null) return true;
        if (spaceship.getHp()<=0 ) {

            return true;
        }
        else
            return false;
    }

    private void get_new_moves_for_enemies() {
        if (new_moves_cooldown_c == 0)
        {
           int shot_not_fired = 0;

            for (int j1 =0 ; j1 < director.Sprites.size() ; j1++)
                if (director.get_Sprite(j1) instanceof Enemy) {
                    Sprite o = director.get_Sprite(j1);
                    double chance = Math.random();
                    if (0<= chance && chance< 0.1) {
                        new_attack_Move(o);
                    } else if (0.1<= chance && chance< 0.9 && shot_not_fired<lvl+1) {
                        new_Enemy_Shot(o);
                        shot_not_fired ++;
                    }
                    else
                        new_enemy_Move(o);
                }

            new_moves_cooldown_c = new_moves_cooldown;
        }


    }

    private void new_Enemy_Shot(Sprite o) {
        Sprite ship = director.getbyName("spaceship");

        double r_d = Math.random()-0.5;

        int x1 = o.x;
        int y1 = o.y;
        int x2 = ship.x + (int)(r_d*ship.getWidth()*1.5);
        int y2 = ship.y + (int)(r_d*ship.getHeight()*1.5);
        int x3 = x1+1000*(x2-x1);
        int y3 = y1+1000*(y2-y1);



        int x0[] = {x1,x3 };
        int y0[] = {y1,y3 };
        int s0[] = { 35000, 700};

        Interpolator aa3 = new Interpolator(x0,y0,s0);
        Bullet b = new Bullet(enemy_bullet_proto);
        b.setposition(x1,y1);
        b.setHp(1); //enemy shots 1 dmg
        director.add_sprite(b);
        b.setFriendly(false);
        director.add_animation(b, aa3);

        mixer.playSound(laser2_effect);


    }

    private void new_attack_Move(Sprite o) {
        Sprite ship = director.getbyName("spaceship");



        int x0[] = {o.x,o.x,
                (int ) ((Math.random()*view_width)),
                (int ) ((Math.random()*view_width)),};
        int y0[] = {o.y,ship.y+104,
                (int ) (Math.random()*view_height/3+100),
                (int ) ((Math.random()*view_height/2)),};
        int s0[] = {(int ) (Math.random()*10+60-4*lvl),
                (int ) (Math.random()*10+60-4*lvl),
                (int ) (Math.random()*50+85),(int ) (Math.random()*100+85),
                (int ) (Math.random()*10+25),};

        Interpolator aa3 = new Interpolator(x0,y0,s0);
        director.add_animation(o, aa3);

    }

    private Sprite moveShip() {

        Sprite m_sprite = director.getbyName("spaceship");

        double r_scale = 7.0*scale;
        float d_scale = 100;
        float[] coords = keys.get_coords();
        float distance_x = coords[0] - m_sprite.x;
        float distance_y = coords[1] - m_sprite.y;
        float distance = (float) Math.sqrt( distance_x*distance_x + distance_y*distance_y );
        float speed = 0;
        if (distance>3)
            speed = (float) (r_scale * (1.00- Math.pow(10,-distance/d_scale)));
        else
            speed = 0;

        vx = speed * (distance_x / distance);
        vy = speed * (distance_y / distance);


        if (m_sprite.x<10 && vx<0)
            vx=-vx;
        if (m_sprite.x>view_width-10 && vx>0)
            vx=-vx;
        if (m_sprite.y<20 && vy<0)
            vy=-vy;
        if (m_sprite.y>view_height-20 && vy >0)
            vy=-vy;


        m_sprite.pos_relative((int) vx,(int)vy);
       // Sprite back = director.background.get(0);
        //back.pos_relative((int)(-vx/6),(int)(-vy/8));
        for (int i = 1; i<50 ; i++) {
            Star a = (Star) director.background.get(i);
            a.pos_relative((int) (-vx/5), (int) (-vy/5));
        }
        return m_sprite;
    }

    private void trytoFire(Sprite m_sprite) {
       if ( guncooldown_c>=0 && fire_swich) {


           switch ( active_weapon) {

               case weapon_dual :
                   if (bulletstorage.fire(weapon_dual)) {weapon_sys_dual(m_sprite);} ;
                   break;
               case weapon_triple:
                   if (bulletstorage.fire(weapon_triple)) {weapon_sys_triple(m_sprite);} ;
                   break;
               case weapon_laz_single:
                   if (bulletstorage.fire(weapon_laz_single)) {weapon_sys_laser(m_sprite);} ;
                   break;
               case weapon_laz_dual:
                   if (bulletstorage.fire(weapon_laz_dual)) {weapon_sys_laser_double(m_sprite);} ;
                   break;
           }

           weapon_sys_single(m_sprite);


            tot_bullets++;
            guncooldown_c=-guncooldown ;


            //shot_effect.play();



        }
    }

    private void weapon_sys_single(Sprite m_sprite) {
        Bullet bat = new Bullet(bullet_proto_4);
        bat.setposition(m_sprite.x,m_sprite.y-40);
        director.add_sprite(bat,"shot");
        bat.setHp(1);

        int x1[] = {m_sprite.x,m_sprite.x};
        int y1[] = {m_sprite.y-40,m_sprite.y-view_height*2};
        int s[] = { 50,50 };
        Interpolator i1 = new Interpolator(x1,y1,s);
        //bullets.add(bat);
        director.add_animation(bat, i1);
        mixer.playSound(laser2_effect);
    }


    private void weapon_sys_laser(Sprite m_sprite) {

        Bullet bat1 = new Bullet(bullet_proto_1);
        Bullet bat2 = new Bullet(bullet_proto_1);
        bat1.setposition(m_sprite.x-25,m_sprite.y-40 );
        bat2.setposition(m_sprite.x+25,m_sprite.y-40 );
        director.add_sprite(bat1, "shot");
        director.add_sprite(bat2, "shot");

        bat1.setHp(4);
        bat2.setHp(4);

        double d=0;
        int x1[] = {m_sprite.x-25, m_sprite.x-25,m_sprite.x+(int)(d-25)};
        int x2[] = {m_sprite.x+25, m_sprite.x+25,m_sprite.x+(int)(d+25)};
        int y1[] = {m_sprite.y-40,m_sprite.y-120,m_sprite.y-view_height*2};
        int y2[] = {m_sprite.y-40,m_sprite.y-120,m_sprite.y-view_height*2};

        int s[] = { 4,50,50 };
        Interpolator i1 = new Interpolator(x1,y1,s);
        Interpolator i2 = new Interpolator(x2,y2,s);
        //bullets.add(bat);
        director.add_animation(bat1, i1);
        director.add_animation(bat2, i2);
        mixer.playSound(laser2_effect);


    }

    private void weapon_sys_laser_double(Sprite m_sprite) {
        Bullet bat1 = new Bullet(bullet_proto_2);
        Bullet bat2 = new Bullet(bullet_proto_2);
        bat1.setposition(m_sprite.x-25,m_sprite.y );
        bat2.setposition(m_sprite.x+25,m_sprite.y );
        director.add_sprite(bat1, "shot");
        director.add_sprite(bat2, "shot");
        bat1.setHp(4);
        bat2.setHp(4);


        int x1[] = {m_sprite.x-25,m_sprite.x-40, m_sprite.x-70, m_sprite.x-60, m_sprite.x-50, m_sprite.x-40,m_sprite.x-40+ (int)((Math.random()-0.5)*view_width)};
        int x2[] = {m_sprite.x+25,m_sprite.x+40, m_sprite.x+70, m_sprite.x+60, m_sprite.x+50, m_sprite.x+40,m_sprite.x+40+(int)((Math.random()-0.5)*view_width)};
        int y1[] = {m_sprite.y,m_sprite.y-20,m_sprite.y-50,m_sprite.y-90, m_sprite.y-130, m_sprite.y-150,m_sprite.y-view_height*2};
        int y2[] = {m_sprite.y,m_sprite.y-20,m_sprite.y-50,m_sprite.y-90, m_sprite.y-130, m_sprite.y-150,m_sprite.y-view_height*2};

        int s[] = { 5,5,5,5,50,50,50,50,50,50 };
        Interpolator i1 = new Interpolator(x1,y1,s);
        Interpolator i2 = new Interpolator(x2,y2,s);
        //bullets.add(bat);
        director.add_animation(bat1, i1);
        director.add_animation(bat2, i2);
        mixer.playSound(laser3_effect);
    }


    private void weapon_sys_dual(Sprite m_sprite) {



        Bullet bat1 = new Bullet(bullet_proto);
        Bullet bat2 = new Bullet(bullet_proto);
        bat1.setposition(m_sprite.x-25,m_sprite.y );
        bat2.setposition(m_sprite.x+25,m_sprite.y );
        director.add_sprite(bat1, "shot");
        director.add_sprite(bat2, "shot");
        bat1.setHp(1);
        bat2.setHp(1);

        double d=(Math.random()-1)*200;
        int x1[] = {m_sprite.x-25, m_sprite.x-view_width};
        int x2[] = {m_sprite.x+25, m_sprite.x+view_width};
        int y1[] = {m_sprite.y,m_sprite.y};
        int y2[] = {m_sprite.y,m_sprite.y};

        int s[] = { 50,50 };
        Interpolator i1 = new Interpolator(x1,y1,s);
        Interpolator i2 = new Interpolator(x2,y2,s);
        //bullets.add(bat);
        director.add_animation(bat1, i1);
        director.add_animation(bat2, i2);
        mixer.playSound(shot1_effect);
    }

    private void weapon_sys_triple(Sprite m_sprite) {
        Bullet bat1 = new Bullet(bullet_proto_3);
        Bullet bat2 = new Bullet(bullet_proto_3);
        Bullet bat3 = new Bullet(bullet_proto_3);
        Bullet bat4 = new Bullet(bullet_proto_3);
        bat1.setposition(m_sprite.x-50,m_sprite.y-20 );
        bat2.setposition(m_sprite.x-25,m_sprite.y-20 );
        bat3.setposition(m_sprite.x+25,m_sprite.y-20 );
        bat4.setposition(m_sprite.x+50,m_sprite.y-20 );
        director.add_sprite(bat1,"shot");
        director.add_sprite(bat2,"shot");
        director.add_sprite(bat3,"shot");
        director.add_sprite(bat4, "shot");
        bat1.setHp(1);
        bat2.setHp(1);
        bat3.setHp(1);
        bat4.setHp(1);



        int x0[] = {m_sprite.x-50, m_sprite.x-50,m_sprite.x+(int)((Math.random()-0.5)*view_width-50)};
        int x1[] = {m_sprite.x-25, m_sprite.x-25,m_sprite.x+(int)((Math.random()-0.5)*view_width-25)};
        int x2[] = {m_sprite.x+25, m_sprite.x+25,m_sprite.x+(int)((Math.random()-0.5)*view_width+25)};
        int x3[] = {m_sprite.x+50, m_sprite.x+50,m_sprite.x+(int)((Math.random()-0.5)*view_width+50)};
        int y1[] = {m_sprite.y-20,m_sprite.y-120,m_sprite.y-view_height*2};
        int y2[] = {m_sprite.y-20,m_sprite.y-100,m_sprite.y-view_height*2};
        int y0[] = {m_sprite.y-20,m_sprite.y-120,m_sprite.y-view_height*2};
        int y3[] = {m_sprite.y-20,m_sprite.y-140,m_sprite.y-view_height*2};

        int s[] = { 4,50,50 };
        Interpolator i1 = new Interpolator(x1,y1,s);
        Interpolator i2 = new Interpolator(x2,y2,s);
        Interpolator i3 = new Interpolator(x0,y0,s);
        Interpolator i4 = new Interpolator(x3,y3,s);
        //bullets.add(bat);
        director.add_animation(bat1, i1);
        director.add_animation(bat2, i2);
        director.add_animation(bat3, i3);
        director.add_animation(bat4, i4);
        mixer.playSound(shot1_effect);
    }

    private void cleanup_Bullets() {
        for (int i=0; i< director.Sprites.size(); i++)
        {
            if (director.get_Sprite(i)  instanceof Bullet) {
                Sprite bullet = director.get_Sprite(i);
                if (((bullet.y < -5) || (bullet.y > view_height + 5) ) ||
                        ((bullet.x<-5) || (bullet.x>view_width + 5)  ) ) {
                    director.destroySprite(bullet);
                    tot_bullets--;
                }
            } else if (director.get_Sprite(i)  instanceof TransientSprite) {
                TransientSprite explosion = (TransientSprite) director.get_Sprite(i);
                if (explosion.isCompleted())
                    director.destroySprite(explosion);
            } else if ( director.get_Sprite(i) instanceof PowerUp) {
                Sprite p = director.get_Sprite(i);
                if ( (p.x < -p.getWidth()) || (p.x > view_width + p.getWidth()) ||
                        (p.y < -p.getHeight()) || (p.y > view_height + p.getHeight())) {
                    director.destroySprite(p);
                }

            }
        }


    }

    private void perform_Collitions1() {
        for (int i1 =0; i1<director.Sprites.size();i1++) {
            if (director.get_Sprite(i1) instanceof Bullet) {
                Bullet bullet = (Bullet) director.Sprites.get(i1);
                if (bullet.isFriendly()) {


                for (int j1 = 0; j1 < director.Sprites.size(); j1++)
                if (director.get_Sprite(j1) instanceof Enemy) {

                    Enemy enemy = (Enemy) director.Sprites.get(j1);

                    double w = (0.8)*(double)(bullet.getWidth() + enemy.getWidth())/2.00;
                    double y = (0.8)*(double)(bullet.getHeight() + enemy.getHeight())/2.00;

                    if ((Math.abs(bullet.x - enemy.x) < w) && (Math.abs(bullet.y - enemy.y) < y)) {
                        //collission
                        int v[]  = resolveCollission(bullet,enemy);
                        
                        
                        if (v[0]<=0) {

                            director.destroySprite(bullet);
                            tot_bullets--;
                            
                        }
                        
                        enemy.setHp(v[1]);
                        
                        if (v[1] <= 0) {
                            TransientSprite explotion = new TransientSprite(explode_proto);
                            explotion.x = enemy.x;
                            explotion.y = enemy.y;
                            spawnPowerup(enemy);
                            director.destroySprite(enemy);
                            director.add_sprite(explotion);
                            score += 10;
                            mixer.playSound(explosion3_effect);
                        } else {
                            TransientSprite explotion = new TransientSprite(mini_explode_proto);
                            explotion.x = bullet.x;
                            explotion.y = bullet.y;

                            director.add_sprite(explotion);
                            mixer.playSound(hit1_effect);
                        }

                        break;
                    }
                }
            }
                else {
                    Ship m_sprite = (Ship) director.getbyName("spaceship");

                    double w = (0.8)*(double)(bullet.getWidth() + m_sprite.getWidth())/2.00;
                    double y = (0.8)*(double)(bullet.getHeight() + m_sprite.getHeight())/2.00;
                    if ((Math.abs(bullet.x - m_sprite.x) < w) && (Math.abs(bullet.y - m_sprite.y) < y)) {
                        //collission

                        int [] v = resolveCollission(m_sprite,bullet);

                        if (v[1]<=0) {

                            director.destroySprite(bullet);
                            mixer.playSound(hit1_effect);
                        }
                        m_sprite.setHp(v[0]);

                        TransientSprite explotion = new TransientSprite(mini_explode_proto);
                        explotion.x = bullet.x;
                        explotion.y = bullet.y;

                        director.add_sprite(explotion);
                        mixer.playSound(explosion3_effect);





                    }
                }
            }
        }


    }

    private void spawnPowerup(Enemy enemy) {

        double chance = Math.random();
        PowerUp powerUp1;

        if (enemy.name.equalsIgnoreCase("Enemy101") || enemy.name.equalsIgnoreCase("Enemy102") ||
        enemy.name.equalsIgnoreCase("Enemy103") )  {
            for (int i =0; i<3; i++) {
                Sprite pp_proto = power6_proto;
                switch (i+1) {
                    case weapon_dual: pp_proto = power5_proto; break;
                    case weapon_triple: pp_proto = power2_proto; break;
                    case weapon_laz_single: pp_proto = power6_proto; break;
                    case weapon_laz_dual: pp_proto = power1_proto; break;

                }
                powerUp1  = new PowerUp(pp_proto,i+1,30);
                double pos = Math.random()-0.5;
                powerUp1.x = (int) (enemy.x + enemy.getWidth()*pos);
                powerUp1.y = (int) (enemy.y+ enemy.getHeight()*pos);
                int [] x0 = {powerUp1.x,powerUp1.x,powerUp1.x};
                int [] y0 = {powerUp1.y,powerUp1.y+200, powerUp1.y+2*view_height};
                int [] s = {50,100,800};
                Interpolator i1 = new Interpolator(x0,y0,s);
                director.add_sprite(powerUp1);
                director.add_animation(powerUp1,i1);
            }
            return;
        }


        if (chance> (0.4* lvl/10)) {
            return;
        }


        chance = Math.random();

        if (chance<0.1) {
            powerUp1  = new PowerUp(power3_proto,powerup_life,1);
        } else if (chance>=0.1 && chance <0.2) {
            powerUp1  = new PowerUp(power4_proto,powerup_hp,2);
        } else if (chance>=0.2 && chance <0.4) {
            powerUp1  = new PowerUp(power5_proto,weapon_dual,20);
        } else if (chance>=0.4 && chance <0.7) {
            powerUp1  = new PowerUp(power2_proto,weapon_triple,20);
        } else if (chance>=0.7 && chance <0.9) {
            powerUp1  = new PowerUp(power6_proto,weapon_laz_single,30);
        } else {
            powerUp1  = new PowerUp(power1_proto,weapon_laz_dual,30);
        }
        powerUp1.x = enemy.x;
        powerUp1.y = enemy.y;
        int [] x0 = {powerUp1.x,powerUp1.x,powerUp1.x};
        int [] y0 = {powerUp1.y,powerUp1.y+200, powerUp1.y+2*view_height};
        int [] s = {50,100,800};
        Interpolator i = new Interpolator(x0,y0,s);
        director.add_sprite(powerUp1);
        director.add_animation(powerUp1,i);



    }

    private int[] resolveCollission(HP a, HP b) {


        int [] v = new int[2];

        if ( a.getHp() > b.getHp() ) {
            int diff = a.getHp() - b.getHp();
            v[0] = diff;
            v[1] = 0;
        }

        else {
            int diff = b.getHp() - a.getHp();
            v[1] = diff;
            v[0] = 0;

        }

        return v;


    }

    private void perform_Collitions2() {
        Ship ship = (Ship ) director.getbyName("spaceship");

        for (int j1 =0 ; j1 < director.Sprites.size() ; j1++)
            if (director.get_Sprite(j1) instanceof Enemy) {

                Enemy enemy  = (Enemy) director.Sprites.get(j1);

                double w = (0.8)*(double)(ship.getWidth() + enemy.getWidth())/2.00;
                double y = (0.8)*(double)(ship.getHeight() + enemy.getHeight())/2.00;

                if ((Math.abs(ship.x - enemy.x)<60) && (Math.abs(ship.y - enemy.y)<60)) {
                    //collission
                    int [] v = resolveCollission(ship,enemy);

                        //the enemy looses always
                        director.destroySprite(enemy);
                        TransientSprite explotion = new TransientSprite(explode_proto);
                        explotion.x = enemy.x;
                        explotion.y = enemy.y;
                        director.add_sprite(explotion);


                    ship.setHp(v[0]);


                    mixer.playSound(explosion3_effect);



                    break;
                }
            }

    }


    private void perform_Collitions3() {
        Ship ship = (Ship ) director.getbyName("spaceship");

        for (int j1 =0 ; j1 < director.Sprites.size() ; j1++)
            if (director.get_Sprite(j1) instanceof PowerUp) {

                PowerUp powerup  = (PowerUp) director.Sprites.get(j1);
                if ((Math.abs(ship.x - powerup.x)<60) && (Math.abs(ship.y - powerup.y)<60)) {
                    //collission


                    resolvepowerup(ship, powerup);


                    director.destroySprite(powerup);
                    mixer.playSound(powerup_effect);


                    break;
                }
            }

    }

    private void resolvepowerup(Ship ship, PowerUp powerup) {

        if (powerup.type == powerup_hp) {
            int h = ship.getHp();
            ship.setHp(h + powerup.amount);
        } else if (powerup.type==powerup_life) {
            lives++;
        } else  {
            //all other are bullets
            bulletstorage.push(powerup.type,powerup.amount);
        }

    }

    public void setDimensions(int width, int height) {
        view_width = width;
        view_height = height;
    }

    public void setlvl(int level) {
        lvl = level;
    }


    public void setContext(Context context) {
        this.context = context;
    }



    public void releaseSoundResources() {

        mixer.cleanUp();
        music_soundtrack.release();


    }

    public void saveState(Bundle a_saveState) {
        a_saveState.putInt("engine.lvl", lvl);
        a_saveState.putInt("engine.lives", lives);
    }



}
