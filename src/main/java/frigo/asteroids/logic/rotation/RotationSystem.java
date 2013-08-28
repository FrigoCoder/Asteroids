
package frigo.asteroids.logic.rotation;

import frigo.asteroids.component.Angular;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class RotationSystem implements Logic {

    private Aspect all = Aspect.allOf(Angular.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(all) ){
            world.set(entity, world.get(entity, Angular.class).update(elapsedSeconds));
        }
    }

}
