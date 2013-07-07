
package frigo.asteroids.logic;

import static frigo.asteroids.util.Rethrow.unchecked;

import java.util.Set;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Speed;
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
        while( Keyboard.next() ){
            int key = Keyboard.getEventKey();
            switch( key ){
                case Keyboard.KEY_UP:
                    entity.add(new Speed(0.0, -1.0));
                    break;
                case Keyboard.KEY_DOWN:
                    entity.add(new Speed(0.0, 1.0));
                    break;
                case Keyboard.KEY_LEFT:
                    entity.add(new Speed(-1.0, 0.0));
                    break;
                case Keyboard.KEY_RIGHT:
                    entity.add(new Speed(1.0, 0.0));
                    break;
                default:
                    break;
            }
        }
    }
}
