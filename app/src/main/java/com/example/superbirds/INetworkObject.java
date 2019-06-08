package com.example.superbirds;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

public interface INetworkObject {
    public HttpsURLConnection getConnection() throws IOException;
}
