
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class AccelerationNullerSystemTest {

    private AccelerationNullerSystem system = new AccelerationNullerSystem();

    private World world = new World();
    private Entity entity = world.createEntity();

    @Before
    public void setUp () {
        world.addEntity(entity);
        world.addLogic(system);
        world.init();
    }

    @Test
    public void update_sets_all_acceleration_to_zero () {
        world.set(entity, new Acceleration(1.0, -1.0));
        world.update(1.0);
        assertThat(world.get(entity, Acceleration.class), is(new Acceleration(0.0, 0.0)));

    }

    @Test
    public void update_does_not_do_anything_to_entities_without_acceleration () {
        world.update(1.0);
        assertThat(world.has(entity, Acceleration.class), is(false));
    }

}
