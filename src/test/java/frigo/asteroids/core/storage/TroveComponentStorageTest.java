
package frigo.asteroids.core.storage;

import static frigo.asteroids.component.Planar.planar;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class TroveComponentStorageTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private World world = new World();
    private Entity entity = world.createEntity();
    private TroveComponentStorage<Planar> storage = new TroveComponentStorage<>();
    private Planar component = planar();

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
