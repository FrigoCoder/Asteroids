
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Speed;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class MovementSystemTest {

    private MovementSystem movementSystem = new MovementSystem();

    private World world = new World();
    private Entity entity = new Entity();

    @Test
    public void update_updates_entities_with_speed_and_position () {
        entity.add(new Speed(0.1, 0.1));
        entity.add(new Position(0.0, 0.1));
        world.addEntity(entity);

        movementSystem.init(world);
        movementSystem.update(world);

        Position position = entity.get(Position.class);
        assertThat(position.x, is(0.1));
        assertThat(position.y, is(0.2));
    }

    @Test
    public void update_does_not_update_entities_without_speed () {
        entity.add(new Position(0.0, 0.1));
        world.addEntity(entity);

        movementSystem.init(world);
        movementSystem.update(world);

        Position position = entity.get(Position.class);
        assertThat(position.x, is(0.0));
        assertThat(position.y, is(0.1));

    }

}
