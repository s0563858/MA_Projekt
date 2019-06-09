package com.example.superbirds;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ClientGame extends AsyncTask<INetworkObject, Integer, INetworkObject> {
    private static int playerYPos = 0;

    INetworkObject netObj;




    @Override
    protected void onPreExecute() {

    }
    @Override
    protected INetworkObject doInBackground(INetworkObject... params) {
        // Runs in background thread
        if(params==null){return null;}
        netObj = params[0];
        try {
            if(netObj.getClass()==NetworkObjectPosition.class){
                communicateWithServer((NetworkObjectPosition) netObj);
            }
            if(netObj.getClass()==NetworkObjectGetID.class){
                communicateWithServer((NetworkObjectGetID) netObj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return netObj;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(INetworkObject resp) {
        netObj.setDone(true);
    }

    public  void communicateWithServer(NetworkObjectGetID n) throws IOException {
        if(netObj==null){return;}
        HttpsURLConnection connection = netObj.getConnection();
        PrintWriter out;
        BufferedReader in;
        String line;
        Log.i("network","[Client] Connecting to Server...");


        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        line = in.readLine();
        in.close();


        Log.i("network","[Client] Received ID[" + line + "] from Server");
        if(line==null){((NetworkObjectGetID)netObj).id=-1;return;}
        if(line.contains("slots")){//restarting the server if the server is full
            INetworkObject netO = new NetworkObjectGetID(0,1);// -> will restart the server
            connection=netO.getConnection();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            in.close();
            communicateWithServer(n);//after the restart request was sent -> try to get the id again
        }else{
            if(!line.contains("restarting")){((NetworkObjectGetID) netObj).id = Integer.valueOf(line);}
        }


    }


    public  void communicateWithServer(NetworkObjectPosition n) throws IOException {
        if(netObj==null){return;}
        HttpsURLConnection connection = n.getConnection();
        PrintWriter out;
        BufferedReader in;
        String line;
        String player = String.valueOf(playerYPos);

        //out = new PrintWriter(connection.getOutputStream());
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        line = in.readLine();
        in.close();


       // out.println(player + "," + netObj.currentPlayersID);





        if(line==null || line=="" ){
            n.otherPlayersPosY=-400;
            n.pipe1PosX=-400;
            n.pipe2PosX=-400;
        } else{
            String[] numbers = line.split(",");
            if(numbers.length!=3){n.otherPlayersPosY=-400;}
            else {
                n.otherPlayersPosY=Float.parseFloat(numbers[0]);
                n.pipe1PosX=Float.parseFloat(numbers[1]);
                n.pipe2PosX=Float.parseFloat(numbers[2]);
            }
        }
        Log.i("network","[Client] anderer user: " + n.otherPlayersPosY+" : "+line);

    }



}
