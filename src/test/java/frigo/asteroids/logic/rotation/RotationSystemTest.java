
package frigo.asteroids.logic.rotation;

import static frigo.asteroids.component.Angular.angular;
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
        world.set(entity, angular().position(0.5).velocity(1).acceleration(1));
        world.update(0.1);
        assertThat(world.get(entity, Angular.class), is(angular().position(0.605).velocity(1.1)));
    }

    @Test
    public void updates_AngularDisplacement_of_entities_by_AngularVelocity_and_elapsed_seconds () {
        world.set(entity, angular().velocity(1));
        world.update(0.1);
        assertThat(world.get(entity, Angular.class), is(angular().position(0.1).velocity(1)));
    }

}
