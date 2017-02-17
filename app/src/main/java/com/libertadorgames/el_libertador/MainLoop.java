package com.libertadorgames.el_libertador;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

/**
 * Created by teo on 8/4/2016.
 */
public class MainLoop extends Thread {

    public static final int FRAME_DELAY = 65;
    private boolean running = false;
    private long cycleTime;


    SceneDirector director;
    GameEngine engine;
    MySurfaceView view;

    public MainLoop() {


    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {

        cycleTime = System.currentTimeMillis();

        engine.setDimensions(view.getWidth(), view.getHeight());
        engine.ini_engine();
        while (running) {



            updateGameState();
            if (!running)
                break;

            updateGUI();
            if (!running)
                break;

            synchFramerate();

        }
        engine.releaseSoundResources();

    }




    private void updateGameState() {
        engine.do_logic();
        director.calc_animations();
    }



    private void updateGUI() {
        Canvas c = null;
        try {
            c = view.getHolder().lockCanvas();
            if ( c == null) {
                return; //if no canvas then we should exit
            }
            synchronized (view.getHolder()) {
                //view.onDraw(c);
                c.drawColor(Color.BLACK);
                director.render_all(c);
            }
        } finally {
            if (c != null) {
                view.getHolder().unlockCanvasAndPost(c);
            }
        }

    }



    private void synchFramerate() {
        cycleTime = cycleTime + FRAME_DELAY;
        long difference = cycleTime - System.currentTimeMillis();
        try {
            Thread.sleep((int ) Math.max(0, difference));
            if (difference<=0){
                cycleTime = System.currentTimeMillis();
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }


}
