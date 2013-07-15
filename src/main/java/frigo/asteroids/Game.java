
package frigo.asteroids;

import static frigo.asteroids.util.Rethrow.unchecked;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Renderable;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;
import frigo.asteroids.logic.AccelerationNullerSystem;
import frigo.asteroids.logic.GravitySystem;
import frigo.asteroids.logic.InputSystem;
import frigo.asteroids.logic.MovementSystem;
import frigo.asteroids.logic.Renderer;
import frigo.asteroids.util.BooleanLatch;

public class Game implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private static final int FPS = 100;
    private static final int DELAY = 1000 / FPS;

    private World world = new World();
    private Random random = new Random();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private boolean initialized;
    private BooleanLatch finished = new BooleanLatch();

    public Game () {
        addEntities();
        addLogics();
    }

    private void addEntities () {
        addSun();
        addShip();
        addAsteroids();
        addStars();
    }

    private void addSun () {
        Entity entity = new Entity();
        double size = 100;
        double density = 1000;
        entity.set(new Attractor());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * density));
        entity.set(new Position(0, 0));
        entity.set(new Renderable(size, 1.0, 1.0, 0.0));
        world.addEntity(entity);
    }

    private void addShip () {
        Entity entity = new Entity();
        double size = 10;
        double density = 1000;
        entity.set(new PlayerControllable());
        entity.set(new Attractable());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * density));
        entity.set(new Acceleration(0, 0));
        entity.set(new Velocity(0, 0));
        entity.set(new Position(0, 0.5));
        entity.set(new Renderable(size, 1.0, 0.0, 0.0));
        world.addEntity(entity);
    }

    private void addAsteroids () {
        for( int i = 0; i < 100; i++ ){
            Entity entity = new Entity();
            double size = 10;
            double density = 1000;
            double speed = 0.2;
            entity.set(new Attractable());
            entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * density));
            entity.set(new Acceleration(0, 0));
            entity.set(new Velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
            entity.set(new Position(getRandom(-1, 1), getRandom(-1, 1)));
            entity.set(new Renderable(size, 0.75, 0.75, 0.75));
            world.addEntity(entity);
        }
    }

    private void addStars () {
        for( int i = 0; i < 5_000; i++ ){
            Entity entity = new Entity();
            entity.set(new Position(getRandom(-1, 1), getRandom(-1, 1)));
            entity.set(new Renderable(1));
            world.addEntity(entity);
        }
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    private void addLogics () {
        world.addLogic(new AccelerationNullerSystem());
        world.addLogic(new InputSystem());
        world.addLogic(new GravitySystem());
        world.addLogic(new MovementSystem());
        world.addLogic(new Renderer());
    }

    public void start () throws Exception {
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(this, 0, DELAY, TimeUnit.MILLISECONDS);
        finished.await();
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        try{
            future.get();
        }catch( CancellationException e ){
            LOGGER.info("Cancelled: ", e);
        }
    }

    private double lastMillis;

    @Override
    public void run () {
        try{
            if( !initialized ){
                lastMillis = System.nanoTime();
                world.init();
                initialized = true;
            }
            double currentMillis = System.nanoTime();
            world.update((currentMillis - lastMillis) / 1_000_000_000.0);
            lastMillis = currentMillis;
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
