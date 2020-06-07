package com.example.hackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Player2 extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {


    private GestureDetector gestureDetector;

    private View viewLeft, viewRight, viewCenter;
    private TextView textView;

    String chapterNumber = "Chapter 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);

        viewLeft = findViewById(R.id.viewLeft);
        viewRight = findViewById(R.id.viewRight);
        viewCenter = findViewById(R.id.viewCenter);

        textView = findViewById(R.id.textView);
        textView.setText(chapterNumber);

        gestureDetector = new GestureDetector(this, this);

        viewLeft.setOnTouchListener(this);
        viewRight.setOnTouchListener(this);
        viewCenter.setOnTouchListener(this);
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

                } else {
                    swipeLeft();
                }
                result = true;
            }
        } else { //Y movement is greater
            if (Math.abs(Ydiff) > _Constants.ABSdiff && Math.abs(vY) > _Constants.Velocity) {
                if (Ydiff > 0) {

                } else {

                }
                result = true;
            }
        }

        return result;
    }

    private void swipeLeft () {
        Intent left = new Intent(this, Player.class);
        startActivity(left);
        finish();
    }

}
