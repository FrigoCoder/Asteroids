
package frigo.asteroids.logic.movement;

import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class MovementSystem extends Logic {

    private Aspect aspect = Aspect.allOf(Planar.class);

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            world.set(entity, world.get(entity, Planar.class).update(elapsedSeconds));
        }
    }

}
