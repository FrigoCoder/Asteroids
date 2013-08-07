
package frigo.asteroids.logic;

import static frigo.asteroids.core.Aspect.allOf;
import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class MovementSystem implements Logic {

    private static final Aspect ALL = allOf(Acceleration.class, Velocity.class, Position.class);
    private static final Aspect NO_POSITION = allOf(Acceleration.class, Velocity.class).andNoneOf(Position.class);
    private static final Aspect NO_ACCELERATION = allOf(Velocity.class, Position.class).andNoneOf(Acceleration.class);

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
        for( Entity entity : world.getEntitiesFor(ALL) ){
            VelocityVerlet verlet =
                new VelocityVerlet(entity.get(Acceleration.class), entity.get(Velocity.class),
                    entity.get(Position.class));
            world.setComponent(entity, verlet.getVelocity(elapsedSeconds));
            world.setComponent(entity, verlet.getPosition(elapsedSeconds));
        }
    }

    private void handleEntitiesWithNoAcceleration (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(NO_ACCELERATION) ){
            VelocityVerlet verlet =
                new VelocityVerlet(new Acceleration(0, 0), entity.get(Velocity.class), entity.get(Position.class));
            world.setComponent(entity, verlet.getVelocity(elapsedSeconds));
            world.setComponent(entity, verlet.getPosition(elapsedSeconds));
        }

    }

    private void handleEntitiesWithNoPosition (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(NO_POSITION) ){
            VelocityVerlet verlet =
                new VelocityVerlet(entity.get(Acceleration.class), entity.get(Velocity.class), new Position(0, 0));
            world.setComponent(entity, verlet.getVelocity(elapsedSeconds));
        }
    }
}
