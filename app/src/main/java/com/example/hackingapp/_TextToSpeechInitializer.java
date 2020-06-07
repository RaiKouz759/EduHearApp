package com.example.hackingapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.Locale;

public class _TextToSpeechInitializer {

    Bundle params;

    static TextToSpeech mytts = null;

    public static void init(Context c, final UtteranceProgressListener utteranceProgressListener, final String xyz){
        mytts = new TextToSpeech(c, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = mytts.setLanguage(Locale.US);
                    speak(xyz);
                    mytts.setOnUtteranceProgressListener(utteranceProgressListener);
                    if(result == TextToSpeech.LANG_NOT_SUPPORTED ||
                            result == TextToSpeech.LANG_MISSING_DATA){
                        Log.d("error", "Language not supported");
                    }
                } else
                    Log.d("error", "TTS failed :(");
            }
        });
    }

    public static void speak(final String myText){

        mytts.speak(myText, TextToSpeech.QUEUE_FLUSH, null, null);

    }


    public static void off(){
        if(mytts !=null) {
            mytts.stop();
            //mytts.shutdown();  //calling this here is not what we want
        }
    }

    public static void shutdown(){
        if(mytts !=null) {
            mytts.shutdown();  //if you need call shutdown with this method
        }
    }
}