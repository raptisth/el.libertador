package com.libertadorgames.el_libertador;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.Image;


/**
 * Created by vteo on 9/29/15.
 */
public class Sprite {
    protected int totalFrames;
    protected Bitmap frame[];
    protected int x, y;
    protected int old_x, old_y;
    protected int anchor_position;
    protected int current_frame;
    protected  int wd ;protected int ht;
    public String name;

    public static class anchor {
        public static final int CENTER = 0;
        public static final int UPRIGHT = 1;
        public static final int DOWNRIGHT = 2;
        public static final int DOWNLEFT = 3;
        public static final int UPLEFT = 4;
    }

    public int getHeight () {
        return ht;
    }

    public int getWidth () {
        return wd;
    }



    public void setposition(int pos_x, int pos_y) {
        old_x = x; old_y = y;
        x=pos_x; y = pos_y;

    }


    public  void pos_relative(int dx, int dy) {
        setposition(x+dx, y+dy);
    }




    public void render(Canvas g) {
        int actual_x = x ; int actual_y = y;


        if (anchor_position == anchor.CENTER) {
            actual_x = x - wd / 2;
            actual_y = y - ht / 2;
        } else if (anchor_position == anchor.UPRIGHT) {
            actual_x = x - wd;
        } else if (anchor_position == anchor.DOWNRIGHT) {
            actual_x = x - wd;
            actual_y = y - ht;
        } else if (anchor_position == anchor.DOWNLEFT) {
            actual_y = y - ht;
        }
        g.drawBitmap(frame[current_frame], actual_x, actual_y, null);
        calcNextFrame();


    }



    protected void calcNextFrame() {
        if (++current_frame == totalFrames){
            current_frame = 0;
        }
    }

    Sprite() {
        current_frame =0;
        anchor_position = anchor.CENTER; //set the default value in case the user forgets
        x = 0;
        y = 0;
    }

    Sprite( Bitmap img) {

        //**in android images should be loaded from res/drawable



        /*
        //imgtype should be GIF or PNG etc...
        //imgFileName should be the name of the file to be used
        //this function should be used as a prototype creator



        ImageReader reader = (ImageReader) ImageIO.getImageReadersByFormatName(imgtype).next();
        ImageInputStream ciis = null;
        try {
            ciis = ImageIO.createImageInputStream(new File(imgFileName));
            reader.setInput(ciis, false);
            totalFrames = reader.getNumImages(true);
            frame = new Image[totalFrames];
            for (int i=0 ; i<totalFrames; i++) {
                frame[i] = reader.read(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        frame = new Bitmap[1];
        frame[0] = img;
        totalFrames =1;

        current_frame =0;
        anchor_position = anchor.CENTER; //set the default value in case the user forgets
        x = 0;
        y = 0;
        ht = frame[0].getHeight();
        wd = frame[0].getWidth();


    }

    Sprite (Bitmap[] imgs) {

        //in android we must get the images from res/drawable

        /*
        //2nd constructor for frames seperated in different files
        //provided in a array of Strings
        //frames seperated in different files

        frame = new Image[totalFrames];

        for (int i=0; i< totalFrames ; i++) {
            ImageReader reader = (ImageReader) ImageIO.getImageReadersByFormatName(imgtype).next();
            ImageInputStream ciis = null;
            try {
                ciis = ImageIO.createImageInputStream(new File(imgFileNames[i]));
                reader.setInput(ciis, false);
                frame[i] = reader.read(0); //read only one frame per imagefile


            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/
        totalFrames = imgs.length;
        frame = new Bitmap[totalFrames];
        for (int i =0 ; i< totalFrames ; i++) {
            frame[i] = imgs[i];
        }

        current_frame =0;
        anchor_position = anchor.CENTER; //set the default value in case the user forgets
        ht = frame[0].getHeight();
        wd = frame[0].getWidth();
        x = 0;
        y = 0;
    }



    Sprite (Sprite other) {
        //other is the prototype object that this sprite should base its instance
        current_frame = other.current_frame;
        totalFrames = other.totalFrames;
        frame = other.frame; //copy the pointer of the frames so the 2 different sprites use the same resources for drawing
        x = other.x; y=other.y;
        old_x = other.old_x; old_y=other.old_y;
        anchor_position = other.anchor_position;
        current_frame = other.current_frame;
        wd = other.wd ;  ht = other.ht;


    }


}