
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class MovementSystemTest {

    private MovementSystem movementSystem = new MovementSystem();

    private World world = new World();
    private Entity entity = world.createEntity();

    @Test
    public void updates_velocity_of_entities_by_acceleration_and_elapsed_seconds () {
        entity.set(new Acceleration(1.0, 1.0));
        entity.set(new Velocity(1.0, -1.0));
        world.addEntity(entity);

        movementSystem.init(world);
        movementSystem.update(world, 0.1);

        Velocity velocity = entity.get(Velocity.class);
        assertThat(velocity, is(new Velocity(1.1, -0.9)));
    }

    @Test
    public void does_not_update_velocity_of_entities_without_acceleration () {
        entity.set(new Velocity(1.0, -1.0));
        world.addEntity(entity);

        movementSystem.init(world);
        movementSystem.update(world, 0.1);

        Velocity velocity = entity.get(Velocity.class);
        assertThat(velocity, is(new Velocity(1.0, -1.0)));
    }

    @Test
    public void updates_position_of_entities_by_velocity_and_elapsed_seconds () {
        entity.set(new Velocity(1.0, 1.0));
        entity.set(new Position(0.0, 0.1));
        world.addEntity(entity);

        movementSystem.init(world);
        movementSystem.update(world, 0.1);

        Position position = entity.get(Position.class);
        assertThat(position, is(new Position(0.1, 0.2)));
    }

    @Test
    public void does_not_update_position_of_entities_without_speed () {
        entity.set(new Position(0.0, 0.1));
        world.addEntity(entity);

        movementSystem.init(world);
        movementSystem.update(world, 0.1);

        Position position = entity.get(Position.class);
        assertThat(position, is(new Position(0.0, 0.1)));

    }

}
