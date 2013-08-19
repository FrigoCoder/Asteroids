
package frigo.asteroids.logic;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class AccelerationNullerSystem implements Logic {

    private Aspect accelerationAspect = Aspect.allOf(Acceleration.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(accelerationAspect) ){
            world.set(entity, new Acceleration(0, 0));
        }
    }

}
