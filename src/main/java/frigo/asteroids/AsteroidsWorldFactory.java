
package frigo.asteroids;

import static frigo.asteroids.core.Vector.NULL;
import static frigo.asteroids.core.Vector.UNIT_Y;
import static frigo.asteroids.core.Vector.vector;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.Random;

import com.jogamp.newt.event.KeyEvent;

import frigo.asteroids.component.Angular;
import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Point;
import frigo.asteroids.component.SelfDestruct;
import frigo.asteroids.component.Size;
import frigo.asteroids.component.TextureName;
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
        createSun();
        ship = createShip();
        for( int i = 0; i < 50; i++ ){
            createAsteroid(vector(getRandom(-1, 1), getRandom(-1, 1)));
        }
        for( int i = 0; i < 20_000; i++ ){
            createStar();
        }
        world.addLogic(createInputSystem());
        world.addLogic(new TimerSystem());
        world.addLogic(new GravitySystem(new NewtonianGravity()));
        world.addLogic(new RotationSystem());
        world.addLogic(new MovementSystem());
        world.addLogic(new SelfDestructSystem());
        return world;
    }

    private Entity createShip () {
        double size = 0.1;
        Entity entity = world.createEntity();
        entity.add(new Thrustable(0.1, 1));
        entity.add(Attractable.ATTRACTABLE);
        entity.add(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.add(new Planar(vector(0, 0.5), vector(0.2, 0), NULL));
        entity.add(new Angular(0, 0.5, 0));
        entity.add(new Size(size));
        entity.add(new TextureName("spaceship.png"));
        return entity;
    }

    private Entity createSun () {
        double size = 0.4;
        Entity entity = world.createEntity();
        entity.add(Attractor.ATTRACTOR);
        entity.add(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.add(new Planar(NULL, NULL, NULL));
        entity.add(new Angular(0, 0.01, 0));
        entity.add(new Size(size));
        entity.add(new TextureName("sun.png"));
        return entity;
    }

    private Entity createAsteroid (Vector position) {
        double size = getRandom(0.02, 0.08);
        double speed = 0.2;
        Entity entity = world.createEntity();
        entity.add(Attractable.ATTRACTABLE);
        entity.add(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.add(new Planar(position, vector(getRandom(-speed, speed), getRandom(-speed, speed)), NULL));
        entity.add(new Angular(0, getRandom(-PI, PI), 0));
        entity.add(new Size(size));
        entity.add(new TextureName("vesta.png"));
        return entity;
    }

    private Entity createStar () {
        double size = 0.01;
        double speed = 0.005;
        Entity entity = world.createEntity();
        entity.add(new Planar(vector(getRandom(-2, 2), getRandom(-1, 1)), vector(getRandom(-speed, speed), getRandom(
            -speed, speed)), NULL));
        entity.add(new Size(size));
        entity.add(Point.POINT);
        return entity;
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    private InputSystem createInputSystem () {
        InputSystem inputSystem = new InputSystem();
        inputSystem.register(KeyEvent.VK_UP, handleAcceleration);
        inputSystem.register(KeyEvent.VK_LEFT, handleLeft);
        inputSystem.register(KeyEvent.VK_RIGHT, handleRight);
        inputSystem.register(KeyEvent.VK_SPACE, handleShooting);
        return inputSystem;
    }

    private InputAction handleAcceleration = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            double angle = ship.get(Angular.class).position;
            Vector heading = UNIT_Y.opposite().rotate(angle).mul(ship.get(Thrustable.class).thrust);
            Planar planar = ship.get(Planar.class);
            planar.accelerate(heading);
            createFlame(planar.position, planar.velocity.sub(heading));
        }

        private Entity createFlame (Vector position, Vector velocity) {
            double size = getRandom(0.02, 0.04);
            double spread = 0.05;
            Entity entity = world.createEntity();
            entity.add(Attractable.ATTRACTABLE);
            entity.add(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
            entity.add(new Planar(position, vector(getRandom(-spread, spread) + velocity.x, getRandom(-spread, spread)
                + velocity.y), NULL));
            entity.add(new Angular(0, getRandom(-PI, PI), 0));
            entity.add(new Size(size));
            entity.add(new TextureName("exhaust.png"));
            entity.add(new Timer(SelfDestruct.SELF_DESTRUCT, 2.0));
            return entity;
        }

    };

    private InputAction handleLeft = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            ship.get(Angular.class).accelerate(ship.get(Thrustable.class).angularThrust);
        }
    };

    private InputAction handleRight = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            ship.get(Angular.class).accelerate(-ship.get(Thrustable.class).angularThrust);
        }

    };

    private InputAction handleShooting = new InputAction() {

        @Override
        public void run (double elapsedSeconds) {
            createAsteroid(ship.get(Planar.class).position);
        }

    };

}
