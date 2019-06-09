package com.example.superbirds;

import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Game extends Thread {


    GameObject bird;
    List<GameObject> movingObjects;
    int networkID;
    private String domain="heartbleed.de";
    INetworkObject netObj;
    GameObject otherPlayer;


    public boolean gameActivated;//public for testing purpouses
    MainActivity main;
    public int score;
    public boolean firstPacketFetched;



    CollisionDetection cd;

    ClientGame c;
    long lastTime;

    public Game(GameObject b,GameObject o , List<GameObject> mo, MainActivity m){

        score=-1;
        bird=b;
        movingObjects=mo;
        main=m;
        cd = new CollisionDetection();
        incrementScore();
        otherPlayer=o;
        firstPacketFetched=false;
        gameActivated = true;
        lastTime = System.nanoTime();

        networkID=-2;
        netObj = new NetworkObjectGetID(1,0);
        c = new ClientGame();
        c.execute(netObj);

    }



    public void movingObjects(){
        long time = System.nanoTime();
        float delta_time = (float) ((time - lastTime) / 1000000);
        lastTime = time;
        bird.setNewPosition(bird.getX(),bird.getY() + 0.02f*delta_time);
        drawGameobject(bird);
        if(netObj!=null && netObj.getDone()){ //|| (networkID==2 && netObj!=null && !netObj.done && firstPacketFetched) ){
            Log.i("network","- y: "+((NetworkObjectPosition)netObj).otherPlayersPosY);
            otherPlayer.setNewPosition(bird.getX(),((NetworkObjectPosition)netObj).otherPlayersPosY);
            drawGameobject(otherPlayer);
        }

        if(networkID==-1 || networkID==1 || (networkID==2 && netObj!=null && !netObj.getDone() && firstPacketFetched)){//wenn spieler der server ist oder es keinen freien platz gibt
            for(GameObject obj : movingObjects) {
                obj.setNewPosition(obj.getX() - 0.02f*delta_time, obj.getY() );
                if(obj.getX() < -50){
                    obj.setNewPosition(400, obj.getY() );
                }
                drawGameobject(obj);
            }

        }
        if(networkID==2 && netObj!=null && netObj.getDone() && ((NetworkObjectPosition)netObj).pipe1PosX!=((NetworkObjectPosition)netObj).pipe2PosX){//wenn der spiler die client-rolle hat
            firstPacketFetched=true;
            for(GameObject obj : movingObjects) {
                if(obj.getName().equals("pipe1")){
                    obj.setNewPosition(((NetworkObjectPosition)netObj).pipe1PosX, obj.getY() );
                }
                if(obj.getName().equals("pipe2")){
                    obj.setNewPosition(((NetworkObjectPosition)netObj).pipe2PosX, obj.getY() );
                }
                if(obj.getName().equals("score1")){obj.setNewPosition(((NetworkObjectPosition)netObj).pipe1PosX, obj.getY() );}
                if(obj.getName().equals("score2")){obj.setNewPosition(((NetworkObjectPosition)netObj).pipe2PosX, obj.getY() );}
                drawGameobject(obj);
            }
            Log.i("network","- y: "+((NetworkObjectPosition)netObj).otherPlayersPosY);
            otherPlayer.setNewPosition(bird.getX(),((NetworkObjectPosition)netObj).otherPlayersPosY);
            drawGameobject(otherPlayer);
        }
    }


    private void network(){
        //--------------Network--------------
        if(c==null || netObj == null || netObj.getDone()){
            Log.i("network","- New async network task");
            netObj = new NetworkObjectPosition();
            netObj.setDone(false);
            ((NetworkObjectPosition)netObj).currentPlayersID=networkID;
            ((NetworkObjectPosition)netObj).currentPlayersPosY=bird.getY();
            ((NetworkObjectPosition)netObj).currentPlayersPipe1Pos = getGameobjectPositionX( "pipe1");
            ((NetworkObjectPosition)netObj).currentPlayersPipe2Pos = getGameobjectPositionX( "pipe2");
            c=new ClientGame();
            c.execute(netObj);
        }
        //--------------Network--------------
    }



    public void run(){
        //c.run();
        if(bird == null || movingObjects == null  || main == null || cd == null){
            gameActivated=false;
            Log.i("Error: ","- game deactivated");
            return;
        }

        while(gameActivated) {
            if(netObj.getClass()==NetworkObjectGetID.class){//getting the id asynchronously
                if(netObj.getDone()){networkID=((NetworkObjectGetID)netObj).id; netObj=null; }
                else {continue;}
            }
            movingObjects();
            detectCollisions();
            network();
        }
        saveScore();
    }


    public void detectCollisions(){
        if(networkID==1 || (networkID==2 && firstPacketFetched)){
            List<Collision> collisions = cd.detectCollisions(movingObjects,bird);

            for (Collision c : collisions) {
                if(c.value=="dead" && c.state=="entered"){
                    Log.i("collision","-gameover");
                    gameActivated=false;
                    bird.setNewPosition(0,-1000);
                }
                if(c.value=="score" && c.state=="entered"){
                    Log.i("score","-score incremented");
                    incrementScore();
                }
            }
        }
    }


    public float getGameobjectPositionX(String gameObjectName){
        float pos = -1000;

        for (GameObject g:
             movingObjects) {
            if(g.getName().equals(gameObjectName)){
                pos=g.getX();
            }

        }
        return pos;
    }


    public  void saveScore() {
        if(main==null){return;}
        Message msg = main.handler.obtainMessage(1,score);
        main.handler.sendMessage(msg);

       /* main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                main.saveNewScore(score);
            }
        });*/
    }


    public  void incrementScore(){
        score++;
        if(main==null){return;}
        Message msg = main.handler.obtainMessage(0,score);
        main.handler.sendMessage(msg);

        /*main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                main.setNewScore(score);
            }
        });*/
    }





    public void FlyButtonClicked(){
        if(bird==null){return;}
      //  Log.i("ImageButton","clicked");
        System.out.println(gameActivated);
        if(gameActivated){
            bird.setNewPosition(bird.getX(),bird.getY() - 20);
            drawGameobject(bird);
        }
    }



    private void drawGameobject(GameObject g){
         final GameObject a = g;

        if(g== null || g.getImage() == null){ return;}
        if(main==null){return;}

        Message msg = main.handler.obtainMessage(2,g);
        main.handler.sendMessage(msg);
      //  main.runOnUiThread(new Runnable() {
      //      @Override
       //     public void run() {
         //       ImageView image  = a.getImage();
         //       image.setY(a.getY());
          //      image.setX(a.getX());
        //    }
       // });

    }

/*
    public  int getIDfromServer() throws IOException {

        if(domain == null || bird == null || movingObjects == null  || main == null || cd == null){return -1;}

        HttpsURLConnection connection;
        PrintWriter out;
        BufferedReader in;
        String line;
        Log.i("network","[Client] Connecting to Server...");
        URL addr = new URL("https://"+domain+"/app?getID=1&restart=0" );
        System.out.println(addr);
        connection = (HttpsURLConnection) addr.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        connection.setUseCaches(false);
        connection.setDoOutput(true);

        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        line = in.readLine();
        in.close();


        Log.i("network","[Client] Received ID[" + line + "] from Server");
        if(line.contains("slots")){//restarting the server if the server is full
            addr = new URL("https://"+domain+"/app?getID=0&restart=1" );
            connection = (HttpsURLConnection) addr.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setUseCaches(false);
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            in.close();
            return getIDfromServer();
        }

        return Integer.valueOf(line);
    }*/
}
