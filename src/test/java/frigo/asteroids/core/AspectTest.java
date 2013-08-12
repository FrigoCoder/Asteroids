
package frigo.asteroids.core;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Circle;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;

public class AspectTest {

    private World world = new World();
    private Aspect all = Aspect.allOf(Position.class, Velocity.class);
    private Aspect none = Aspect.noneOf(Position.class, Velocity.class);

    @Test
    public void aspect_all_matches_entities_having_all_components () {
        Entity entity = world.createEntity(new Position(0, 0), new Velocity(0, 0));
        assertThat(world.matches(entity, all), is(true));
    }

    @Test
    public void aspect_all_does_not_match_entities_not_having_all_components () {
        Entity entity = world.createEntity(new Position(0, 0));
        assertThat(world.matches(entity, all), is(false));
    }

    @Test
    public void aspect_all_matches_entities_having_more_components () {
        Entity entity = world.createEntity(new Position(0, 0), new Velocity(0, 0), new Circle());
        assertThat(world.matches(entity, all), is(true));
    }

    @Test
    public void aspect_none_matches_entities_having_no_such_components () {
        Entity entity = world.createEntity();
        assertThat(world.matches(entity, none), is(true));
    }

    @Test
    public void aspect_none_does_not_match_entities_having_such_components () {
        Entity entity = world.createEntity(new Position(0, 0));
        assertThat(world.matches(entity, none), is(false));
    }

    @Test
    public void aspect_none_matches_entities_having_other_components () {
        Entity entity = world.createEntity(new Circle());
        assertThat(world.matches(entity, none), is(true));
    }

}
