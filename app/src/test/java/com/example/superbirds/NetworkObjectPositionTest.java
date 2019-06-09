package com.example.superbirds;

import org.junit.Test;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import static org.junit.Assert.*;

public class NetworkObjectPositionTest {

    @Test
    public void instantiate() {
        NetworkObjectPosition no = new NetworkObjectPosition();
        assertNotNull(no);
    }



    @Test
    public void getConnectionTest() throws IOException {
        NetworkObjectPosition no = new NetworkObjectPosition();
        no.currentPlayersPipe2Pos=1;
        no.currentPlayersPipe1Pos=2;
        no.currentPlayersID=33;
        no.currentPlayersPosY=12;
        no.setDone(false);
        no.pipe2PosX=10;
        no.pipe1PosX=20;

        HttpsURLConnection h = no.getConnection();
        assertTrue(h.getURL().toString().equals ("https://heartbleed.de/app?getID=0&restart=0&posY=12.0&id=33&pipe1=2.0&pipe2=1.0"));
    }

    @Test
    public void getConnectionWithoutValuesTest() throws IOException {
        NetworkObjectPosition no = new NetworkObjectPosition();

        HttpsURLConnection h = no.getConnection();
        System.out.println(h.getURL());
        assertTrue(h.getURL().toString().equals ("https://heartbleed.de/app?getID=0&restart=0&posY=0.0&id=0&pipe1=0.0&pipe2=0.0"));
    }

    @Test
    public void valuesTest() {
        NetworkObjectPosition no = new NetworkObjectPosition();
        no.currentPlayersPipe2Pos=1;
        no.currentPlayersPipe1Pos=2;
        no.currentPlayersID=33;
        no.currentPlayersPosY=12;
        no.setDone(false);
        no.pipe2PosX=10;
        no.pipe1PosX=20;
        assertEquals(no.currentPlayersPipe2Pos,1,0.001f);
        assertEquals(no.currentPlayersPipe1Pos,2,0.001f);
        assertEquals(no.currentPlayersID,33,0.001f);
        assertEquals(no.currentPlayersPosY,12,0.001f);
        assertEquals(no.getDone(),false);
        assertEquals(no.pipe2PosX,10,0.001f);
        assertEquals(no.pipe1PosX,20,0.001f);
    }

}