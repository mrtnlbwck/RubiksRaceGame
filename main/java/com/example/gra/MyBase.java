package com.example.gra;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */
public class MyBase {

    public static final String SHARED_PREF = "sharedPref";
    /** Zmienna zawierająca ilość ruchów z ostatniej gry */
    public static final String LAST_STEP = "lastStep";
    /** Zmienna zawierająca czas ostatniej gry */
    public static final String LAST_TIME = "lastTime";
    /** Zmienna zawierająca najmniejszą ilość ruchów ze wszystkich gier */
    public static final String BEST_STEP = "bestStep";
    /** Zmienna zawierająca najlepszy czas ze wszytskkich gier */
    public static final String BEST_TIME = "bestTime";
    private static SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    public MyBase(Context context){
        preferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /** Metoda zapisująca ilość ruchów z ostatniej gry */
    public void saveLastStep(int steps){
        editor.putInt(LAST_STEP,steps).commit();
    }

    /** Metoda pobierająca ilość ruchów z ostatniej gry */
    public static int getLastStep() {
        return preferences.getInt(LAST_STEP,0);
    }

    /** Metoda zapisująca najmniejszą ilość ruchó ze wszystkich gier */
    public void saveBestStep(int steps){
        editor.putInt(BEST_STEP,steps).commit();
    }

    /** Metoda pobierająca najmniejszą ilość ruchów */
    public static int getBestStep() {
        return preferences.getInt(BEST_STEP,0);
    }

    /** Metoda zapisująca czas ostatniej gry */
    public void saveLastTime(int seconds){
        editor.putInt(LAST_TIME,seconds).commit();
    }

    /** Metoda pobierająca czas ostatniej gry */
    public static int getLastTime() {
        return preferences.getInt(LAST_TIME,0);
    }

    /** Metoda zapisująca najlepszy czas gry */
    public void saveBestTime(int seconds){
        editor.putInt(BEST_TIME,seconds).commit();
    }

    /** Metoda pobierająca najlepszy czas gry */
    public static int getBestTime() {
        return preferences.getInt(BEST_TIME,0);
    }
}
