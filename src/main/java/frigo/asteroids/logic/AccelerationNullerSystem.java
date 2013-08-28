
package frigo.asteroids.logic;

import frigo.asteroids.component.AngularAcceleration;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class AccelerationNullerSystem implements Logic {

    private Aspect angularAccelerationAspect = Aspect.allOf(AngularAcceleration.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(angularAccelerationAspect) ){
            world.set(entity, new AngularAcceleration(0));
        }
    }

}
