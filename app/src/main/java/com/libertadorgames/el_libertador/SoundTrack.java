package com.libertadorgames.el_libertador;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by teo on 16/4/2016.
 */
public class SoundTrack {


        private MediaPlayer clip;

        SoundTrack(int soundFileName, Context context) {


                    clip = MediaPlayer.create(context, soundFileName);
                    clip.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    clip.setLooping(true);


        }


        public void play() {

            clip.start();

        }


    public void release() {

        if (  clip.isPlaying()    ) clip.stop();
        clip.release();
        clip = null;

    }
}
