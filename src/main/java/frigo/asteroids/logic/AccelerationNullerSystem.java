
package frigo.asteroids.logic;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class AccelerationNullerSystem implements Logic {

    private Aspect aspect = new Aspect().all(Acceleration.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            entity.set(new Acceleration(0, 0));
        }
    }

}
