
package frigo.asteroids;

import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.Random;

import com.jogamp.newt.event.KeyEvent;

import frigo.asteroids.component.Angular;
import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Background;
import frigo.asteroids.component.Image;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.SelfDestruct;
import frigo.asteroids.component.Size;
import frigo.asteroids.component.Thrustable;
import frigo.asteroids.component.Timer;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Vector;
import frigo.asteroids.core.World;
import frigo.asteroids.logic.InputAction;
import frigo.asteroids.logic.InputSystem;
import frigo.asteroids.logic.SelfDestructSystem;
import frigo.asteroids.logic.TimerSystem;
import frigo.asteroids.logic.gravity.GravitySystem;
import frigo.asteroids.logic.gravity.NewtonianGravity;
import frigo.asteroids.logic.movement.MovementSystem;
import frigo.asteroids.logic.rotation.RotationSystem;

public class AsteroidsWorldFactory {

    public static final double DENSITY = 3_000_000_000.0;
    private Random random = new Random();
    private World world;
    private Entity ship;

    public World createWorld () {
        world = new World();
        for( int i = 0; i < 5_000; i++ ){
            createStar();
        }
        for( int i = 0; i < 100; i++ ){
            createAsteroid(vector(getRandom(-1, 1), getRandom(-1, 1)));
        }
        createSun();
        createSun2();
        ship = createShip();
        world.addLogic(createInputSystem());
        world.addLogic(new TimerSystem());
        world.addLogic(new GravitySystem(new NewtonianGravity()));
        world.addLogic(new RotationSystem());
        world.addLogic(new MovementSystem());
        world.addLogic(new SelfDestructSystem());
        return world;
    }

    private Entity createStar () {
        Entity entity = world.createEntity();
        entity.add(Background.BACKGROUND);
        entity.add(new Image("star64.png", -1));
        entity.add(new Planar(getRandomVector(2, 1), getRandomVector(0.005), ZERO));
        setSize(entity, getRandom(0.01, 0.1));
        return entity;
    }

    private Entity createAsteroid (Vector position) {
        Entity entity = world.createEntity();
        entity.add(new Angular(0, getRandom(-PI, PI), 0));
        entity.add(Attractable.ATTRACTABLE);
        entity.add(new Image("vesta.png", 0));
        entity.add(new Planar(position, getRandomVector(0.2), ZERO));
        setSize(entity, getRandom(0.02, 0.08));
        return entity;
    }

    private Entity createSun () {
        Entity entity = world.createEntity();
        entity.add(new Angular(0, 0.01, 0));
        entity.add(Attractor.ATTRACTOR);
        entity.add(new Image("sun.png", 1));
        entity.add(new Planar(ZERO, ZERO, ZERO));
        setSize(entity, 0.4);
        return entity;
    }

    private Entity createSun2 () {
        Entity entity = world.createEntity();
        entity.add(new Angular(0, 0.01, 0));
        entity.add(Attractor.ATTRACTOR);
        entity.add(new Image("sun.png", 1));
        entity.add(new Planar(vector(1.0, 1.0), ZERO, ZERO));
        setSize(entity, 0.2);
        return entity;
    }

    private Entity createShip () {
        Entity entity = world.createEntity();
        entity.add(new Angular(0, 0.5, 0));
        entity.add(Attractable.ATTRACTABLE);
        entity.add(new Image("spaceship.png", 2));
        entity.add(new Planar(vector(0, 0.5), vector(0.2, 0), ZERO));
        setSize(entity, 0.1);
        entity.add(new Thrustable(0.1, 1));
        return entity;
    }

    private InputSystem createInputSystem () {
        InputSystem inputSystem = new InputSystem();
        inputSystem.register(KeyEvent.VK_LEFT, accelerateLeft);
        inputSystem.register(KeyEvent.VK_RIGHT, accelerateRight);
        inputSystem.register(KeyEvent.VK_UP, accelerateShip);
        inputSystem.register(KeyEvent.VK_UP, createFlame);

        inputSystem.register(KeyEvent.VK_SPACE, createBullet);
        return inputSystem;
    }

    private InputAction accelerateLeft = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            ship.get(Angular.class).accelerate(ship.get(Thrustable.class).angularThrust);
        }
    };

    private InputAction accelerateRight = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            ship.get(Angular.class).accelerate(-ship.get(Thrustable.class).angularThrust);
        }

    };

    private InputAction accelerateShip = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            Vector thrust = getHeading(ship).mul(ship.get(Thrustable.class).thrust);

            Planar planar = ship.get(Planar.class);
            planar.accelerate(thrust);
        }

    };

    private InputAction createFlame = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            Entity entity = world.createEntity();
            entity.add(Attractable.ATTRACTABLE);
            entity.add(new Image("exhaust.png"));
            setSize(entity, getRandom(0.02, 0.04));
            entity.add(new Timer(SelfDestruct.SELF_DESTRUCT, 2.0));

            Vector thrust = getHeading(ship).mul(ship.get(Thrustable.class).thrust);

            Vector relativeVelocity = thrust.opposite().add(getRandomVector(0.05));
            Planar planar = ship.get(Planar.class);
            entity.add(new Planar(planar.position, planar.velocity.add(relativeVelocity), planar.acceleration));

            double relativeAngularVelocity = getRandom(-PI, PI);
            Angular angular = ship.get(Angular.class);
            entity.add(new Angular(angular.position, angular.velocity + relativeAngularVelocity, angular.acceleration));
        }

    };

    private InputAction createBullet = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            Entity entity = spawnAtSource(ship);
            entity.add(Attractable.ATTRACTABLE);
            entity.add(new Image("missile.png"));
            setSize(entity, 0.05);
            entity.add(new Timer(SelfDestruct.SELF_DESTRUCT, 10.0));

            Vector relativeVelocity = getHeading(ship).mul(0.2);
            Planar planar = ship.get(Planar.class);
            entity.add(new Planar(planar.position, planar.velocity.add(relativeVelocity), planar.acceleration));
        }

    };

    private Entity spawnAtSource (Entity source) {
        Planar planar = (Planar) source.get(Planar.class).clone();
        Angular angular = (Angular) source.get(Angular.class).clone();

        Entity entity = world.createEntity();
        entity.add(planar);
        entity.add(angular);
        return entity;
    }

    private Vector getHeading (Entity entity) {
        double angle = entity.get(Angular.class).position;
        return Vector.Y.rotate(angle);
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    private Vector getRandomVector (double size) {
        return vector(getRandom(-size, size), getRandom(-size, size));
    }

    private Vector getRandomVector (double x, double y) {
        return vector(getRandom(-x, x), getRandom(-y, y));
    }

    private void setSize (Entity entity, double size) {
        entity.add(new Size(size));
        entity.add(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
    }
}
