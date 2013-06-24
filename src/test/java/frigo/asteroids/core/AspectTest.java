
package frigo.asteroids.core;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.components.Position;
import frigo.asteroids.components.Renderable;
import frigo.asteroids.components.Speed;

public class AspectTest {

    private Aspect aspect = Aspect.all(asList(Position.class, Speed.class));
    private Entity entity = new Entity();

    @Test
    public void aspect_all_matches_entities_having_all_components () {
        entity.add(new Position(1.0, 1.0));
        entity.add(new Speed(0.1, 0.1));
        assertThat(aspect.matches(entity), is(true));
    }

    @Test
    public void aspect_all_does_not_match_entities_not_having_all_components () {
        entity.add(new Position(1.0, 1.0));
        assertThat(aspect.matches(entity), is(false));
    }

    @Test
    public void aspect_all_matches_entities_having_more_components () {
        entity.add(new Position(1.0, 1.0));
        entity.add(new Speed(0.1, 0.1));
        entity.add(new Renderable());
        assertThat(aspect.matches(entity), is(true));
    }

}
