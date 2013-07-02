
package frigo.asteroids.core;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import frigo.asteroids.component.Position;

public class EntityTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Entity entity = new Entity();
    private Position position = new Position(1, 1);

    @Test
    public void added_component_can_be_retrieved () {
        entity.add(position);
        assertThat(entity.get(Position.class), sameInstance(position));
    }

    @Test
    public void not_added_component_retrieval_throws_exception () {
        thrown.expect(NoSuchElementException.class);
        entity.get(Position.class);
    }

    @Test
    public void added_component_can_be_checked_for_presence () {
        entity.add(position);
        assertThat(entity.has(Position.class), is(true));
    }

    @Test
    public void not_added_component_can_be_checked_for_presence () {
        assertThat(entity.has(Position.class), is(false));
    }

}
