package com.almightyamir.musicalcelebrations;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements View.OnClickListener{

    MediaPlayer anthemSong;

    Button btnAnthem, btnMangal, btnDiwali, btnFolk, btnStop, btnMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.CENTER);
        mainLayout.setBackgroundResource(R.drawable.nepal_flag);

        btnAnthem = new Button(this);
        btnAnthem.setBackgroundColor(Color.BLUE);
        btnAnthem.setText("Click here to hear Nepali National Anthem");
        btnAnthem.setTextSize(25);
        btnAnthem.setTypeface(null, Typeface.BOLD);
        btnAnthem.setPadding(5, 5, 5, 5);
        mainLayout.addView(btnAnthem);

        btnAnthem.setOnClickListener(this);

        btnMangal = new Button(this);
        btnMangal.setBackgroundColor(Color.BLUE);
        btnMangal.setText("Click here to hear Mangal Dhun");
        btnMangal.setTextSize(25);
        btnMangal.setTypeface(null, Typeface.BOLD);
        btnMangal.setPadding(5, 5, 5, 5);
        mainLayout.addView(btnMangal);

        btnMangal.setOnClickListener(this);

        btnDiwali = new Button(this);
        btnDiwali.setBackgroundColor(Color.BLUE);
        btnDiwali.setText("Click here to hear Diwali Music");
        btnDiwali.setTextSize(25);
        btnDiwali.setTypeface(null, Typeface.BOLD);
        btnDiwali.setPadding(5, 5, 5, 5);
        mainLayout.addView(btnDiwali);

        btnDiwali.setOnClickListener(this);

        btnFolk = new Button(this);
        btnFolk.setBackgroundColor(Color.BLUE);
        btnFolk.setText("Click here to hear Folk Music");
        btnFolk.setTextSize(25);
        btnFolk.setTypeface(null, Typeface.BOLD);
        btnFolk.setPadding(5, 5, 5, 5);
        mainLayout.addView(btnFolk);

        btnFolk.setOnClickListener(this);

        /*LinearLayout controlLayout = new LinearLayout(this);
        controlLayout.setOrientation(LinearLayout.HORIZONTAL);
        mainLayout.setGravity(Gravity.CENTER);
        mainLayout.addView(controlLayout);

        btnStop = new Button(this);
        btnStop.setBackgroundColor(Color.LTGRAY);
        btnStop.setLayoutParams(new LinearLayout.LayoutParams(10, 500));
        btnStop.setText("STOP");
        btnStop.setTextSize(20);
        btnStop.setTypeface(null, Typeface.BOLD);
        btnStop.setPadding(5, 5, 5, 5);
        controlLayout.addView(btnStop);

        btnStop.setOnClickListener(this);

        btnMute = new Button(this);
        btnMute.setBackgroundColor(Color.LTGRAY);
        btnMute.setLayoutParams(new LinearLayout.LayoutParams(10, 500));
        btnMute.setText("MUTE");
        btnMute.setTextSize(20);
        btnMute.setTypeface(null, Typeface.BOLD);
        btnMute.setPadding(5, 5, 5, 5);
        controlLayout.addView(btnMute);

        btnMute.setOnClickListener(this);*/

        setContentView(mainLayout);
    }

    @Override
    public void onClick(View v) {

        Log.e("onClick", "Started");

        Button b = (Button)v;
        String buttontext = b.getText().toString();

        Log.e("ButtonText", ""+buttontext);

        if(anthemSong == null)
            anthemSong = MediaPlayer.create(getApplicationContext(), R.raw.national_anthem);

        anthemSong.setOnPreparedListener(songPListener);
        anthemSong.setOnCompletionListener(songCListener);

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
        Log.e("playSong", "Started");

        if (anthemSong.isPlaying())
        {
            anthemSong.stop();
            anthemSong.release();
            Log.e("playSong", "Stopped");
        }
        if(anthemSong.getCurrentPosition() != 1)
        {
            anthemSong.seekTo(1);
            Log.e("playSong", "Seek");
        }
        anthemSong.start();
    }
}
