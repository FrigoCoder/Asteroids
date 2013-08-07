
package frigo.asteroids.core;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Renderable;
import frigo.asteroids.component.Velocity;

public class AspectTest {

    private Aspect aspect = Aspect.allOf(Position.class, Velocity.class);
    private World world = new World();
    private Entity entity = world.createEntity();

    @Test
    public void aspect_all_matches_entities_having_all_components () {
        world.setComponent(entity, new Position(1, 1));
        world.setComponent(entity, new Velocity(0.1, 0.1));
        assertThat(aspect.matches(entity), is(true));
    }

    @Test
    public void aspect_all_does_not_match_entities_not_having_all_components () {
        world.setComponent(entity, new Position(1, 1));
        assertThat(aspect.matches(entity), is(false));
    }

    @Test
    public void aspect_all_matches_entities_having_more_components () {
        world.setComponent(entity, new Position(1, 1));
        world.setComponent(entity, new Velocity(0.1, 0.1));
        world.setComponent(entity, new Renderable(1.0));
        assertThat(aspect.matches(entity), is(true));
    }

    @Ignore
    @Test
    public void test_none_matcher () {
    }

}
