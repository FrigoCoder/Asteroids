
package frigo.asteroids;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.Random;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.AngularAcceleration;
import frigo.asteroids.component.AngularDisplacement;
import frigo.asteroids.component.AngularVelocity;
import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Point;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Size;
import frigo.asteroids.component.TextureName;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.ArrayComponentStorageFactory;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;
import frigo.asteroids.logic.AccelerationNullerSystem;
import frigo.asteroids.logic.InputSystem;
import frigo.asteroids.logic.gravity.GravitySystem;
import frigo.asteroids.logic.gravity.NewtonianGravity;
import frigo.asteroids.logic.movement.MovementSystem;
import frigo.asteroids.logic.rotation.RotationSystem;

public class AsteroidsWorldFactory {

    private static final double DENSITY = 3_000_000_000.0;
    private Random random = new Random();
    private World world;

    public World createWorld () {
        world = new World(new ArrayComponentStorageFactory());
        addEntities();
        addLogics();
        return world;
    }

    private void addEntities () {
        createSun();
        createShip();
        for( int i = 0; i < 100; i++ ){
            createAsteroid();
        }
        for( int i = 0; i < 20_000; i++ ){
            createStar();
        }
    }

    private void addLogics () {
        world.addLogic(new AccelerationNullerSystem());
        world.addLogic(new InputSystem());
        world.addLogic(new RotationSystem());
        world.addLogic(new GravitySystem(new NewtonianGravity(world)));
        world.addLogic(new MovementSystem());
    }

    private Entity createSun () {
        double size = 0.4;
        Entity entity = world.createEntity();
        world.set(entity, new Attractor());
        world.set(entity, new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        world.set(entity, new Acceleration(0, 0));
        world.set(entity, new Velocity(0, 0));
        world.set(entity, new Position(0, 0));
        world.set(entity, new Size(size));
        world.set(entity, new TextureName("sun.png"));
        return entity;
    }

    private Entity createShip () {
        double size = 0.1;
        Entity entity = world.createEntity();
        world.set(entity, new PlayerControllable(0.1, 1.0));
        world.set(entity, new Attractable());
        world.set(entity, new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        world.set(entity, new Acceleration(0, 0));
        world.set(entity, new Velocity(0.2, 0));
        world.set(entity, new Position(0, 0.5));
        world.set(entity, new AngularAcceleration(0));
        world.set(entity, new AngularVelocity(0));
        world.set(entity, new AngularDisplacement(0));
        world.set(entity, new Size(size));
        world.set(entity, new TextureName("spaceship.png"));
        return entity;
    }

    private Entity createAsteroid () {
        double size = 0.05;
        double speed = 0.2;
        Entity entity = world.createEntity();
        world.set(entity, new Attractable());
        world.set(entity, new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        world.set(entity, new Acceleration(0, 0));
        world.set(entity, new Velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
        world.set(entity, new Position(getRandom(-1, 1), getRandom(-1, 1)));
        world.set(entity, new AngularVelocity(getRandom(-PI, PI)));
        world.set(entity, new AngularDisplacement(0));
        world.set(entity, new Size(size));
        world.set(entity, new TextureName("vesta.png"));
        return entity;
    }

    private Entity createStar () {
        double size = 0.01;
        double speed = 0.005;
        Entity entity = world.createEntity();

        world.set(entity, new Velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
        world.set(entity, new Position(getRandom(-2, 2), getRandom(-1, 1)));
        world.set(entity, new Size(size));
        world.set(entity, new Point());
        return entity;
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

}
