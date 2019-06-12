package com.example.superbirds;

import android.os.Handler;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameTest {
    @Test
    public void instantiate() {
        Game g = new Game(null,null,null,null);
        assertNotNull(g);
    }

    @Test
    public void saveScoreTest() {
        MainActivity  main = mock(MainActivity.class);

        main.handler = mock(Handler.class);
        Game g = new Game(null,null,null,main);
        int counter = 0;

        g.saveScore();
        verify(main.handler,times(2)).sendMessage(null);
    }

    @Test
    public void incrementScoreTest() {
        Game g = new Game(null,null,null,null);
        g.incrementScore();
        assertTrue(g.score==1);
    }

    @Test
    public void flyButtonClickedTest() {
        GameObject go = new GameObject(0,0,"bird",null );
        Game g = new Game(go,null,null,null);
        g.gameActivated=true;
        g.FlyButtonClicked();
        assertTrue(go.getY()==-20);
    }
}