
package frigo.asteroids;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.lwjgl.opengl.Display;

public class Game implements Runnable {

    public static void main (String[] args) throws InterruptedException, ExecutionException {
        Game game = new Game();
        game.start();
    }

    private World world = new World();
    private Random random = new Random();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private boolean initialized;

    public Game () {
        addBalls();
        addUpdaters();
    }

    private void addBalls () {
        for( int i = 0; i < 10; i++ ){
            Entity ball = new Entity();
            ball.add(new Speed(random(-0.01, 0.01), random(-0.01, 0.01)));
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

    public void start () throws InterruptedException, ExecutionException {
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(this, 0, 10, TimeUnit.MILLISECONDS);
        future.get();
    }

    @Override
    public void run () {
        if( !initialized ){
            world.init();
            initialized = true;
        }
        world.update();
        if( Display.isCloseRequested() ){
            executor.shutdown();
        }
    }

}
