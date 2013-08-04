
package frigo.asteroids.core;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Position;

public class EntityTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private World world = new World();
    private Entity entity = world.createEntity();
    private Position position = new Position(1, 1);

    @Test
    public void created_entities_have_different_ids () {
        Entity other = world.createEntity();
        assertThat(entity.id, not(other.id));
    }

    @Test
    public void added_component_can_be_retrieved () {
        entity.set(position);
        assertThat(entity.get(Position.class), sameInstance(position));
    }

    @Test
    public void not_added_component_retrieval_throws_exception () {
        thrown.expect(NoSuchElementException.class);
        entity.get(Position.class);
    }

    @Test
    public void added_component_can_be_checked_for_presence () {
        entity.set(position);
        assertThat(entity.has(Position.class), is(true));
    }

    @Test
    public void not_added_component_can_be_checked_for_presence () {
        assertThat(entity.has(Position.class), is(false));
    }

    @Test
    public void vararg_constructor_adds_all_components () {
        Attractor attractor = new Attractor();
        entity = world.createEntity(position, attractor);
        assertThat(entity.get(Position.class), sameInstance(position));
        assertThat(entity.get(Attractor.class), sameInstance(attractor));
    }

}
