
package frigo.asteroids.core;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Renderable;
import frigo.asteroids.component.Velocity;

public class AspectTest {

    private Aspect aspect = Aspect.all(Position.class, Velocity.class);
    private Entity entity = new Entity();

    @Test
    public void aspect_all_matches_entities_having_all_components () {
        entity.set(new Position(1, 1));
        entity.set(new Velocity(0.1, 0.1));
        assertThat(aspect.matches(entity), is(true));
    }

    @Test
    public void aspect_all_does_not_match_entities_not_having_all_components () {
        entity.set(new Position(1, 1));
        assertThat(aspect.matches(entity), is(false));
    }

    @Test
    public void aspect_all_matches_entities_having_more_components () {
        entity.set(new Position(1, 1));
        entity.set(new Velocity(0.1, 0.1));
        entity.set(new Renderable(1.0));
        assertThat(aspect.matches(entity), is(true));
    }

}
