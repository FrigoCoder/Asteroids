
package frigo.asteroids.core;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import frigo.asteroids.component.Position;

public class ArrayComponentStorageTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private World world = new World();
    private Entity entity = world.createEntity();
    private ComponentStorage<Position> storage = new ArrayComponentStorage<>();
    private Position component = new Position(0.0, 0.0);

    @Test
    public void has_returns_false_for_entity_without_component () {
        assertThat(storage.has(entity), is(false));
    }

    @Test
    public void has_returns_true_for_entity_with_component () {
        storage.set(entity, component);
        assertThat(storage.has(entity), is(true));
    }

    @Test
    public void get_throws_exception_for_entity_without_component () {
        thrown.expect(NoSuchElementException.class);
        storage.get(entity);
    }

    @Test
    public void get_returns_component_for_entity_with_component () {
        storage.set(entity, component);
        assertThat(storage.get(entity), sameInstance(component));
    }

}
