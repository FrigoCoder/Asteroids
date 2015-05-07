
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
import frigo.asteroids.component.Damage;
import frigo.asteroids.component.Health;
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
import frigo.asteroids.logic.collision.CollisionAction;
import frigo.asteroids.logic.collision.CollisionDetectionSystem;
import frigo.asteroids.logic.gravity.GravitySystem;
import frigo.asteroids.logic.gravity.NewtonianGravity;
import frigo.asteroids.logic.input.InputAction;
import frigo.asteroids.logic.input.InputSystem;
import frigo.asteroids.logic.movement.MovementSystem;
import frigo.asteroids.logic.rotation.RotationSystem;
import frigo.asteroids.logic.selfdestruct.SelfDestructSystem;
import frigo.asteroids.logic.timer.TimerSystem;

public class AsteroidsWorldFactory {

    public static final double DENSITY = 5_000_000_000.0;
    private Random random = new Random();
    private World world;
    private Entity ship;

    public World createWorld () {
        world = new World();

        world.register(Angular.class);
        world.register(Attractable.class);
        world.register(Attractor.class);
        world.register(Background.class);
        world.register(Damage.class);
        world.register(Health.class);
        world.register(Image.class);
        world.register(Mass.class);
        world.register(Planar.class);
        world.register(SelfDestruct.class);
        world.register(Size.class);
        world.register(Thrustable.class);
        world.register(Timer.class);

        for( int i = 0; i < 5_000; i++ ){
            createStar();
        }
        for( int i = 0; i < 100; i++ ){
            createAsteroid(vector(getRandom(-1, 1), getRandom(-1, 1)));
        }
        createSun();
        createBlueSun();
        ship = createShip();
        world.addLogic(createInputSystem());
        world.addLogic(new TimerSystem());
        world.addLogic(new GravitySystem(new NewtonianGravity()));
        world.addLogic(new RotationSystem());
        world.addLogic(new MovementSystem());
        world.addLogic(createCollisionDetectionSystem());
        world.addLogic(new SelfDestructSystem());
        return world;
    }

    private Entity createStar () {
        double size = getRandom(0.005, 0.05);
        Entity entity = world.createEntity();
        entity.setFlag(Background.ID);
        entity.set(Image.ID, new Image("star64.png", -1));
        entity.set(Planar.ID, new Planar(getRandomVector(2, 1), getRandomVector(0.005), ZERO));
        entity.setDouble(Size.ID, size);
        return entity;
    }

    private Entity createAsteroid (Vector position) {
        double size = getRandom(0.01, 0.04);
        double mass = getMass(size);
        Entity entity = world.createEntity();
        entity.set(Angular.ID, new Angular(0, getRandom(-PI, PI), 0));
        entity.setFlag(Attractable.ID);
        entity.setDouble(Health.ID, mass);
        entity.set(Image.ID, new Image("vesta.png", 0));
        entity.setDouble(Mass.ID, mass);
        entity.set(Planar.ID, new Planar(position, getRandomVector(0.2), ZERO));
        entity.setDouble(Size.ID, size);
        return entity;
    }

    private Entity createSun () {
        double size = 0.2;
        double mass = getMass(size);
        Entity entity = world.createEntity();
        entity.set(Angular.ID, new Angular(0, 0.01, 0));
        entity.setFlag(Attractor.ID);
        entity.setDouble(Health.ID, mass);
        entity.set(Image.ID, new Image("sun.png", 1));
        entity.setDouble(Mass.ID, mass);
        entity.set(Planar.ID, new Planar(ZERO, ZERO, ZERO));
        entity.setDouble(Size.ID, size);
        return entity;
    }

    private Entity createBlueSun () {
        double size = 0.1;
        double mass = getMass(size);
        Entity entity = world.createEntity();
        entity.set(Angular.ID, new Angular(0, 0.01, 0));
        entity.setFlag(Attractor.ID);
        entity.setDouble(Health.ID, mass);
        entity.set(Image.ID, new Image("sun_blue.png", 1));
        entity.set(Planar.ID, new Planar(vector(1.0, 1.0), ZERO, ZERO));
        entity.setDouble(Size.ID, size);
        entity.setDouble(Mass.ID, mass);
        return entity;
    }

