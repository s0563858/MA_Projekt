package mobanw.birds.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class BirdController {
    private Vector3 position;
    private Vector3 velocity;
    public static final int gravity=-15;
    public static final int movement=90;

    private Rectangle birdCollider;

    private Texture textureBird;


    public Texture getTextureBird() {
        return textureBird;
    }

    public Vector3 getPosition() {
        return position;
    }

    public BirdController(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        textureBird = new Texture("bird.png");
        birdCollider = new Rectangle(x,y,50,50);
    }


    public void update(float dt){
        if(position.y > 0){
            velocity.add(0,gravity,0);
        }
        velocity.scl(dt);
        position.add(movement*dt,velocity.y,0);

        velocity.scl(1/dt);
        if(position.y < 0){
            position.y = 0;
        }
        birdCollider.setPosition(position.x,position.y);
    }

    public void fly(){
        velocity.y =230;
    }

    public Rectangle getBirdCollider() {
        return birdCollider;
    }
}
