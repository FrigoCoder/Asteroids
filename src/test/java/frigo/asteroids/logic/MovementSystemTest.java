
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class MovementSystemTest {

    private MovementSystem movementSystem = new MovementSystem();

    private World world = new World();
    private Entity entity = new Entity();

    @Test
    public void update_updates_entities_with_speed_and_position_and_elapsed_seconds () {
        entity.add(new Velocity(10.0, 10.0));
        entity.add(new Position(0.0, 0.1));
        world.addEntity(entity);

        movementSystem.init(world);
        movementSystem.update(world, 0.01);

        Position position = entity.get(Position.class);
        assertThat(position, is(new Position(0.1, 0.2)));
    }

    @Test
    public void update_does_not_update_entities_without_speed () {
        entity.add(new Position(0.0, 0.1));
        world.addEntity(entity);

        movementSystem.init(world);
        movementSystem.update(world, 1);

        Position position = entity.get(Position.class);
        assertThat(position, is(new Position(0.0, 0.1)));

    }

}
