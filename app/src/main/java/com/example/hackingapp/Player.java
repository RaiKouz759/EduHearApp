package com.example.hackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Player extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private static int timeSkip = 10000; //10 seconds

    //SharedPreferences
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    //mediaPlayer
    private MediaPlayer mediaPlayer;

    private GestureDetector gestureDetector;

    private View viewLeft, viewRight, viewCenter;
    private TextView textView;

    int LorR;
    int playPause;
    int center;
    int time;

    String chapterNumber = "Chapter 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // this is the view we will add the gesture detector to
        viewLeft = findViewById(R.id.viewLeft);
        viewRight = findViewById(R.id.viewRight);
        viewCenter = findViewById(R.id.viewCenter);

        textView = findViewById(R.id.textView);
        textView.setText(chapterNumber);

        //mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mPreferences = getSharedPreferences("player", MODE_PRIVATE);
        mEditor = mPreferences.edit();

        mediaPlayer = MediaPlayer.create(this, R.raw.faded);
        time = mPreferences.getInt("timestamp", 0);
        mediaPlayer.seekTo(time);
        mediaPlayer.start();
        playPause = 1;

        // get the gesture detector
        gestureDetector = new GestureDetector(this, this);

        // Add a touch listener to the view
        // The touch listener passes all its events on to the gesture detector
        viewLeft.setOnTouchListener(this);
        viewRight.setOnTouchListener(this);
        viewCenter.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.viewCenter) {
                    gestureDetector.onTouchEvent(event);
                    center = 1;
                } else {

                }
                return true;
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (view.getId() == R.id.viewLeft) {
            gestureDetector.onTouchEvent(motionEvent);
            LorR = 1;
            //Toast.makeText(this, "doubleTap", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (view.getId() == R.id.viewRight) {
            gestureDetector.onTouchEvent(motionEvent);
            LorR = 2;
            //Toast.makeText(this, "doubleTapRight", Toast.LENGTH_SHORT).show();
            return true;
        }
        /*if (view.getId() == R.id.viewCenter) {
            gestureDetector.onTouchEvent(motionEvent);
            center = 1;
            return true;
        }*/
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (LorR == 1) {

            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable._rw);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();

            mediaPlayer.pause();
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - timeSkip);
            mediaPlayer.start();
        } else if (LorR == 2){
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable._ff);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();

            mediaPlayer.pause();
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + timeSkip);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "LorR variable undefined", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        if (center == 1) {
            if (playPause == 1) {
                pause();
            } else if (playPause == 2) {
                play();
            } else {
                Toast.makeText(this, "playPause variable undefined", Toast.LENGTH_SHORT).show();
            }
            center = 0;
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float vX, float vY) {
        boolean result = false;
        float Ydiff = moveEvent.getY() - downEvent.getY();
        float Xdiff = moveEvent.getX() - downEvent.getX();

        if (Math.abs(Xdiff) > Math.abs(Ydiff)) { //X movement is greater
            if (Math.abs(Xdiff) > _Constants.ABSdiff && Math.abs(vX) > _Constants.Velocity) {
                if (Xdiff > 0) {
                    swipeRight();
                } else {
                    //swipeLeft();
                }
                result = true;
            }
        } else { //Y movement is greater
            if (Math.abs(Ydiff) > _Constants.ABSdiff && Math.abs(vY) > _Constants.Velocity) {
                if (Ydiff > 0) {
                    close();
                } else {
                    close();
                }
                result = true;
            }
        }

        return result;
    }

    private void swipeRight () {
        Intent right = new Intent(this, Player2.class);
        startActivity(right);
        pause();
        finish();
    }

    private void close() {
        pause();
        finish();
    }

    @Override
    public void onBackPressed() {
        pause();
        finish();
    }

    private void pause () {
        mediaPlayer.pause();
        playPause = 2;

        Toast toast = new Toast(this);
        ImageView view = new ImageView(this);
        view.setImageResource(R.drawable.pause);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

        //Write to Preferences
        time = mediaPlayer.getCurrentPosition();
        mEditor.putInt("timestamp", time);
        mEditor.commit();
    }

    private void play () {
        //Read from Preferences
        time = mPreferences.getInt("timestamp", 0);
        mediaPlayer.seekTo(time);
        mediaPlayer.start();
        playPause = 1;

        Toast toast = new Toast(this);
        ImageView view = new ImageView(this);
        view.setImageResource(R.drawable.play);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
