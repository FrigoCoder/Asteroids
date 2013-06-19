
package frigo.asteroids;

import static frigo.asteroids.Rethrow.unchecked;

import java.util.Random;

import org.lwjgl.opengl.Display;

public class Game {

    public static void main (String[] args) {
        Game game = new Game();
        game.start();
    }

    private World world = new World();
    private Random random = new Random();

    public Game () {
        addBalls();
        addUpdaters();
    }

    private void addBalls () {
        for( int i = 0; i < 10; i++ ){
            Entity ball = new Entity();
            ball.add(new Speed(random(-1, 1), random(-1, 1)));
            ball.add(new Position(random(-1, 1), random(-1, 1)));
            world.addEntity(ball);
        }
    }

    private double random (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    private void addUpdaters () {
        world.addUpdater(new MovementSystem());
        world.addUpdater(new Renderer());
    }

    public void start () {
        while( !Display.isCloseRequested() ){
            run();
        }
    }

    public void run () {
        world.update();
        try{
            Thread.sleep(10);
        }catch( InterruptedException e ){
            throw unchecked(e);
        }

    }

}
