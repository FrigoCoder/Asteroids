
package frigo.asteroids.logic;

import static frigo.asteroids.AsteroidsWorldFactory.DENSITY;
import static frigo.asteroids.component.Angular.angular;
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

public class InputSystem extends Logic {

    private Aspect controllableAspect = Aspect.allOf(PlayerControllable.class, Planar.class, Angular.class);
    private Random random = new Random();

    @Override
    public void update (World world, double elapsedSeconds) {
        List<KeyMessage> messages = new LinkedList<>();
        messages.addAll(world.getMessages(KeyPressed.class));
        messages.addAll(world.getMessages(KeyHeld.class));

        for( Entity entity : world.getEntitiesFor(controllableAspect) ){
            PlayerControllable controllable = world.get(entity, PlayerControllable.class);

            for( KeyMessage message : messages ){
                switch( message.key ){
                    case KeyEvent.VK_UP:
                        double angle = world.get(entity, Angular.class).position;
                        Vector heading = UNIT_Y.rotate(angle).mul(controllable.thrust);
                        world.set(entity, world.get(entity, Planar.class).accelerate(heading));
                        break;
                    case KeyEvent.VK_LEFT:
                        world.set(entity, world.get(entity, Angular.class).accelerate(controllable.angularThrust));
                        break;
                    case KeyEvent.VK_RIGHT:
                        world.set(entity, world.get(entity, Angular.class).accelerate(-controllable.angularThrust));
                        break;
                    case KeyEvent.VK_SPACE:
                        Vector position = world.get(entity, Planar.class).position;
                        createAsteroid(world, position);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private Entity createAsteroid (World world, Vector position) {
        double size = getRandom(0.02, 0.08);
        double speed = 0.2;
        Entity entity = world.createEntity();
        world.set(entity, new Attractable());
        world.set(entity, new Mass(PI * 4 / 3 * pow(size, 3) * DENSITY));
        world.set(entity, planar().position(position).velocity(getRandom(-speed, speed), getRandom(-speed, speed)));
        world.set(entity, angular().velocity(getRandom(-PI, PI)));
        world.set(entity, new Size(size));
        world.set(entity, new TextureName("vesta.png"));
        return entity;
    }

    private double getRandom (double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

}
