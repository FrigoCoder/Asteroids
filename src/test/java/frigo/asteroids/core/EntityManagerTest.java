
package frigo.asteroids.core;

import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Planar;

public class EntityManagerTest {

    private World world = new World();
    private Entity entity = world.createEntity();

    @Before
    public void setUp () {
        entity.add(Attractable.ID, Attractable.ATTRACTABLE);
    }

    @Test
    public void added_entity_is_present () {
        Aspect aspect = Aspect.allOf();
        assertThat(world.getEntitiesFor(aspect), hasItem(entity));
    }

    @Test
    public void entities_matching_aspect_are_returned () {
        entity.add(Planar.ID, new Planar(vector(1, 1), vector(1, 1), ZERO));

        world.createEntity();

        Aspect aspect = Aspect.allOf(Planar.ID);

        List<Entity> expected = new LinkedList<>(asList(entity));
        assertThat(world.getEntitiesFor(aspect), is(expected));
    }

}
