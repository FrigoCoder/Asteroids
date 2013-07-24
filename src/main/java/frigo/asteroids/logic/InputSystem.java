
package frigo.asteroids.logic;

import com.jogamp.newt.event.KeyEvent;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;
import frigo.asteroids.message.KeyHeld;
import frigo.asteroids.message.KeyPressed;

public class InputSystem implements Logic {

    private Aspect aspect = Aspect.allOf(PlayerControllable.class, Acceleration.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        Entity entity = world.getEntitiesFor(aspect).iterator().next();
        PlayerControllable controllable = entity.get(PlayerControllable.class);
        double thrust = controllable.thrust;
        Acceleration acceleration = entity.get(Acceleration.class);
        for( KeyPressed event : world.getMessages(KeyPressed.class) ){
            acceleration = acceleration.add(getDirection(event.key).mul(thrust));
        }
        for( KeyHeld event : world.getMessages(KeyHeld.class) ){
            acceleration = acceleration.add(getDirection(event.key).mul(thrust));
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
