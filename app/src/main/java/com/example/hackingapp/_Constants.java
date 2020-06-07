package com.example.hackingapp;

public class _Constants {

    public static final int ABSdiff = 100;

    public static final int Velocity = 100;

    public static String mainActivity = "For Primary School swipe up... " +
            "for Secondary School swipe right, for Vocational Skills swipe left, to exit swipe down.";

    public static String primary = "For Science swipe up, for English swipe right, for Malay swipe left, to go back swipe down.";

    public static String secondary = "For English swipe up, for Malay swipe down, to go to the next page swipe right.";

    public static String secondary2 = "For History swipe up, for Science swipe down, to go to the next page swipe right, " +
            "to go to the previous page swipe left.";

    public static String secondary3 = "For Islamic Studies swipe up, for Moral Studies swipe down, to go to the previous page swipe left.";

    public static String vocational = "For Office Management swipe up, for Call Services swipe down, to go to the next page swipe right.";

    public static String vocational2 = "For Computer Systems swipe up, for Reflexology swipe down, to go to the previous page swipe left.";

    public static void stop () {
        _TextToSpeechInitializer.off();
        _TextToSpeechInitializer.shutdown();
    }

}
