package com.libertadorgames.el_libertador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    //private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

        Button b= (Button) findViewById(R.id.callbutton1);
        b.bringToFront();

        TextView t = (TextView) findViewById(R.id.textView);
        t.bringToFront();


    }




    public void buttonclicked_func(View v) {

        Intent intent = new Intent(this, Activity2SurfView.class);
        startActivity( intent);

    }





}
