package com.example.superbirds;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import static org.junit.Assert.*;

public class NetworkObjectTest {

    @Test
    public void instantiate() {
        NetworkObject no = new NetworkObject();
        assertNotNull(no);
    }



    @Test
    public void getConnectionTest() throws IOException {
        NetworkObject no = new NetworkObject();
        no.currentPlayersPipe2Pos=1;
        no.currentPlayersPipe1Pos=2;
        no.currentPlayersID=33;
        no.currentPlayersPosY=12;
        no.done=false;
        no.pipe2PosX=10;
        no.pipe1PosX=20;

        HttpsURLConnection h = no.getConnection();
        assertTrue(h.getURL().toString().equals ("https://heartbleed.de/app?getID=0&restart=0&posY=12.0&id=33&pipe1=2.0&pipe2=1.0"));
    }

    @Test
    public void getConnectionWithoutValuesTest() throws IOException {
        NetworkObject no = new NetworkObject();

        HttpsURLConnection h = no.getConnection();
        System.out.println(h.getURL());
        assertTrue(h.getURL().toString().equals ("https://heartbleed.de/app?getID=0&restart=0&posY=0.0&id=0&pipe1=0.0&pipe2=0.0"));
    }

    @Test
    public void valuesTest() {
        NetworkObject no = new NetworkObject();
        no.currentPlayersPipe2Pos=1;
        no.currentPlayersPipe1Pos=2;
        no.currentPlayersID=33;
        no.currentPlayersPosY=12;
        no.done=false;
        no.pipe2PosX=10;
        no.pipe1PosX=20;
        assertEquals(no.currentPlayersPipe2Pos,1,0.001f);
        assertEquals(no.currentPlayersPipe1Pos,2,0.001f);
        assertEquals(no.currentPlayersID,33,0.001f);
        assertEquals(no.currentPlayersPosY,12,0.001f);
        assertEquals(no.done,false);
        assertEquals(no.pipe2PosX,10,0.001f);
        assertEquals(no.pipe1PosX,20,0.001f);
    }

}