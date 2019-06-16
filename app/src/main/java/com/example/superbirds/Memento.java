package com.example.superbirds;

import android.content.SharedPreferences;

/**
 * Memento Pattern Interface
 */
public interface Memento {
    /**
     * Name der Datei wo die Key-Value Paare gespeichert werden
     */
    String PREFERENCES = "gamePref";

    /**
     * Key für den High Score
     */
    String HIGHSCORE = "scoreKey";

    /**
     * Lädt Spielstand
     * @return Array mit Speicherungen
     */
    String getGameState() throws Exception;

    /**
     * Speichert den Spielstand
     * @param highScore High Score
     */
    void saveGameState(int highScore) throws Exception;

    /**
     * Gibt die SharedPreferences Instanz zurück
     * @return SharedPreferences
     */
    SharedPreferences getSharedPrefs();
}
