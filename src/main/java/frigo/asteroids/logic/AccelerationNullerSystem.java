
package frigo.asteroids.logic;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class AccelerationNullerSystem implements Logic {

    private static final Aspect ACCELERATION_ASPECT = Aspect.allOf(Acceleration.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(ACCELERATION_ASPECT) ){
            world.setComponent(entity, new Acceleration(0, 0));
        }
    }

}
