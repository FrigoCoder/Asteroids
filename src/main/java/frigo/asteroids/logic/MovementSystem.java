
package frigo.asteroids.logic;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class MovementSystem implements Logic {

    private Aspect all;
    private Aspect noPosition;
    private Aspect noAcceleration;

    @Override
    public void init (World world) {
        all = new Aspect(world).allOf(Acceleration.class, Velocity.class, Position.class);
        noPosition = new Aspect(world).allOf(Acceleration.class, Velocity.class).andNoneOf(Position.class);
        noAcceleration = new Aspect(world).allOf(Velocity.class, Position.class).andNoneOf(Acceleration.class);
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
                new VelocityVerlet(world.getComponent(entity, Acceleration.class), world.getComponent(entity,
                    Velocity.class), world.getComponent(entity, Position.class));
            world.setComponent(entity, verlet.getVelocity(elapsedSeconds));
            world.setComponent(entity, verlet.getPosition(elapsedSeconds));
        }
    }

    private void handleEntitiesWithNoAcceleration (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(noAcceleration) ){
            VelocityVerlet verlet =
                new VelocityVerlet(new Acceleration(0, 0), world.getComponent(entity, Velocity.class),
                    world.getComponent(entity, Position.class));
            world.setComponent(entity, verlet.getVelocity(elapsedSeconds));
            world.setComponent(entity, verlet.getPosition(elapsedSeconds));
        }

    }

    private void handleEntitiesWithNoPosition (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(noPosition) ){
            VelocityVerlet verlet =
                new VelocityVerlet(world.getComponent(entity, Acceleration.class), world.getComponent(entity,
                    Velocity.class), new Position(0, 0));
            world.setComponent(entity, verlet.getVelocity(elapsedSeconds));
        }
    }
}
