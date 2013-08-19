
package frigo.asteroids.logic.rotation;

import frigo.asteroids.component.AngularAcceleration;
import frigo.asteroids.component.AngularDisplacement;
import frigo.asteroids.component.AngularVelocity;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class RotationSystem implements Logic {

    private Aspect all = Aspect.allOf(AngularAcceleration.class, AngularVelocity.class, AngularDisplacement.class);
    private Aspect noAcceleration = Aspect.allOf(AngularVelocity.class, AngularDisplacement.class).andNoneOf(
        AngularAcceleration.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        handleEntitiesWithAllComponents(world, elapsedSeconds);
        handleEntitiesWithNoAcceleration(world, elapsedSeconds);
    }

    private void handleEntitiesWithAllComponents (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(all) ){
            AngularVerlet verlet =
                new AngularVerlet(world.get(entity, AngularAcceleration.class),
                    world.get(entity, AngularVelocity.class), world.get(entity, AngularDisplacement.class));
            world.set(entity, verlet.getVelocity(elapsedSeconds));
            world.set(entity, verlet.getDisplacement(elapsedSeconds));
        }
    }

    private void handleEntitiesWithNoAcceleration (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(noAcceleration) ){
            AngularVerlet verlet =
                new AngularVerlet(new AngularAcceleration(0.0), world.get(entity, AngularVelocity.class), world.get(
                    entity, AngularDisplacement.class));
            world.set(entity, verlet.getVelocity(elapsedSeconds));
            world.set(entity, verlet.getDisplacement(elapsedSeconds));
        }
    }

}
