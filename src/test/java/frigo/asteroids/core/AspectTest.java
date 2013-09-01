
package frigo.asteroids.core;

import static frigo.asteroids.component.Vector.NULL;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Point;

public class AspectTest {

    private World world = new World();
    private Aspect all = Aspect.allOf(Planar.class, Mass.class);
    private Aspect none = Aspect.noneOf(Planar.class, Mass.class);

    @Test
    public void aspect_all_matches_entities_having_all_components () {
        Entity entity = world.createEntity(new Planar(NULL), new Mass(0));
        assertThat(world.matches(entity, all), is(true));
    }

    @Test
    public void aspect_all_does_not_match_entities_not_having_all_components () {
        Entity entity = world.createEntity(new Planar(NULL));
        assertThat(world.matches(entity, all), is(false));
    }

    @Test
    public void aspect_all_matches_entities_having_more_components () {
        Entity entity = world.createEntity(new Planar(NULL), new Mass(0), new Point());
        assertThat(world.matches(entity, all), is(true));
    }

    @Test
    public void aspect_none_matches_entities_having_no_such_components () {
        Entity entity = world.createEntity();
        assertThat(world.matches(entity, none), is(true));
    }

    @Test
    public void aspect_none_does_not_match_entities_having_such_components () {
        Entity entity = world.createEntity(new Planar(NULL));
        assertThat(world.matches(entity, none), is(false));
    }

    @Test
    public void aspect_none_matches_entities_having_other_components () {
        Entity entity = world.createEntity(new Point());
        assertThat(world.matches(entity, none), is(true));
    }

}
