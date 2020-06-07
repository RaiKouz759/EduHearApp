package com.example.hackingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private static final String TAG = "MainActivity";
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 123;
    Intent speechIntent;

    //widgets
    private View view;
    private ImageView imageViewLogo;

    //variables
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);
        imageViewLogo = findViewById(R.id.imageViewLogo);

        view.setOnTouchListener(this);

        //fade logo
        crossfade();

        gestureDetector = new GestureDetector(this, this);

        //_TextToSpeechInitializer.init(this, utteranceProgressListener, _Constants.mainActivity);
        //delay 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _TextToSpeechInitializer.init(getApplicationContext(), utteranceProgressListener, _Constants.mainActivity);
            }
        }, 2000);
    }

    //tts
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //lvList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, matches));
            matches.replaceAll(String::toUpperCase);  //change build.gradle and project structure
            if(matches.contains("PRIMARY")){
                //replace with your intent to primary class
                Intent up = new Intent(this, Primary.class);
                startActivity(up);
                //   _Constants.stop();

            }
            else if(matches.contains("SECONDARY")){
                //replace with your intent to secondary class
                Intent right = new Intent(this, Secondary.class);
                startActivity(right);
            }
            else if(matches.contains("VOCATIONAL") || matches.contains("SKILLS")) {
                //replace with your intent to vocational class
                Intent left = new Intent(this, Vocational.class);
                startActivity(left);
            }
            else {
                _TextToSpeechInitializer.init(this, utteranceProgressListener,"Please repeat your command.");
            }
        }
        else{
            _TextToSpeechInitializer.init(this, utteranceProgressListener,"Please repeat your command.");
        }
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

        /*int action = motionEvent.getAction();

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(TAG,"Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(TAG,"Action was MOVE");
                Log.d(TAG, "onTouch: (x,y) (" +motionEvent.getX() + ", " + motionEvent.getY() + ")");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(motionEvent);
        }*/

        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    //Gesture Detector
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG,"onDown: called");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG,"onShowPress: called");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG,"onSingleTapUp: called");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG,"onScroll: called");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
                Log.d(TAG,"onLongPress: called");
                _Constants.stop();
                startVoiceRecognition();
    }

    public void startVoiceRecognition(){


        speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please say something");
        startActivityForResult(speechIntent, VOICE_RECOGNITION_REQUEST_CODE);

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float vX, float vY) {
        Log.d(TAG,"onFling: called");
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
        Intent left = new Intent(this, Vocational.class);
        startActivity(left);
        _Constants.stop();

    }

    private void swipeRight() {
        Intent right = new Intent(this, Secondary.class);
        startActivity(right);
        _Constants.stop();

    }

    private void swipeUp() {
        //Toast.makeText(this,"swipeUp", Toast.LENGTH_SHORT).show();
        Intent up = new Intent(this, Primary.class);
        startActivity(up);
        _Constants.stop();
    }

    private void swipeDown() {
        //Toast.makeText(this,"swipeDown", Toast.LENGTH_SHORT).show();
        finish();
        _Constants.stop();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void crossfade() {

        // Set the content view to 100% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        imageViewLogo.setAlpha(1f);
        imageViewLogo.setVisibility(View.VISIBLE);

        // Animate the content view to 0% opacity, and clear any animation
        // listener set on the view.
        imageViewLogo.animate()
                .alpha(0f)
                .setDuration(3000)
                .setListener(null);
    }
}