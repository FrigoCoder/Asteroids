
package frigo.asteroids.logic.rotation;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Angular;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class RotationSystemTest {

    private World world = new World();
    private Entity entity = world.createEntity();

    @Before
    public void setUp () {
        world.addLogic(new RotationSystem());
        world.init();
    }

    @Test
    public void properly_updates_velocity_and_position_with_acceleration_and_velocity () {
        entity.add(new Angular(0.5, 1, 1));
        world.update(0.1);
        assertThat(entity.get(Angular.class), is(new Angular(0.605, 1.1, 0.0)));
    }

    @Test
    public void updates_AngularDisplacement_of_entities_by_AngularVelocity_and_elapsed_seconds () {
        entity.add(new Angular(0, 1, 0));
        world.update(0.1);
        assertThat(entity.get(Angular.class), is(new Angular(0.1, 1, 0)));
    }

}
