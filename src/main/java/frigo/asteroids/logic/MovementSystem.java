
package frigo.asteroids.logic;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class MovementSystem implements Logic {

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        handleEntitiesWithAllComponents(world, elapsedSeconds);
        handleEntitiesWithNoAcceleration(world, elapsedSeconds);
        handleEntitiesWithNoPosition(world, elapsedSeconds);
    }

    private void handleEntitiesWithAllComponents (World world, double elapsedSeconds) {
        Aspect aspect = Aspect.allOf(Acceleration.class, Velocity.class, Position.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            VelocityVerlet verlet =
                new VelocityVerlet(entity.get(Acceleration.class), entity.get(Velocity.class),
                    entity.get(Position.class));
            entity.set(verlet.getVelocity(elapsedSeconds));
            entity.set(verlet.getPosition(elapsedSeconds));
        }
    }

    private void handleEntitiesWithNoAcceleration (World world, double elapsedSeconds) {
        Aspect aspect = Aspect.allOf(Velocity.class, Position.class).andNoneOf(Acceleration.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            VelocityVerlet verlet =
                new VelocityVerlet(new Acceleration(0, 0), entity.get(Velocity.class), entity.get(Position.class));
            entity.set(verlet.getVelocity(elapsedSeconds));
            entity.set(verlet.getPosition(elapsedSeconds));
        }

    }

    private void handleEntitiesWithNoPosition (World world, double elapsedSeconds) {
        Aspect aspect = Aspect.allOf(Acceleration.class, Velocity.class).andNoneOf(Position.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            VelocityVerlet verlet =
                new VelocityVerlet(entity.get(Acceleration.class), entity.get(Velocity.class), new Position(0, 0));
            entity.set(verlet.getVelocity(elapsedSeconds));
        }
    }
}