    private Entity createShip () {
        double size = 0.05;
        double mass = getMass(size);
        Entity entity = world.createEntity();
        entity.set(Angular.ID, new Angular(0, 0.5, 0));
        entity.setFlag(Attractable.ID);
        entity.set(Image.ID, new Image("spaceship.png", 2));
        entity.set(Planar.ID, new Planar(vector(0, 0.5), vector(0.2, 0), ZERO));
        entity.setDouble(Size.ID, size);
        entity.setDouble(Mass.ID, mass);
        entity.set(Thrustable.ID, new Thrustable(0.1, 1));
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

    private InputAction accelerateLeft = elapsedSeconds -> {
        Angular angular = ship.get(Angular.ID);
        Thrustable thrustable = ship.get(Thrustable.ID);
        angular.accelerate(thrustable.angularThrust);
    };

    private InputAction accelerateRight = elapsedSeconds -> {
        Angular angular = ship.get(Angular.ID);
        Thrustable thrustable = ship.get(Thrustable.ID);
        angular.accelerate(-thrustable.angularThrust);
    };

    private InputAction accelerateShip = elapsedSeconds -> {
        Thrustable thrustable = ship.get(Thrustable.ID);
        Vector thrust = getHeading(ship).mul(thrustable.thrust);
        Planar planar = ship.get(Planar.ID);
        planar.accelerate(thrust);
    };

    private InputAction createFlame = elapsedSeconds -> {
        double size = getRandom(0.01, 0.02);
        double mass = getMass(size);

        Entity flame = world.createEntity();

        double relativeAngularVelocity = getRandom(-PI, PI);
        Angular angular = ship.get(Angular.ID);
        flame.set(Angular.ID, new Angular(angular.position, angular.velocity + relativeAngularVelocity,
            angular.acceleration));

        flame.setFlag(Attractable.ID);
        flame.set(Image.ID, new Image("exhaust.png"));
        flame.setDouble(Size.ID, size);
        flame.setDouble(Mass.ID, mass);
        flame.set(Timer.ID, new Timer(3.0, (x, entity) -> {
            entity.setFlag(SelfDestruct.ID);
        }));

        Thrustable thrustable = ship.get(Thrustable.ID);
        Vector thrust = getHeading(ship).mul(thrustable.thrust);
        Vector relativeVelocity = thrust.opposite().rotate(getRandom(-PI / 6, PI / 6)).mul(getRandom(0.9, 1.1));
        Planar planar = ship.get(Planar.ID);
        flame.set(Planar.ID, new Planar(planar.position, planar.velocity.add(relativeVelocity), planar.acceleration));

    };

    private InputAction createBullet = elapsedSeconds -> {
        double size = 0.02;
        double mass = getMass(size);
        double damage = mass;

        Entity bullet = world.createEntity();

        double relativeAngularPosition = getRandom(-PI / 6, PI / 6);

        Angular angular = ship.get(Angular.ID);
        bullet.set(Angular.ID, new Angular(angular.position + relativeAngularPosition, angular.velocity,
            angular.acceleration));

        bullet.setFlag(Attractable.ID);
        bullet.setDouble(Damage.ID, damage);
        bullet.set(Image.ID, new Image("missile.png"));
        bullet.setDouble(Size.ID, size);
        bullet.setDouble(Mass.ID, mass);
        bullet.set(Timer.ID, new Timer(10.0, (x, entity) -> {
            entity.setFlag(SelfDestruct.ID);
        }));

        Vector relativeVelocity = getHeading(ship).mul(0.2).rotate(relativeAngularPosition);
        Planar planar = ship.get(Planar.ID);
        bullet.set(Planar.ID, new Planar(planar.position, planar.velocity.add(relativeVelocity), planar.acceleration));
    };

    private Vector getHeading (Entity entity) {
        Angular angular = entity.get(Angular.ID);
        double angle = angular.position;
        return Vector.Y.rotate(angle);
    }

    private CollisionDetectionSystem createCollisionDetectionSystem () {
        CollisionDetectionSystem collisionSystem = new CollisionDetectionSystem();
        collisionSystem.register(collisionAction);
        return collisionSystem;
    }

    private CollisionAction collisionAction = new CollisionAction() {

        @Override
        public void collision (Entity attacker, Entity attacked) {

            double damage = attacker.getDouble(Damage.ID);
            explode(attacker);

            double health = attacked.getDouble(Health.ID);
            health -= damage;
            attacked.setDouble(Health.ID, health);

            if( health <= 0 ){
                explode(attacked);
            }
        }

        private void explode (Entity entity) {
            entity.setFlag(SelfDestruct.ID);
            double mass = entity.getDouble(Mass.ID);
            while( mass > 0 ){
                Entity flame = createDebris(entity);
                mass -= flame.getDouble(Mass.ID);
            }
        }

        private Entity createDebris (Entity source) {
            double size = getRandom(0.008, 0.01);
            double mass = getMass(size);

            Entity debris = world.createEntity();

            double relativeAngularVelocity = getRandom(-PI, PI);
            Angular sourceAngular = source.get(Angular.ID);
            debris.set(Angular.ID, new Angular(sourceAngular.position, relativeAngularVelocity, 0));

            debris.setFlag(Attractable.ID);
            debris.set(Image.ID, new Image("exhaust.png"));
            debris.setDouble(Size.ID, size);
            debris.setDouble(Mass.ID, mass);
            debris.set(Timer.ID, new Timer(getRandom(5, 10), (x, entity) -> {
                entity.setFlag(SelfDestruct.ID);
            }));
            Vector relativeVelocity = Vector.vector(getGaussian(0.05), getGaussian(0.05));
            Planar sourcePlanar = source.get(Planar.ID);
            debris.set(Planar.ID, new Planar(sourcePlanar.position, relativeVelocity, ZERO));

            return debris;
        }

    };

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    private double getGaussian (double scale) {
        return random.nextGaussian() * scale;
    }

    private Vector getRandomVector (double size) {
        return vector(getRandom(-size, size), getRandom(-size, size));
    }

    private Vector getRandomVector (double x, double y) {
        return vector(getRandom(-x, x), getRandom(-y, y));
    }

    private double getMass (double size) {
        return PI * 4 / 3 * pow(size, 3) * DENSITY;
    }

}
