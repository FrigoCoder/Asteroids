
package frigo.asteroids;

import static frigo.asteroids.component.Angular.angular;
import static frigo.asteroids.component.Planar.planar;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.Random;

import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Point;
import frigo.asteroids.component.Size;
import frigo.asteroids.component.TextureName;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;
import frigo.asteroids.logic.PlayerControllableBasedInputSystem;
import frigo.asteroids.logic.SelfDestructSystem;
import frigo.asteroids.logic.gravity.GravitySystem;
import frigo.asteroids.logic.gravity.NewtonianGravity;
import frigo.asteroids.logic.movement.MovementSystem;
import frigo.asteroids.logic.rotation.RotationSystem;

public class AsteroidsWorldFactory {

    public static final double DENSITY = 3_000_000_000.0;
    private Random random = new Random();
    private World world;

    public World createWorld () {
        world = new World();
        addEntities();
        addLogics();
        return world;
    }

    private void addEntities () {
        createSun();
        createShip();
        for( int i = 0; i < 50; i++ ){
            createAsteroid();
        }
        for( int i = 0; i < 20_000; i++ ){
            createStar();
        }
    }

    private void addLogics () {
        world.addLogic(new PlayerControllableBasedInputSystem());
        world.addLogic(new RotationSystem());
        world.addLogic(new GravitySystem(new NewtonianGravity(world)));
        world.addLogic(new MovementSystem());
        world.addLogic(new SelfDestructSystem());
    }

    private Entity createSun () {
        double size = 0.4;
        Entity entity = world.createEntity();
        entity.set(new Attractor());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(planar());
        entity.set(angular().velocity(0.01));
        entity.set(new Size(size));
        entity.set(new TextureName("sun.png"));
        return entity;
    }

    private Entity createShip () {
        double size = 0.1;
        Entity entity = world.createEntity();
        entity.set(new PlayerControllable(0.1, 1));
        entity.set(new Attractable());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(planar().position(0, 0.5).velocity(0.2, 0));
        entity.set(angular().velocity(0.5));
        entity.set(new Size(size));
        entity.set(new TextureName("spaceship.png"));
        return entity;
    }

    private Entity createAsteroid () {
        double size = getRandom(0.02, 0.08);
        double speed = 0.2;
        Entity entity = world.createEntity();
        entity.set(new Attractable());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(planar().position(getRandom(-1, 1), getRandom(-1, 1)).velocity(getRandom(-speed, speed),
            getRandom(-speed, speed)));
        entity.set(angular().velocity(getRandom(-PI, PI)));
        entity.set(new Size(size));
        entity.set(new TextureName("vesta.png"));
        return entity;
    }

    private Entity createStar () {
        double size = 0.01;
        double speed = 0.005;
        Entity entity = world.createEntity();
        entity.set(planar().position(getRandom(-2, 2), getRandom(-1, 1)).velocity(getRandom(-speed, speed),
            getRandom(-speed, speed)));
        entity.set(new Size(size));
        entity.set(new Point());
        return entity;
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

}
