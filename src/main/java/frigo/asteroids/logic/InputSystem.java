
package frigo.asteroids.logic;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.newt.event.KeyEvent;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;
import frigo.asteroids.message.KeyHeld;
import frigo.asteroids.message.KeyMessage;
import frigo.asteroids.message.KeyPressed;

public class InputSystem implements Logic {

    private Aspect controllableAspect;

    @Override
    public void init (World world) {
        controllableAspect = Aspect.allOf(PlayerControllable.class, Acceleration.class);
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        Entity entity = world.getEntitiesFor(controllableAspect).iterator().next();
        PlayerControllable controllable = world.get(entity, PlayerControllable.class);

        double thrust = controllable.thrust;
        double rotation = controllable.rotation;

        List<KeyMessage> messages = new LinkedList<>();
        messages.addAll(world.getMessages(KeyPressed.class));
        messages.addAll(world.getMessages(KeyHeld.class));

        for( KeyMessage message : messages ){
            switch( message.key ){
                case KeyEvent.VK_UP:
                    world.set(entity, world.get(entity, Acceleration.class).add(new Vector(0, thrust)));
                    break;
                case KeyEvent.VK_DOWN:
                    world.set(entity, world.get(entity, Acceleration.class).add(new Vector(0, -thrust)));
                    break;
                case KeyEvent.VK_LEFT:
                    world.set(entity, world.get(entity, Acceleration.class).add(new Vector(-thrust, 0)));
                    break;
                case KeyEvent.VK_RIGHT:
                    world.set(entity, world.get(entity, Acceleration.class).add(new Vector(thrust, 0)));
                    break;
                default:
                    break;
            }
        }
    }

}
