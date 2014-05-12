
package frigo.asteroids.logic.selfdestruct;

import frigo.asteroids.component.SelfDestruct;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;

public class SelfDestructSystem extends Logic {

    private Aspect aspect = Aspect.allOf(SelfDestruct.ID);

    @Override
    public void update (double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            world.removeEntity(entity);
        }
    }

}
