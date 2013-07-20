
package frigo.asteroids.logic;

import com.jogamp.newt.event.KeyEvent;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;
import frigo.asteroids.message.KeyPressed;

public class InputSystem implements Logic {

    private Aspect aspect = Aspect.all(PlayerControllable.class, Acceleration.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        Entity entity = world.getEntitiesFor(aspect).iterator().next();
        PlayerControllable controllable = entity.get(PlayerControllable.class);
        double continuousThrust = controllable.thrust;
        Acceleration acceleration = entity.get(Acceleration.class);
        for( KeyPressed event : world.getMessages(KeyPressed.class) ){
            Vector direction = getDirection(event.key);
            acceleration = acceleration.add(direction.mul(continuousThrust));
        }
        entity.set(acceleration);
    }

    private Vector getDirection (short key) {
        switch( key ){
            case KeyEvent.VK_UP:
                return new Vector(0, -1);
            case KeyEvent.VK_DOWN:
                return new Vector(0, 1);
            case KeyEvent.VK_LEFT:
                return new Vector(-1, 0);
            case KeyEvent.VK_RIGHT:
                return new Vector(1, 0);
            default:
                return new Vector(0, 0);
        }
    }

}
