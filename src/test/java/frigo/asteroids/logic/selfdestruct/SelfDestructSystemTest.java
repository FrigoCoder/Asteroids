
package frigo.asteroids.logic.selfdestruct;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.SelfDestruct;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class SelfDestructSystemTest {

    private World world = new World();

    @Before
    public void setUp () {
        world.register(SelfDestruct.class);
        world.addLogic(new SelfDestructSystem());
        world.init();
    }

    @Test
    public void unrelated_entity_is_not_removed () {
        Entity entity = world.createEntity();
        world.update(1.0);
        Aspect aspect = Aspect.allOf();
        assertThat(world.getEntitiesFor(aspect), hasItem(entity));
    }

    @Test
    public void entity_is_removed () {
        Entity entity = world.createEntity();
        entity.setFlag(SelfDestruct.ID);
        world.update(1.0);
        Aspect aspect = Aspect.allOf();
        assertThat(world.getEntitiesFor(aspect), not(hasItem(entity)));
    }

}
