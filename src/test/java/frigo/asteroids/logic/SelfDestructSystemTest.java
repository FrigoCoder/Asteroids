
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.SelfDestruct;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class SelfDestructSystemTest {

    private World world = new World();

    @Before
    public void setUp () {
        world.addLogic(new SelfDestructSystem());
        world.init();
    }

    @Test
    public void entity_is_removed_if_time_just_elapsed () {
        Entity entity = world.createEntity();
        entity.set(new SelfDestruct(1.0));
        world.update(1.0);
        assertThat(world.getEntities(), not(hasItem(entity)));
    }

    @Test
    public void entity_is_removed_if_more_time_elapsed () {
        Entity entity = world.createEntity();
        entity.set(new SelfDestruct(1.0));
        world.update(1.1);
        assertThat(world.getEntities(), not(hasItem(entity)));
    }
}
