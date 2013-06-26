
package frigo.asteroids;

import static frigo.asteroids.util.Rethrow.unchecked;

import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import frigo.asteroids.components.Position;
import frigo.asteroids.components.Renderable;
import frigo.asteroids.components.Speed;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;
import frigo.asteroids.logics.MovementSystem;
import frigo.asteroids.logics.Renderer;
import frigo.asteroids.util.BooleanLatch;

public class Game implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

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
            ball.add(new Speed(getRandom(-0.01, 0.01), getRandom(-0.01, 0.01)));
            ball.add(new Position(getRandom(-1, 1), getRandom(-1, 1)));
            ball.add(new Renderable());
            world.addEntity(ball);
        }
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    private void addLogics () {
        world.addLogic(new MovementSystem());
        world.addLogic(new Renderer());
    }

    public void start () throws Exception {
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(this, 0, 10, TimeUnit.MILLISECONDS);
        finished.await();
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        try{
            future.get();
        }catch( CancellationException e ){
            LOGGER.info("Cancelled: ", e);
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

    public static void main (String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }

}
