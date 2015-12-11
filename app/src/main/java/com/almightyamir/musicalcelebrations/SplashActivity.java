package com.almightyamir.musicalcelebrations;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 5000;

    private MediaPlayer mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK);
        mainLayout.setBackgroundResource(R.drawable.pokhara);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);

                mplayer.stop();
                mplayer.release();

                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);

        setContentView(mainLayout);

    }

    public void createSong()
    {
        if(mplayer == null)
            mplayer = MediaPlayer.create(SplashActivity.this, R.raw.kutumba_intro);

        mplayer.setOnPreparedListener(songPListener);
        mplayer.setOnCompletionListener(songCListener);
    }

    private MediaPlayer.OnPreparedListener songPListener = new MediaPlayer.OnPreparedListener()
    {
        @Override
        public void onPrepared(MediaPlayer mp)
        {
            playSong();
        }
    };

    private MediaPlayer.OnCompletionListener songCListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mp)
        {
            playSong();
        }
    };

    public void playSong()
    {
        if (mplayer.isPlaying())
        {
            mplayer.pause();
        }
        if(mplayer.getCurrentPosition() != 1)
        {
            mplayer.seekTo(1);
        }
        mplayer.start();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        createSong();
    }

    /*@Override
    protected void onPause()
    {
        super.onPause();
        deallocateSong();
    }*/

    public void deallocateSong()
    {

        if (mplayer.isPlaying())
        {
            mplayer.stop();
        }

        if (!(mplayer == null))
        {
            mplayer.release();
            mplayer = null;
        }

        songCListener = null;
        songPListener = null;
    }
}
