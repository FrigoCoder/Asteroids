
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
        addPlanet();
        addShip();
        addAsteroids();
        addStars();
        addLogics();
    }

    private void addPlanet () {
        Entity planet = new Entity();
        double size = 50;
        planet.set(new Mass(pow(size, 3) * PI));
        planet.set(new Acceleration(0, 0));
        planet.set(new Velocity(0, 0));
        planet.set(new Position(0, 0));
        planet.set(new Renderable(size));
        world.addEntity(planet);
    }

    private void addShip () {
        Entity ship = new Entity();
        double size = 10;
        ship.set(new Mass(pow(size, 3) * PI));
        ship.set(new PlayerControllable());
        ship.set(new Acceleration(0, 0));
        ship.set(new Velocity(0, 0));
        ship.set(new Position(0, 0.5));
        ship.set(new Renderable(size));
        world.addEntity(ship);
    }

    private void addAsteroids () {
        for( int i = 0; i < 10; i++ ){
            Entity asteroid = new Entity();
            double size = 5;
            double speed = 0.2;
            asteroid.set(new Mass(pow(size, 3) * PI));
            asteroid.set(new Acceleration(0, 0));
            asteroid.set(new Velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
            asteroid.set(new Position(getRandom(-1, 1), getRandom(-1, 1)));
            asteroid.set(new Renderable(size));
            world.addEntity(asteroid);
        }
    }

    private void addStars () {
        for( int i = 0; i < 100; i++ ){
            Entity asteroid = new Entity();
            asteroid.set(new Position(getRandom(-1, 1), getRandom(-1, 1)));
            asteroid.set(new Renderable(1));
            world.addEntity(asteroid);
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
