
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

import frigo.asteroids.components.Position;
import frigo.asteroids.components.Speed;

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
        entity.add(new Position(1, 1));
        entity.add(new Speed(1, 1));
        world.addEntity(entity);

        Entity empty = new Entity();
        world.addEntity(empty);

        Aspect aspect = Aspect.all(Position.class, Speed.class);

        Set<Entity> expected = new HashSet<>(asList(entity));
        assertThat(world.getEntitiesFor(aspect), is(expected));

    }

    @Test
    public void logic_is_initialized_at_init_call () {
        // given
        world.addLogic(logic);
        // when
        world.init();
        // then
        verify(logic).init(world);
    }

    @Test
    public void logic_is_updated_at_update_call () {
        // given
        world.addLogic(logic);
        // when
        world.update();
        // then
        verify(logic).update(world);
    }
}
