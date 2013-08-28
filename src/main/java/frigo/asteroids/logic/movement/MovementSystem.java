
package frigo.asteroids.logic.movement;

import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class MovementSystem implements Logic {

    private Aspect aspect = Aspect.allOf(Planar.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            VelocityVerlet verlet = new VelocityVerlet(world.get(entity, Planar.class));
            Planar planar =
                new Planar(verlet.getPosition(elapsedSeconds), verlet.getVelocity(elapsedSeconds), new Vector(0, 0));
            world.set(entity, planar);
        }
    }

}
