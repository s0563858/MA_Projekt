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
     * Key für die letzte x Position
     */
    String POS_X = "posXKey";

    /**
     * Key für die letzte y Position
     */
    String POS_Y = "posYKey";


    /**
     * Lädt Spielstand
     * @return Array mit Speicherungen
     */
    String[] getGameState();

    /**
     * Speichert den Spielstand
     * @param highScore High Score
     * @param xPosition Letzte x Position
     * @param yPosition Letzte y Position
     */
    void saveGameState(int highScore, int xPosition, int yPosition);

    /**
     * Gibt die SharedPreferences Instanz zurück
     * @return SharedPreferences
     */
    SharedPreferences getSharedPrefs();
}
