package com.example.hackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class Vocational_Page2 extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private View view;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocational__page2);

        view = findViewById(R.id.view);

        view.setOnTouchListener(this);

        gestureDetector = new GestureDetector(this, this);

        _TextToSpeechInitializer.init(this, utteranceProgressListener, _Constants.vocational2);
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
        Intent page1 = new Intent(this, Vocational.class);
        startActivity(page1);
        finish();
        _Constants.stop();

    }

    private void swipeRight() {

    }

    private void swipeUp() {
        Intent computerSystems = new Intent(this, ComputerSystems.class);
        startActivity(computerSystems);
        _Constants.stop();

    }

    private void swipeDown() {
        Intent reflexology = new Intent(this, Reflexology.class);
        startActivity(reflexology);
        _Constants.stop();

    }

}
