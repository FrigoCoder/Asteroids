
package frigo.asteroids.logic;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class MovementSystem implements Logic {

    private Aspect velocityUpdate = Aspect.all(Acceleration.class, Velocity.class);
    private Aspect positionUpdate = Aspect.all(Velocity.class, Position.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        updateVelocityWithAcceleration(world, elapsedSeconds);
        updatePositionWithVelocity(world, elapsedSeconds);
    }

    private void updateVelocityWithAcceleration (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(velocityUpdate) ){
            Acceleration acceleration = entity.get(Acceleration.class);
            Velocity velocity = entity.get(Velocity.class);
            Velocity newVelocity = velocity.add(acceleration.mul(elapsedSeconds));
            entity.add(newVelocity);
        }
    }

    private void updatePositionWithVelocity (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(positionUpdate) ){
            Velocity velocity = entity.get(Velocity.class);
            Position position = entity.get(Position.class);
            Position newPosition = position.add(velocity.mul(elapsedSeconds));
            entity.add(newPosition);
        }
    }

}
