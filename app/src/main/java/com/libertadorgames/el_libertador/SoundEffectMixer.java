package com.libertadorgames.el_libertador;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by teo on 7/6/2016.
 */
public class SoundEffectMixer {

    private SoundPool soundpool;
    Context context;




    public SoundEffectMixer(Context context, int max) {
        this.context = context;
        int mAudioStreamType = AudioManager.STREAM_MUSIC;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            soundpool = new SoundPool.Builder()
                    .setMaxStreams(max)
                    .setAudioAttributes(
                            new AudioAttributes.Builder()
                                    .setLegacyStreamType(mAudioStreamType)
                                    .setContentType(
                                            AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                    .build())
                    .build();
        } else {
            soundpool = new SoundPool(max, mAudioStreamType, 0);
        }




    }


    public int addEffect(int resource) {

        int r;
        r = soundpool.load(context, resource, 1);
        return r;
    }

    public final void playSound(int sound) {

            AudioManager mgr = (AudioManager)context.getSystemService(
                    Context.AUDIO_SERVICE);
            float streamVolumeCurrent =
                    mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
            float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float volume = streamVolumeCurrent / streamVolumeMax;
            soundpool.play(sound, volume, volume, 1, 0, 1f);

    }

    public final void cleanUp() {

        context = null;
        soundpool.release();
        soundpool = null;
    }
}
