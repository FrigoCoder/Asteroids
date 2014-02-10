
package frigo.asteroids.core;

import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Planar;

public class EntityTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private World world = new World();
    private Entity entity = world.createEntity();
    private Planar position = new Planar(vector(1, 1), ZERO, ZERO);

    @Test
    public void added_component_can_be_retrieved () {
        entity.add(position);
        assertThat(entity.get(Planar.class), sameInstance(position));
    }

    @Test
    public void not_added_component_retrieval_throws_exception () {
        thrown.expect(NoSuchElementException.class);
        entity.get(Planar.class);
    }

    @Test
    public void added_component_can_be_checked_for_presence () {
        entity.add(position);
        assertThat(entity.has(Planar.class), is(true));
    }

    @Test
    public void not_added_component_can_be_checked_for_presence () {
        assertThat(entity.has(Planar.class), is(false));
    }

    @Test
    public void removed_component_is_removed () {
        entity.add(position);
        entity.remove(Planar.class);
        assertThat(entity.has(Planar.class), is(false));
    }

    @Test
    public void vararg_constructor_adds_all_components () {
        Attractor attractor = Attractor.ATTRACTOR;
        entity = world.createEntity(position, attractor);
        assertThat(entity.get(Planar.class), sameInstance(position));
        assertThat(entity.get(Attractor.class), sameInstance(attractor));
    }

}
