package mobanw.birds.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states= new Stack<State>();
    }

    public void push(State s){
        states.push(s);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State s){
        states.pop().dispose();
        states.push(s);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch s){
        states.peek().render(s);
    }
}
