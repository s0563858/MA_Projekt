package com.example.superbirds;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Implementierung des Memento Patterns
 * Speicherung erfolgt durch SharedPreferences
 */
public class SPMemento implements Memento {

    private SharedPreferences sharedPreferences;

    /**
     * Instantiiert SharedPreferences
     * @param context App Context
     */
    public SPMemento(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void saveGameState(int highScore, int xPosition, int yPosition) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HIGHSCORE, highScore);
        editor.putInt(POS_X, xPosition);
        editor.putInt(POS_Y, yPosition);
        editor.commit();
    }

    @Override
    public String[] getGameState() {
        String hS = String.valueOf(sharedPreferences.getInt(HIGHSCORE, -1));
        String pX = String.valueOf(sharedPreferences.getInt(POS_X, -1));
        String pY = String.valueOf(sharedPreferences.getInt(POS_Y, -1));

        return new String[] {hS, pX, pY};
    }

    @Override
    public SharedPreferences getSharedPrefs() {
        return sharedPreferences;
    }
}
