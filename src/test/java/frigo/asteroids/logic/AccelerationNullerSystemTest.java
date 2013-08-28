
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.AngularAcceleration;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class AccelerationNullerSystemTest {

    private AccelerationNullerSystem system = new AccelerationNullerSystem();

    private World world = new World();
    private Entity entity = world.createEntity();

    @Before
    public void setUp () {
        world.addLogic(system);
        world.init();
    }

    @Test
    public void sets_angular_acceleration_to_zero () {
        world.set(entity, new AngularAcceleration(1.0));
        world.update(1.0);
        assertThat(world.get(entity, AngularAcceleration.class), is(new AngularAcceleration(0.0)));

    }

    @Test
    public void does_not_create_angular_acceleration () {
        world.update(1.0);
        assertThat(world.has(entity, AngularAcceleration.class), is(false));
    }

}
