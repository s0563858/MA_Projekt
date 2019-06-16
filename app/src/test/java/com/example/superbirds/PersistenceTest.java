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
    public void saveGameStateTest() throws Exception {
        memento.saveGameState(42);

        Assert.assertEquals(42, memento.getSharedPrefs().getInt(memento.HIGHSCORE, -1));
    }

    @Test
    public void getGameStateTest() throws Exception {
        memento.saveGameState(42);

        Assert.assertEquals("42", memento.getGameState());
    }

    @Test
    public void getDefaultValueWhenNothingWasSavedToKeyTest() {
        int test = memento.getSharedPrefs().getInt("HIGHSCORE", -1);

        Assert.assertTrue(test == -1);
    }

    @Test
    public void getDefaultValueWhenUnknownKeyIsUsedTest() {
        int test = memento.getSharedPrefs().getInt("WHUT", -1);

        Assert.assertTrue(test == -1);
    }

    ////////////////////////
    //     EDGE CASES     //
    //   AND EXCEPTIONS   //
    ////////////////////////

    @Test
    public void saveGameStateWithIntMaxTest() throws Exception {
        memento.saveGameState(Integer.MAX_VALUE);

        Assert.assertEquals(String.valueOf(Integer.MAX_VALUE), memento.getGameState());
    }

    @Test
    public void saveGameStateWithLessThanIntMinShouldNOTThrowExceptionTest() throws Exception {
        memento.saveGameState(Integer.MIN_VALUE-1);

        Assert.assertEquals(String.valueOf(Integer.MAX_VALUE), memento.getGameState());
    }

    @Test(expected = Exception.class)
    public void getGameStateWhenNothingWasSavedThrowsExceptionTest() throws Exception {
        memento.getGameState();
    }

    @Test(expected = Exception.class)
    public void saveGameStateWithDefValueShouldThrowExceptionTest() throws Exception {
        memento.saveGameState(-1);
    }

    @Test(expected = Exception.class)
    public void saveGameStateWithNegValueShouldThrowExceptionTest() throws Exception {
        memento.saveGameState(-31);
    }

    @Test(expected = Exception.class)
    public void saveGameStateWithIntMinShouldThrowException() throws Exception {
        memento.saveGameState(Integer.MIN_VALUE);
    }

    @Test(expected = Exception.class)
    public void saveGameStateWitGreaterThanIntMaxShouldThrowExceptionTest() throws Exception {
        memento.saveGameState(Integer.MAX_VALUE+1);
    }
}