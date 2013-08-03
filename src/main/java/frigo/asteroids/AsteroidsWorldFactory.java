
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
import frigo.asteroids.logic.AccelerationNullerSystem;
import frigo.asteroids.logic.InputSystem;
import frigo.asteroids.logic.MovementSystem;
import frigo.asteroids.logic.gravity.GravitySystem;
import frigo.asteroids.logic.gravity.NewtonianGravity;

public class AsteroidsWorldFactory {

    private static final double DENSITY = 3_000_000_000.0;
    private Random random = new Random();
    private World world;

    public World createWorld () {
        world = new World();
        addEntities();
        addLogics();
        return world;
    }

    private void addEntities () {
        world.addEntity(createSun());
        world.addEntity(createShip());
        for( int i = 0; i < 100; i++ ){
            world.addEntity(createAsteroid());
        }
        for( int i = 0; i < 5_000; i++ ){
            world.addEntity(createStar());
        }
    }

    private void addLogics () {
        world.addLogic(new AccelerationNullerSystem());
        world.addLogic(new InputSystem());
        world.addLogic(new GravitySystem(new NewtonianGravity()));
        world.addLogic(new MovementSystem());
    }

    private Entity createSun () {
        double size = 0.4;
        Entity entity = world.createEntity();
        entity.set(new Attractor());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(new Acceleration(0, 0));
        entity.set(new Velocity(0, 0));
        entity.set(new Position(0, 0));
        entity.set(new Renderable(size, "sun_512.png"));
        return entity;
    }

    private Entity createShip () {
        double size = 0.1;
        Entity entity = world.createEntity();
        entity.set(new PlayerControllable(0.3));
        entity.set(new Attractable());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(new Acceleration(0, 0));
        entity.set(new Velocity(0.2, 0));
        entity.set(new Position(0, 0.5));
        entity.set(new Renderable(size, "spaceship.png"));
        return entity;
    }

    private Entity createAsteroid () {
        double size = 0.05;
        double speed = 0.2;
        Entity entity = world.createEntity();
        entity.set(new Attractable());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(new Acceleration(0, 0));
        entity.set(new Velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
        entity.set(new Position(getRandom(-1, 1), getRandom(-1, 1)));
        entity.set(new Renderable(size, "vesta.png"));
        return entity;
    }

    private Entity createStar () {
        double size = 1.0;
        double speed = 0.005;
        Entity entity = world.createEntity();
        entity.set(new Velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
        entity.set(new Position(getRandom(-1, 1), getRandom(-1, 1)));
        entity.set(new Renderable(size, 1.0, 1.0, 1.0));
        return entity;
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

}
