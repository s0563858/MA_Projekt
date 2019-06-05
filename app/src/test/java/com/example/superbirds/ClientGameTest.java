package com.example.superbirds;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

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
        NetworkObject no = mock(NetworkObject.class);
        HttpURLConnection h = mock(HttpURLConnection.class);
        c.netObj=no;
        byte[] test = "1,0,0".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer();
        assertTrue(c.netObj.otherPlayersPosY==1);
    }


    @Test
    public void communicateWithServerEmptyAnswer() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObject no = mock(NetworkObject.class);
        HttpURLConnection h = mock(HttpURLConnection.class);
        c.netObj=no;
        byte[] test = "".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer();
        assertTrue(c.netObj.otherPlayersPosY==-1000);
    }

    @Test
    public void communicateWithServerBadAnswer() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObject no = mock(NetworkObject.class);
        HttpURLConnection h = mock(HttpURLConnection.class);
        c.netObj=no;
        byte[] test = "dwdwdwd".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer();
        assertTrue(c.netObj.otherPlayersPosY==-1000);
    }

    @Test
    public void communicateWithServerBadFormatAnswer() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObject no = mock(NetworkObject.class);
        HttpURLConnection h = mock(HttpURLConnection.class);
        c.netObj=no;
        byte[] test = "1,1".getBytes();
        InputStream is = new ByteArrayInputStream(test);
        when(h.getInputStream()).thenReturn(is);
        when(no.getConnection()).thenReturn(h);
        c.communicateWithServer();
        assertTrue(c.netObj.otherPlayersPosY==-1000);
    }

    @Test
    public void communicateWithServerNetObjNull() throws IOException {
        ClientGame c=new ClientGame();
        NetworkObject no = mock(NetworkObject.class);
        HttpURLConnection h = mock(HttpURLConnection.class);
        c.netObj=null;
        assertTrue(c.doInBackground(null)==null);
    }

}