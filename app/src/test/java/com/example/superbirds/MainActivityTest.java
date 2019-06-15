package com.example.superbirds;

import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainActivityTest {
    @Test
    public void instantiate() {
        MainActivity m = new MainActivity();
        assertNotNull(m);
    }

    @Test
    public void saveNewScoreTest() {
        MainActivity m = new MainActivity();
        Memento mem = mock(Memento.class);

        GameObject b = mock(GameObject.class);
        when(b.getX()).thenReturn(0f);
        when(b.getY()).thenReturn(0f);
        Game g = new Game(b,null,null,null);
        m.currentGame=g;
        m.memento=mem;
        m.saveNewScore(0);
        verify(mem, times(1)).saveGameState(anyInt(),anyInt(),anyInt());
    }

    @Test
    public void saveNewScoreMementoNullTest() {
        MainActivity m = new MainActivity();
        Memento mem = mock(Memento.class);

        GameObject b = mock(GameObject.class);
        when(b.getX()).thenReturn(0f);
        when(b.getY()).thenReturn(0f);
        Game g = new Game(b,null,null,null);
        m.currentGame=g;
        m.memento=null;
        m.saveNewScore(0);
        verify(mem, times(0)).saveGameState(anyInt(),anyInt(),anyInt());
    }

    @Test
    public void saveNewScoreGameNullTest() {
        MainActivity m = new MainActivity();
        Memento mem = mock(Memento.class);

        GameObject b = mock(GameObject.class);
        when(b.getX()).thenReturn(0f);
        when(b.getY()).thenReturn(0f);
        Game g = new Game(b,null,null,null);
        m.currentGame=null;
        m.memento=mem;
        m.saveNewScore(0);
        verify(mem, times(0)).saveGameState(anyInt(),anyInt(),anyInt());
    }


    @Test
    public void saveNewScoreBirdNullTest() {
        MainActivity m = new MainActivity();
        Memento mem = mock(Memento.class);

        GameObject b = mock(GameObject.class);
        when(b.getX()).thenReturn(0f);
        when(b.getY()).thenReturn(0f);
        Game g = new Game(null,null,null,null);
        m.currentGame=g;
        m.memento=mem;
        m.saveNewScore(0);
        verify(mem, times(0)).saveGameState(anyInt(),anyInt(),anyInt());
    }



    @Test
    public void saveNewScoreValuesTest() {
        MainActivity m = new MainActivity();
        Memento mem = mock(Memento.class);

        GameObject b = mock(GameObject.class);
        when(b.getX()).thenReturn(10f);
        when(b.getY()).thenReturn(20f);
        Game g = new Game(b,null,null,null);
        m.currentGame=g;
        m.memento=mem;
        m.saveNewScore(1);
        verify(mem, times(1)).saveGameState(1,10,20);
    }




    @Test
    public void setNewScoreTest() {
        MainActivity m = new MainActivity();
        TextView s = mock(TextView.class);
        m.score = s;

        m.setNewScore(0);
        verify(s, times(1)).setText(anyString());
    }


    @Test
    public void setNewScoreValueTest() {
        MainActivity m = new MainActivity();
        TextView s = mock(TextView.class);
        m.score = s;

        m.setNewScore(22);
        verify(s, times(1)).setText("Score: 22");
    }


    @Test
    public void setNewScoreScoreNullTest() {
        MainActivity m = new MainActivity();
        TextView s = mock(TextView.class);
        m.score = null;

        assertFalse(m.setNewScore(22));
    }


}