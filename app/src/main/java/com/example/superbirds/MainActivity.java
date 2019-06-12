package com.example.superbirds;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Memento memento;
    TextView score;

    Game currentGame;

    public Handler handler;
    public static int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        this.height = height;
        GameObject bird;
        List<GameObject> pipes = new ArrayList<GameObject>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton button  = findViewById(R.id.imageButton);
        score = findViewById(R.id.textView3);
        TextView lastScore = findViewById(R.id.textView);

        memento = new SPMemento(getApplicationContext());
        SharedPreferences sharedPreferences = memento.getSharedPrefs();

        if(sharedPreferences.contains(memento.HIGHSCORE)) {
            lastScore.setText("Last score: "+memento.getGameState()[0]);
        }

        bird = new GameObject(40,this.height/2,"bird", (ImageView) findViewById( R.id.imageView));
        GameObject otherPlayer = new GameObject(40,this.height/2,"otherPlayer", (ImageView) findViewById( R.id.imageView8));
        GameObject pipe1 = new GameObject(300,210,"pipe1",(ImageView) findViewById( R.id.imageView3));
        GameObject pipe2 = new GameObject(300,-30,"pipe1", (ImageView) findViewById( R.id.imageView2));
        GameObject pipe3 = new GameObject(500,180,"pipe2", (ImageView) findViewById( R.id.imageView5));
        GameObject pipe4 = new GameObject(500,-80,"pipe2", (ImageView) findViewById( R.id.imageView4));

        GameObject scoreElement1 = new GameObject(150,this.height/2,"score1", (ImageView) findViewById( R.id.imageView6));
        GameObject scoreElement2 = new GameObject(400,this.height/2,"score2", (ImageView) findViewById( R.id.imageView7));

        pipes.add(pipe1);
        pipes.add(pipe2);
        pipes.add(pipe3);
        pipes.add(pipe4);

        pipes.add(scoreElement1);
        pipes.add(scoreElement2);

        handler = new Handler() {
            @Override public void handleMessage(Message msg) {
                if(msg.what==0){// 0 -> score incremented
                    setNewScore((int)msg.obj);
                }
                if(msg.what==1){// 1 -> save the score
                    saveNewScore((int)msg.obj);
                }
                if(msg.what==2){// 2 -> draw the object
                    ImageView image  = ((GameObject) msg.obj).getImage();
                    image.setY(((GameObject) msg.obj).getY());
                    image.setX(((GameObject) msg.obj).getX());
                }
                //System.out.println(msg.what);
            }
        };


        currentGame = new Game(bird,otherPlayer,pipes,this);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentGame.FlyButtonClicked();
            }
        });

        currentGame.start();
    }

    public void saveNewScore(int scr) {
        if(memento==null||score==null){return;}
        memento.saveGameState(scr, 0,0);
    }

    public void setNewScore(int scr) {
        if(memento==null||score==null){return;}
        score.setText("Score: "+String.valueOf(scr));
    }


    public Rect getRectForTest(){
        return new Rect(1,2,3,4);
    }
}
