
package frigo.asteroids;

import static frigo.asteroids.Rethrow.unchecked;

import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    public static void main (String[] args) throws InterruptedException, ExecutionException {
        Game game = new Game();
        game.start();
    }

    private World world = new World();
    private Random random = new Random();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private boolean initialized;
    private BooleanLatch finished = new BooleanLatch();

    public Game () {
        addBalls();
        addLogics();
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

    private void addLogics () {
        world.addLogic(new MovementSystem());
        world.addLogic(new Renderer());
    }

    public void start () throws InterruptedException, ExecutionException {
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(this, 0, 10, TimeUnit.MILLISECONDS);
        finished.await();
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        try{
            future.get();
        }catch( CancellationException e ){
        }
    }

    @Override
    public void run () {
        try{
            if( !initialized ){
                world.init();
                initialized = true;
            }
            world.update();
        }catch( Exception e ){
            finished.release();
            throw unchecked(e);
        }
    }
}
