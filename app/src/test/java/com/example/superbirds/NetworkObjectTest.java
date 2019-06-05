package com.example.superbirds;

import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkObjectTest {

    @Test
    public void instantiate() {
        NetworkObject no = new NetworkObject();
        assertNotNull(no);
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