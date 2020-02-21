package com.efarrar.cof_soundpool;

/*
URL: https://youtu.be/fIWPSni7kUk
Blog: https://codinginflow.com/tutorials/android/soundpool


Video that goes over the basics of configuring a SoundPool
 */

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //VARIABLES
    private SoundPool soundPool;        //var for soundPool
    private int sound1, sound2, sound3, sound4, sound5, sound6;     //variables for sound files, are int because they are the id of the sound files.
    //private int sound3StreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Creating the soundPool.
        Using an if/else statement to cover the method used in Android 21 and >. else use the old
        way in Android 20 and below.

        For setUsage and SetContentType using sonification is best for playing sounds or beeps
        Ctrl + B to review notes on these methods
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            //configuring SoundPool
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)           //means we can play all 6 sounds at once.
                    .setAudioAttributes(audioAttributes)
                    .build();

        }else {
            //maxStream = number of sound files, AudioManager.STREAM_MUSIC allows us to stream to connected devices, srcQuality doesn't have an affect so set it to 0
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        //loading the sounds; priority set to 1 is future proofing...look this up
        sound1 = soundPool.load(this, R.raw.sound1, 1);
        sound2 = soundPool.load(this, R.raw.sound2, 1);
        sound3 = soundPool.load(this, R.raw.sound3, 1);
        sound4 = soundPool.load(this, R.raw.sound4, 1);
        sound5 = soundPool.load(this, R.raw.sound5, 1);
        sound6 = soundPool.load(this, R.raw.sound6, 1);
    }

    //creating the method that is called when when we click the buttons, we pass in a view to determine what button is clicked
    public void playSound(View v) {
        /*
        - Switch statement uses the view id as the para. We check the id and then play the sound.
        - To play the sound we use the soundPool.play()
        - EXAMPLE: soundPool.play(sound1, 1,1,0,0,1);  (leftVolume, rightVolume, priority, loop, rate)
        - We have to set the right and left  volume which is btw the range of 0.0 -> 1.0
        - Priority: What priority in sound will play in the maxStream
        - loop: is how many times we want to loop it.
        - rate: how fast we want to play the sound
         */
        switch (v.getId()){
            case R.id.button_sound1:
                soundPool.play(sound1, 1, 1, 0,0, 1);
                break;
            case R.id.button_sound2:
                soundPool.play(sound2, 1, 1, 0,0, 1);
                break;
            case R.id.button_sound3:
                soundPool.play(sound3, 1, 1, 0,0, 1);
                break;
            case R.id.button_sound4:
                soundPool.play(sound4, 1, 1, 0,0, 1);
                break;
            case R.id.button_sound5:
                soundPool.play(sound5, 1, 1, 0,0, 1);
                break;
            case R.id.button_sound6:
                soundPool.play(sound6, 1, 1, 0,0, 1);
                break;
        }

    }

    //we need to override the onDestroy() to properly clear memory
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
