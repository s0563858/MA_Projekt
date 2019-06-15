package com.example.superbirds;

import android.os.Handler;
import android.widget.ImageView;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void instantiateIDWhileStart() {
        Game g = new Game(null,null,null,null);
        assertEquals(g.networkID,-2);
    }

    @Test
    public void instantiateScoreWhileStart() {
        Game g = new Game(null,null,null,null);
        assertEquals(g.score,0);
    }

    @Test
    public void saveScoreTest() {
        MainActivity  main = mock(MainActivity.class);

        main.handler = mock(Handler.class);
        Game g = new Game(null,null,null,main);

        g.saveScore();
        verify(main.handler,times(2)).sendMessage(null);
    }


    @Test
    public void drawGameObjectTest() {
        MainActivity  main = mock(MainActivity.class);

        main.handler = mock(Handler.class);
        Game ga = new Game(null,null,null,main);

        ImageView i = mock(ImageView.class);
        GameObject g = new GameObject(1,1,"test",i);
        ga.drawGameobject(g);
        verify(main.handler,times(2)).sendMessage(null);
    }

    @Test
    public void drawGameObjectImageIsNullTest() {
        MainActivity  main = mock(MainActivity.class);

        main.handler = mock(Handler.class);
        Game ga = new Game(null,null,null,main);

        ImageView i = mock(ImageView.class);
        GameObject g = new GameObject(1,1,"test",null);
        assertFalse( ga.drawGameobject(g));
    }

    @Test
    public void drawGameObjectGameObjectIsNullTest() {
        MainActivity  main = mock(MainActivity.class);

        main.handler = mock(Handler.class);
        Game ga = new Game(null,null,null,main);

        assertFalse( ga.drawGameobject(null));
    }

    @Test
    public void drawGameObjectMainNullTest() {
        MainActivity  main = mock(MainActivity.class);

        main.handler = mock(Handler.class);
        Game ga = new Game(null,null,null,null);

        ImageView i = mock(ImageView.class);
        GameObject g = new GameObject(1,1,"test",i);

        assertFalse( ga.drawGameobject(g));
    }

    @Test
    public void saveScoreMainNullTest() {
        MainActivity  main = mock(MainActivity.class);

        main.handler = mock(Handler.class);
        Game g = new Game(null,null,null,null);
        assertFalse(g.saveScore());
    }

    @Test
    public void runTest() {
        Game g = new Game(null,null,null,null);
        g.run();
        assertFalse(g.gameActivated);
    }

    @Test
    public void getGameobjectPositionXTest() {
        GameObject g = new GameObject(100,2,"test",null);
        List<GameObject> mo = new ArrayList<GameObject>();
        mo.add(g);
        Game ga = new Game(null,null,mo,null);
        assertEquals(ga.getGameobjectPositionX("test"),100,0.001f);
    }

    @Test
    public void getGameobjectPositionXMovingObjectsIsEmptyTest() {
        List<GameObject> mo = new ArrayList<GameObject>();
        Game ga = new Game(null,null,mo,null);
        assertEquals(ga.getGameobjectPositionX("test"),-1000,0.001f);
    }

    @Test
    public void getGameobjectPositionXMovingObjectsIsNullTest() {
        Game ga = new Game(null,null,null,null);
        assertEquals(ga.getGameobjectPositionX("test"),-1000,0.001f);
    }

    @Test
    public void getGameobjectPositionXGameobjectNameIsNullTest() {
        Game ga = new Game(null,null,null,null);
        assertEquals(ga.getGameobjectPositionX("test"),-1000,0.001f);
    }

    @Test
    public void incrementScoreTest() {
        Game g = new Game(null,null,null,null);
        g.incrementScore();
        assertTrue(g.score==1);
    }

    @Test
    public void incrementScoreMainNotNullTest() {
        MainActivity  main = mock(MainActivity.class);

        main.handler = mock(Handler.class);
        Game g = new Game(null,null,null,main);
        assertTrue(g.incrementScore());
    }

    @Test
    public void incrementScoreMainNullTest() {
        Game g = new Game(null,null,null,null);
        assertFalse(g.incrementScore());
    }

    @Test
    public void flyButtonClickedBirdNullTest() {
        Game g = new Game(null,null,null,null);
        g.gameActivated=true;
        assertFalse(g.FlyButtonClicked());
    }

    @Test
    public void flyButtonClickedGameNotActivatedTest() {
        Game g = new Game(null,null,null,null);
        g.gameActivated=false;
        assertFalse(g.FlyButtonClicked());
    }

}