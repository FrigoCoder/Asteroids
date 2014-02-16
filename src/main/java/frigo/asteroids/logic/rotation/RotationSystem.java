
package frigo.asteroids.logic.rotation;

import frigo.asteroids.component.Angular;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;

public class RotationSystem extends Logic {

    private Aspect all = Aspect.allOf(Angular.ID);

    @Override
    public void update (double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(all) ){
            entity.get(Angular.ID).update(elapsedSeconds);
        }
    }

}
