package mobanw.birds.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import mobanw.birds.game.SuperBirdsMain;
import mobanw.birds.game.gameObjects.BirdController;
import mobanw.birds.game.gameObjects.PipeController;

public class PlayState extends State {

    private BirdController bird;
    private Texture background;

    public static final int minimalSpaceBetweenPipes=130;
    public static final int maxPipesAmount=4;

    private Array<PipeController> pipes;

    public PlayState(GameStateManager g) {
        super(g);
        bird = new BirdController(60,250);
        background = new Texture("bigBackground.jpg");
        cam.setToOrtho(false, SuperBirdsMain.width/2,SuperBirdsMain.height/2);

        pipes = new Array<PipeController>();
        for(int i = 0; i < maxPipesAmount; i++){
            pipes.add(new PipeController(i*(minimalSpaceBetweenPipes+PipeController.pipeWidth)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.fly();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x+80;


        for(PipeController p : pipes){
            if(cam.position.x - (cam.viewportWidth/2) > p.getPositionTopPipe().x + p.getTopPipe().getWidth()){
                p.movePipes(p.getPositionTopPipe().x + ((p.pipeWidth + minimalSpaceBetweenPipes)*maxPipesAmount ));
            }
            if(p.detectCollision(bird.getBirdCollider())){
                gsm.set(new PlayState(gsm));
            }
        }

        cam.update();

    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x - (cam.viewportWidth/2),0);
        sb.draw(bird.getTextureBird(),bird.getPosition().x,bird.getPosition().y,bird.getTextureBird().getWidth()/7,bird.getTextureBird().getHeight()/7);
        for(PipeController p : pipes){
            sb.draw(p.getTopPipe(),p.getPositionTopPipe().x,p.getPositionTopPipe().y,p.getTopPipe().getWidth()/2,p.getTopPipe().getHeight()/2);
            sb.draw(p.getBottomPipe(),p.getPositionBottomPipe().x,p.getPositionBottomPipe().y,p.getBottomPipe().getWidth()/2,p.getBottomPipe().getHeight()/2);
        }
        sb.end();

    }

    @Override
    protected void dispose() {

    }
}
