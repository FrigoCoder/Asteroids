
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

    private Aspect aspect = Aspect.all(PlayerControllable.class, Acceleration.class);

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
        
        double speed = 1.0;
        while( Keyboard.next() ){
            int key = Keyboard.getEventKey();
            boolean pressed = Keyboard.getEventKeyState();
            if((key==Keyboard.KEY_UP && pressed) || (key==Keyboard.KEY_DOWN && !pressed)){ 
                entity.add(entity.get(Acceleration.class).add(0.0, -speed));
            }
            if((key==Keyboard.KEY_UP && !pressed) || (key==Keyboard.KEY_DOWN && pressed)){ 
                entity.add(entity.get(Acceleration.class).add(0.0, speed));
            }
            if((key==Keyboard.KEY_LEFT && pressed) || (key==Keyboard.KEY_RIGHT && !pressed)){
                entity.add(entity.get(Acceleration.class).add(-speed, 0.0));
            }
            if((key==Keyboard.KEY_LEFT && !pressed) || (key==Keyboard.KEY_RIGHT && pressed)){
                entity.add(entity.get(Acceleration.class).add(speed, 0.0));
            }
        }
    }
}
