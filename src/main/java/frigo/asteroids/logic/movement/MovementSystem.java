
package frigo.asteroids.logic.movement;

import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;

public class MovementSystem extends Logic {

    private Aspect aspect = Aspect.allOf(Planar.class);

    @Override
    public void update (double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Planar planar = entity.get(Planar.ID);
            planar.update(elapsedSeconds);
        }
    }

}
