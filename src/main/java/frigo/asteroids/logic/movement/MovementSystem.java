
package frigo.asteroids.logic.movement;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class MovementSystem implements Logic {

    private Aspect all = Aspect.allOf(Acceleration.class, Velocity.class, Position.class);
    private Aspect noPosition = Aspect.allOf(Acceleration.class, Velocity.class).andNoneOf(Position.class);
    private Aspect noAcceleration = Aspect.allOf(Velocity.class, Position.class).andNoneOf(Acceleration.class);

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
        for( Entity entity : world.getEntitiesFor(all) ){
            VelocityVerlet verlet =
                new VelocityVerlet(world.get(entity, Acceleration.class), world.get(entity, Velocity.class), world.get(
                    entity, Position.class));
            world.set(entity, verlet.getVelocity(elapsedSeconds));
            world.set(entity, verlet.getPosition(elapsedSeconds));
        }
    }

    private void handleEntitiesWithNoAcceleration (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(noAcceleration) ){
            VelocityVerlet verlet =
                new VelocityVerlet(new Acceleration(0, 0), world.get(entity, Velocity.class), world.get(entity,
                    Position.class));
            world.set(entity, verlet.getVelocity(elapsedSeconds));
            world.set(entity, verlet.getPosition(elapsedSeconds));
        }

    }

    private void handleEntitiesWithNoPosition (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(noPosition) ){
            VelocityVerlet verlet =
                new VelocityVerlet(world.get(entity, Acceleration.class), world.get(entity, Velocity.class),
                    new Position(0, 0));
            world.set(entity, verlet.getVelocity(elapsedSeconds));
        }
    }

}
