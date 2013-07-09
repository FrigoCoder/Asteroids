
package frigo.asteroids.core;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;

public class WorldTest {

    private World world = new World();
    private Entity entity = new Entity();
    private Logic logic = mock(Logic.class);

    @Test
    public void added_entity_is_present () {
        world.addEntity(entity);
        assertThat(world.getEntities(), hasItem(entity));
    }

    @Test
    public void entities_matching_aspect_are_returned () {
        entity.set(new Position(1, 1));
        entity.set(new Velocity(1, 1));
        world.addEntity(entity);

        Entity empty = new Entity();
        world.addEntity(empty);

        Aspect aspect = Aspect.all(Position.class, Velocity.class);

        Set<Entity> expected = new HashSet<>(asList(entity));
        assertThat(world.getEntitiesFor(aspect), is(expected));
    }

    @Test
    public void logic_is_initialized_at_init_call () {
        world.addLogic(logic);
        world.init();
        verify(logic).init(world);
    }

    @Test
    public void logic_is_updated_at_update_call () {
        world.addLogic(logic);
        world.update(1);
        verify(logic).update(world, 1);
    }

    @Test
    public void logic_is_updated_by_the_elapsed_seconds () {
        world.addLogic(logic);
        world.update(0.01);
        verify(logic).update(world, 0.01);
    }
}
