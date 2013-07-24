
package frigo.asteroids;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.Random;

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
import frigo.asteroids.jogl.JOGLRunner;
import frigo.asteroids.logic.AccelerationNullerSystem;
import frigo.asteroids.logic.GravitySystem;
import frigo.asteroids.logic.InputSystem;
import frigo.asteroids.logic.MovementSystem;

public class Game {

    private static final int DENSITY = 100;
    private World world = new World();
    private Random random = new Random();

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
        double size = 100;
        Entity entity = new Entity();
        entity.set(new Attractor());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(new Position(0, 0));
        entity.set(new Renderable(size, 1, 1, 0));
        world.addEntity(entity);
    }

    private void addShip () {
        double size = 10;
        Entity entity = new Entity();
        entity.set(new PlayerControllable(1));
        entity.set(new Attractable());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(new Acceleration(0, 0));
        entity.set(new Velocity(0, 0));
        entity.set(new Position(0, 0.5));
        entity.set(new Renderable(size, 1, 0, 0));
        world.addEntity(entity);
    }

    private void addAsteroids () {
        for( int i = 0; i < 100; i++ ){
            double size = 10;
            double speed = 0.2;
            Entity entity = new Entity();
            entity.set(new Attractable());
            entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
            entity.set(new Acceleration(0, 0));
            entity.set(new Velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
            entity.set(new Position(getRandom(-1, 1), getRandom(-1, 1)));
            entity.set(new Renderable(size, 0.75, 0.75, 0.75));
            world.addEntity(entity);
        }
    }

    private void addStars () {
        for( int i = 0; i < 5_000; i++ ){
            double size = 1;
            double speed = 0;
            Entity entity = new Entity();
            entity.set(new Attractable());
            entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
            entity.set(new Acceleration(0, 0));
            entity.set(new Velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
            entity.set(new Position(getRandom(-1, 1), getRandom(-1, 1)));
            entity.set(new Renderable(size));
            world.addEntity(entity);
        }
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    private void addLogics () {
        world.addLogic(new InputSystem());
        world.addLogic(new GravitySystem());
        world.addLogic(new MovementSystem());
        world.addLogic(new AccelerationNullerSystem());
    }

    public static void main (String[] args) {
        Game game = new Game();
        JOGLRunner runner = new JOGLRunner(game.world, 100);
        runner.start();
    }

}
