
package frigo.asteroids.logic;

import frigo.asteroids.component.SelfDestruct;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class SelfDestructSystem extends Logic {

    private Aspect aspect = Aspect.allOf(SelfDestruct.class);

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            world.removeEntity(entity);
        }
    }

}
