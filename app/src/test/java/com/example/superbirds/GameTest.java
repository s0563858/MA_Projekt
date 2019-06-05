package com.example.superbirds;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
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
    public void getIDfromServerTest() {
        Game g = new Game(null,null,null,null);
        try {
            assertTrue(g.getIDfromServer()==-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveScoreTest() {
        MainActivity main = mock(MainActivity.class);
        Game g = new Game(null,null,null,main);
        g.saveScore();
        verify(main,times(2)).runOnUiThread(ArgumentMatchers.any(Runnable.class));
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