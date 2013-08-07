
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class MovementSystemTest {

    private World world = new World();
    private Entity entity = world.createEntity();
    private MovementSystem movementSystem = new MovementSystem();

    @Before
    public void setUp () {
        world.addEntity(entity);
        world.addLogic(movementSystem);
        world.init();
    }

    @Test
    public void updates_velocity_of_entities_by_acceleration_and_elapsed_seconds () {
        world.set(entity, new Acceleration(1.0, 1.0));
        world.set(entity, new Velocity(1.0, -1.0));

        world.update(0.1);

        assertThat(world.get(entity, Velocity.class), is(new Velocity(1.1, -0.9)));
    }

    @Test
    public void does_not_update_velocity_of_entities_without_acceleration () {
        world.set(entity, new Velocity(1.0, -1.0));

        world.update(0.1);

        assertThat(world.get(entity, Velocity.class), is(new Velocity(1.0, -1.0)));
    }

    @Test
    public void updates_position_of_entities_by_velocity_and_elapsed_seconds () {
        world.set(entity, new Velocity(1.0, 1.0));
        world.set(entity, new Position(0.0, 0.1));

        world.update(0.1);

        assertThat(world.get(entity, Position.class), is(new Position(0.1, 0.2)));
    }

    @Test
    public void does_not_update_position_of_entities_without_velocity () {
        world.set(entity, new Position(0.0, 0.1));

        world.update(0.1);

        assertThat(world.get(entity, Position.class), is(new Position(0.0, 0.1)));
    }

    @Test
    public void properly_updates_velocity_and_position_of_entities_with_acceleration_and_velocity () {
        world.set(entity, new Acceleration(1.0, 1.0));
        world.set(entity, new Velocity(1.0, -1.0));
        world.set(entity, new Position(0.5, 0.5));

        world.update(0.1);

        assertThat(world.get(entity, Velocity.class), is(new Velocity(1.1, -0.9)));
        assertThat(world.get(entity, Position.class), is(new Position(0.605, 0.405)));
    }
}
