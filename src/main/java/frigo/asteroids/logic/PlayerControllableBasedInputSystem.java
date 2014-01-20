
package frigo.asteroids.logic;

import static frigo.asteroids.AsteroidsWorldFactory.DENSITY;
import static frigo.asteroids.component.Planar.planar;
import static frigo.asteroids.core.Vector.UNIT_Y;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.jogamp.newt.event.KeyEvent;

import frigo.asteroids.component.Angular;
import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.SelfDestruct;
import frigo.asteroids.component.Size;
import frigo.asteroids.component.TextureName;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.Vector;
import frigo.asteroids.core.World;
import frigo.asteroids.message.KeyHeld;
import frigo.asteroids.message.KeyMessage;
import frigo.asteroids.message.KeyPressed;

public class PlayerControllableBasedInputSystem extends Logic {

    private Aspect controllableAspect = Aspect.allOf(PlayerControllable.class, Planar.class, Angular.class);
    private Random random = new Random();

    @Override
    public void update (World world, double elapsedSeconds) {
        List<KeyMessage> messages = new LinkedList<>();
        messages.addAll(world.getMessages(KeyPressed.class));
        messages.addAll(world.getMessages(KeyHeld.class));

        for( Entity entity : world.getEntitiesFor(controllableAspect) ){
            PlayerControllable controllable = entity.get(PlayerControllable.class);

            for( KeyMessage message : messages ){
                switch( message.key ){
                    case KeyEvent.VK_UP:
                        handleAcceleration(world, entity, controllable);
                        break;
                    case KeyEvent.VK_LEFT:
                        entity.get(Angular.class).accelerate(controllable.angularThrust);
                        break;
                    case KeyEvent.VK_RIGHT:
                        entity.get(Angular.class).accelerate(-controllable.angularThrust);
                        break;
                    case KeyEvent.VK_SPACE:
                        Vector position = entity.get(Planar.class).position;
                        createAsteroid(world, position);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void handleAcceleration (World world, Entity entity, PlayerControllable controllable) {
        double angle = entity.get(Angular.class).position;
        Vector heading = UNIT_Y.opposite().rotate(angle).mul(controllable.thrust);
        Planar planar = entity.get(Planar.class).accelerate(heading);
        entity.set(planar);
        createFlame(world, planar.position, planar.velocity.sub(heading));
    }

    private Entity createAsteroid (World world, Vector position) {
        double size = getRandom(0.02, 0.08);
        double speed = 0.2;
        Entity entity = world.createEntity();
        entity.set(new Attractable());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(planar().position(position).velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
        entity.set(new Angular(0, getRandom(-PI, PI), 0));
        entity.set(new Size(size));
        entity.set(new TextureName("vesta.png"));
        return entity;
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    private Entity createFlame (World world, Vector position, Vector velocity) {
        double size = getRandom(0.02, 0.04);
        double spread = 0.05;
        Entity entity = world.createEntity();
        entity.set(new Attractable());
        entity.set(new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        entity.set(planar().position(position).velocity(getRandom(-spread, spread) + velocity.x,
            getRandom(-spread, spread) + velocity.y));
        entity.set(new Angular(0, getRandom(-PI, PI), 0));
        entity.set(new Size(size));
        entity.set(new TextureName("exhaust.png"));
        entity.set(new SelfDestruct(2.0));
        return entity;
    }

}
