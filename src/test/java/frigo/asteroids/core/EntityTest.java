
package frigo.asteroids.core;

import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Planar;

public class EntityTest {

    private World world = new World();
    private Entity entity = world.createEntity();
    private Planar position = new Planar(vector(1, 1), ZERO, ZERO);

    @Before
    public void setUp () {
        world.register(Planar.class);
    }

    @Test
    public void added_component_can_be_retrieved () {
        entity.add(Planar.class, position);
        assertThat(entity.get(Planar.class), sameInstance(position));
    }

    @Test
    public void not_added_component_retrieval_returns_null () {
        assertThat(entity.get(Planar.class), nullValue());
    }

    @Test
    public void added_component_can_be_checked_for_presence () {
        entity.add(Planar.class, position);
        assertThat(entity.has(Planar.class), is(true));
    }

    @Test
    public void not_added_component_can_be_checked_for_presence () {
        assertThat(entity.has(Planar.class), is(false));
    }

    @Test
    public void removed_component_is_removed () {
        entity.add(Planar.class, position);
        entity.remove(Planar.class);
        assertThat(entity.has(Planar.class), is(false));
    }

}
