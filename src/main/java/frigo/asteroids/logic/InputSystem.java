
package frigo.asteroids.logic;

import static frigo.asteroids.util.Rethrow.unchecked;

import java.util.Set;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class InputSystem implements Logic {

    private Aspect aspect = Aspect.all(PlayerControllable.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        if( !Keyboard.isCreated() ){
            try{
                Keyboard.create();
            }catch( LWJGLException e ){
                throw unchecked(e);
            }
        }
        Set<Entity> entities = world.getEntitiesFor(aspect);
        Entity entity = entities.iterator().next();
        entity.add(new Acceleration(0.0, 0.0));
        double speed = 10.0;
        while( Keyboard.next() ){
            int key = Keyboard.getEventKey();
            switch( key ){
                case Keyboard.KEY_UP:
                    entity.add(new Acceleration(0.0, -speed));
                    break;
                case Keyboard.KEY_DOWN:
                    entity.add(new Acceleration(0.0, speed));
                    break;
                case Keyboard.KEY_LEFT:
                    entity.add(new Acceleration(-speed, 0.0));
                    break;
                case Keyboard.KEY_RIGHT:
                    entity.add(new Acceleration(speed, 0.0));
                    break;
                default:
                    break;
            }
        }
    }
}
