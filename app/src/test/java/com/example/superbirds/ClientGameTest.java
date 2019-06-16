package com.example.superbirds;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientGameTest {
    @Test
    public void instantiate() {
        ClientGame c=new ClientGame();
        assertNotNull(c);
    }

    @Test
    public void communicateWithServer() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObjectPosition no = mock(NetworkObjectPosition.class);
        HttpsURLConnection h = mock(HttpsURLConnection.class);
        c.netObj=no;
        byte[] test = "1,0,0".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer(no);
        assertTrue(((NetworkObjectPosition)c.netObj).otherPlayersPosY==1);
    }

    @Test
    public void getIDfromServerTest() throws IOException{
        ClientGame c=new ClientGame();
        NetworkObjectGetID no = mock(NetworkObjectGetID.class);
        HttpsURLConnection h = mock(HttpsURLConnection.class);
        c.netObj=no;
        byte[] test = "1".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer(no);
        assertTrue(((NetworkObjectGetID)c.netObj).id==1);
    }

    @Test
    public void getIDfromServerEmptyAnswerTest() throws IOException{
        ClientGame c=new ClientGame();
        NetworkObjectGetID no = mock(NetworkObjectGetID.class);
        HttpsURLConnection h = mock(HttpsURLConnection.class);
        c.netObj=no;
        byte[] test = "".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer(no);
        assertTrue(((NetworkObjectGetID)c.netObj).id==-1);
    }

    @Test
    public void communicateWithServerEmptyAnswer() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObjectPosition no = mock(NetworkObjectPosition.class);
        HttpsURLConnection h = mock(HttpsURLConnection.class);
        c.netObj=no;
        byte[] test = "".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer(no);
        assertTrue(((NetworkObjectPosition)c.netObj).otherPlayersPosY==-400);
    }

    @Test
    public void communicateWithServerBadAnswer() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObjectPosition no = mock(NetworkObjectPosition.class);
        HttpsURLConnection h = mock(HttpsURLConnection.class);
        c.netObj=no;
        byte[] test = "dwdwdwd".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer(no);
        assertTrue(((NetworkObjectPosition)c.netObj).otherPlayersPosY==-400);
    }

    @Test
    public void communicateWithServerBadFormatAnswer() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObjectPosition no = mock(NetworkObjectPosition.class);
        HttpsURLConnection h = mock(HttpsURLConnection.class);
        c.netObj=no;
        byte[] test = "1,1".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer(no);
        assertTrue(((NetworkObjectPosition)c.netObj).otherPlayersPosY==-400);
    }

    @Test
    public void communicateWithServerNetObjNull() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObjectPosition no = mock(NetworkObjectPosition.class);
        HttpURLConnection h = mock(HttpURLConnection.class);
        c.netObj=null;
        assertTrue(c.doInBackground((INetworkObject[]) null)==null);
    }
}