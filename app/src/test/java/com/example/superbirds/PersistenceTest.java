package com.example.superbirds;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * SharedPreferences Tests mit Roboelectric
 */
@RunWith(RobolectricTestRunner.class)
public class PersistenceTest {
    Memento memento;
    Context context;
    SharedPreferences prefs;

    @Before
    public void setupTest() {
        context = ApplicationProvider.getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        memento = new SPMemento(prefs);
    }

    @Test
    public void getSharedPreferencesTest() {
        Assert.assertEquals(memento.getSharedPrefs(), prefs);
    }

    @Test
    public void saveGameStateTest() {
        memento.saveGameState(42);

        Assert.assertEquals(42, memento.getSharedPrefs().getInt(memento.HIGHSCORE, -1));
    }

    @Test
    public void getGameStateTest() {
        memento.saveGameState(42);

        Assert.assertEquals("42", memento.getGameState());
    }

    @Test
    public void getDefaultValueWhenNothingWasSavedToKey() {
        int test = memento.getSharedPrefs().getInt("HIGHSCORE", -1);

        Assert.assertTrue(test == -1);
    }

    @Test
    public void getDefaultValueWhenUnknownKeyIsUsedTest() {
        int test = memento.getSharedPrefs().getInt("WHUT", -1);

        Assert.assertTrue(test == -1);
    }
}