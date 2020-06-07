package com.example.hackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class Secondary_Page3 extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private View view;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary__page3);

        view = findViewById(R.id.view);

        view.setOnTouchListener(this);

        gestureDetector = new GestureDetector(this, this);

        _TextToSpeechInitializer.init(this, utteranceProgressListener, _Constants.secondary3);
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
        Intent page2 = new Intent(this, Secondary_Page2.class);
        startActivity(page2);
        finish();
        _Constants.stop();

    }

    private void swipeRight() {

    }

    private void swipeUp() {
        Intent religiousStudies = new Intent(this, Secondary_ReligiousStudies.class);
        startActivity(religiousStudies);
        _Constants.stop();

    }

    private void swipeDown() {
        Intent moralStudies = new Intent(this, Secondary_MoralStudies.class);
        startActivity(moralStudies);
        _Constants.stop();

    }

}
