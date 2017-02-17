package com.libertadorgames.el_libertador;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by teo on 4/4/2016.
 */
public class MySurfaceView extends SurfaceView {


    private  MainLoop mainLoopThread;
    private SurfaceHolder holder;
    private MySurfaceView v;

    GameEngine engine;
    SceneDirector director;


    public MySurfaceView(Context context) {
        super(context);
        init();



    }

    public MySurfaceView(Context context, AttributeSet attrbs  ) {
        super(context, attrbs);
        init();

    }


    private void init() {
        v= this;
        createHolderCallback();

    }

    private void createHolderCallback() {
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {


            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                mainLoopThread.setRunning(false);

                while (retry) {
                    try {
                        mainLoopThread.interrupt(); //check again if interrupt needs further work
                        mainLoopThread.join();

                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mainLoopThread = new MainLoop();
                mainLoopThread.view = v;
                mainLoopThread.engine = engine;
                mainLoopThread.director = director;
                mainLoopThread.setRunning(true);
                mainLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

    }

/*
    public void packstate(Bundle outState) {

        //initially we should start running our loop
        mainLoopThread.setRunning(false);

        mainLoopThread.interrupt();
        try {
            mainLoopThread.join();
        } catch (InterruptedException e) {

        }
        mainLoopThread.packstate(outState);

    }

    public void restoreState(Bundle savedInstanceState) {
        mainLoopThread.restoreState(savedInstanceState);
    }*/
}
