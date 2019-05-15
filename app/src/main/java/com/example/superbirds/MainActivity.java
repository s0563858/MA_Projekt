package com.example.superbirds;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Memento memento;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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



        bird = new GameObject(0,0,"bird", (ImageView) findViewById( R.id.imageView));
        GameObject pipe1 = new GameObject(150,150,"pipe",(ImageView) findViewById( R.id.imageView3));
        GameObject pipe2 = new GameObject(150,-30,"pipe", (ImageView) findViewById( R.id.imageView2));
        GameObject pipe3 = new GameObject(400,150,"pipe", (ImageView) findViewById( R.id.imageView5));
        GameObject pipe4 = new GameObject(400,-10,"pipe", (ImageView) findViewById( R.id.imageView4));

        GameObject scoreElement1 = new GameObject(150,100,"score", (ImageView) findViewById( R.id.imageView6));
        GameObject scoreElement2 = new GameObject(400,100,"score", (ImageView) findViewById( R.id.imageView7));

        pipes.add(pipe1);
        pipes.add(pipe2);
        pipes.add(pipe3);
        pipes.add(pipe4);

        pipes.add(scoreElement1);
        pipes.add(scoreElement2);

        final Game newGame = new Game(bird,pipes,this);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newGame.FlyButtonClicked();
            }
        });

        newGame.start();

    }


    public void saveNewScore(int scr){
        if(memento==null||score==null){return;}
        memento.saveGameState(scr, 0,0);
        score.setText("Score: "+String.valueOf(scr));
    }









}
