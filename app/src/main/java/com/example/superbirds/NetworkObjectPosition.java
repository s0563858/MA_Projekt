package com.example.superbirds;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkObjectPosition implements INetworkObject {
    public float otherPlayersPosY;
    public float pipe1PosX;
    public float pipe2PosX;

    public int currentPlayersID;
    public float currentPlayersPosY;
    public float currentPlayersPipe1Pos;
    public float currentPlayersPipe2Pos;
    private boolean done;

    private String domain="heartbleed.de";

    @Override
    public boolean getDone() {
        return done;
    }

    @Override
    public void setDone(boolean d) {
        done=d;
    }

    public HttpsURLConnection getConnection() throws IOException {
        URL addr = new URL("https://"+domain+"/app?getID=0&restart=0" +"&posY=" +this.currentPlayersPosY + "&id=" + this.currentPlayersID + "&pipe1="+this.currentPlayersPipe1Pos+ "&pipe2="+this.currentPlayersPipe2Pos);
        System.out.println(addr);
        HttpsURLConnection connection = (HttpsURLConnection) addr.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        connection.setUseCaches(false);
        connection.setDoOutput(true);
        return connection;
    }
}
