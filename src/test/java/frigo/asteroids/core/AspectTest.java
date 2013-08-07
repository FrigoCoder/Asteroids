
package frigo.asteroids.core;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Renderable;
import frigo.asteroids.component.Velocity;

public class AspectTest {

    private World world = new World();
    private Aspect aspect = new Aspect(world).allOf(Position.class, Velocity.class);
    private Entity entity = world.createEntity();

    @Test
    public void aspect_all_matches_entities_having_all_components () {
        world.set(entity, new Position(1, 1));
        world.set(entity, new Velocity(0.1, 0.1));
        assertThat(aspect.matches(entity), is(true));
    }

    @Test
    public void aspect_all_does_not_match_entities_not_having_all_components () {
        world.set(entity, new Position(1, 1));
        assertThat(aspect.matches(entity), is(false));
    }

    @Test
    public void aspect_all_matches_entities_having_more_components () {
        world.set(entity, new Position(1, 1));
        world.set(entity, new Velocity(0.1, 0.1));
        world.set(entity, new Renderable(1.0));
        assertThat(aspect.matches(entity), is(true));
    }

    @Ignore
    @Test
    public void test_none_matcher () {
    }

}
