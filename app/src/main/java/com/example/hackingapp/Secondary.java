package com.example.hackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class Secondary extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private View view;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        view = findViewById(R.id.view);

        view.setOnTouchListener(this);

        gestureDetector = new GestureDetector(this, this);

        _TextToSpeechInitializer.init(this, utteranceProgressListener, _Constants.secondary);
    }

    UtteranceProgressListener utteranceProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {
        }
        @Override
        public void onDone(String utteranceId) {
            //do something after voice is done.
        }
        @Override
        public void onError(String utteranceId) {
        }
    };

    @Override
    protected void onPause() {
        _Constants.stop();
        super.onPause();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
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
                    swipeLeft();
                }
                result = true;
            }
        } else { //Y movement is greater
            if (Math.abs(Ydiff) > _Constants.ABSdiff && Math.abs(vY) > _Constants.Velocity) {
                if (Ydiff > 0) {
                    swipeDown();
                } else {
                    swipeUp();
                }
                result = true;
            }
        }

        return result;
    }

    private void swipeLeft() {

    }

    private void swipeRight() {
        Intent page2 = new Intent(this, Secondary_Page2.class);
        startActivity(page2);
        finish();
        _Constants.stop();

    }

    private void swipeUp() {
        Intent english = new Intent(this, Secondary_English.class);
        startActivity(english);
        _Constants.stop();

    }

    private void swipeDown() {
        Intent malay = new Intent(this, Secondary_Malay.class);
        startActivity(malay);
        _Constants.stop();

    }

}
