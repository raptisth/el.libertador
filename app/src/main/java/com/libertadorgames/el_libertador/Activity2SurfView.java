package com.libertadorgames.el_libertador;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.Iterator;
import java.util.Set;


public class Activity2SurfView extends Activity {

    MySurfaceView view;
    private GameEngine engine;
    private SceneDirector director;
    private KeyMaster keys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_surf_view);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();


    }


    private void init() {

        view = (MySurfaceView) findViewById(R.id.surfaceview1);
        //initializations for important objects of the Activity
        engine = new GameEngine();
        director = new SceneDirector();
        keys = new KeyMaster();
        view.setOnTouchListener(keys);
        engine.setDirector(director);
        engine.setRecources(view.getResources());
        engine.setContext(view.getContext());
        engine.setKeys(keys);
        view.engine = engine;
        view.director = director;




    }

    @Override
    protected void onStop() {
        super.onStop();
        Bundle a_saveState = new Bundle();
        engine.saveState(a_saveState);
        keys.saveState(a_saveState);
        //to be implemented later due to serious complexity...
        // director.saveState(a_saveState);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPref.edit();

        String key;
        String header = "game.";
        Set<String> keySet = a_saveState.keySet();
        Iterator<String> it = keySet.iterator();

        while (it.hasNext()){
            key = it.next();

            Object o = a_saveState.get(key);
            if (o == null){
                ed.remove(header + key);
            } else if (o instanceof Integer){
                ed.putInt(header + key, (Integer) o);
            } else if (o instanceof Long){
                ed.putLong(header + key, (Long) o);
            } else if (o instanceof Boolean){
                ed.putBoolean(header + key, (Boolean) o);
            } else if (o instanceof CharSequence){
                ed.putString(header + key, ((CharSequence) o).toString());
            }
        }


        ed.commit();
    }
}
