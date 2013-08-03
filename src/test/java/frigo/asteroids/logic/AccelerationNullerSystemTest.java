
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
    }

    @Test
    public void update_sets_all_acceleration_to_zero () {
        entity.set(new Acceleration(1.0, -1.0));
        system.update(world, 1.0);
        assertThat(entity.get(Acceleration.class), is(new Acceleration(0.0, 0.0)));

    }

    @Test
    public void update_does_not_do_anything_to_entities_without_acceleration () {
        system.update(world, 1.0);
        assertThat(entity.has(Acceleration.class), is(false));
    }

}
