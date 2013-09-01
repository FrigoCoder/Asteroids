
package frigo.asteroids.logic;

import static frigo.asteroids.component.Vector.UNIT_Y;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.newt.event.KeyEvent;

import frigo.asteroids.component.Angular;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;
import frigo.asteroids.message.KeyHeld;
import frigo.asteroids.message.KeyMessage;
import frigo.asteroids.message.KeyPressed;

public class InputSystem extends Logic {

    private Aspect controllableAspect = Aspect.allOf(PlayerControllable.class, Planar.class, Angular.class);

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
                    default:
                        break;
                }
            }
        }
    }

}
