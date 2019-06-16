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

    /**
     * Wird zum Instantiieren des Objekts für die Integration Tests benötigt
     * @param mockPrefs SharedPreferences Instanz
     */
    public SPMemento(SharedPreferences mockPrefs) {
        sharedPreferences = mockPrefs;
    }

    @Override
    public void saveGameState(int highScore) throws Exception {
        if(highScore <0) {throw new Exception("Negative values not allowed");}
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HIGHSCORE, highScore);
        editor.commit();
    }

    @Override
    public String getGameState() throws Exception {
        if(sharedPreferences.getInt(HIGHSCORE, -1) == -1) {
            throw new Exception("Nothing was saved yet.");
        }

        String hS = String.valueOf(sharedPreferences.getInt(HIGHSCORE, -1));

        return hS;
    }

    @Override
    public SharedPreferences getSharedPrefs() {
        return sharedPreferences;
    }
}
