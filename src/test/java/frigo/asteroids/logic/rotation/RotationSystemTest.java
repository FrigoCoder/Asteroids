
package frigo.asteroids.logic.rotation;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.AngularAcceleration;
import frigo.asteroids.component.AngularDisplacement;
import frigo.asteroids.component.AngularVelocity;
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
    public void
        properly_updates_AngularVelocity_and_AngularDisplacement_of_entities_with_acceleration_and_AngularVelocity () {
        world.set(entity, new AngularAcceleration(1.0));
        world.set(entity, new AngularVelocity(1.0));
        world.set(entity, new AngularDisplacement(0.5));

        world.update(0.1);

        assertThat(world.get(entity, AngularVelocity.class), is(new AngularVelocity(1.1)));
        assertThat(world.get(entity, AngularDisplacement.class), is(new AngularDisplacement(0.605)));
    }

    @Test
    public void updates_AngularDisplacement_of_entities_by_AngularVelocity_and_elapsed_seconds () {
        world.set(entity, new AngularVelocity(1.0));
        world.set(entity, new AngularDisplacement(0.0));

        world.update(0.1);

        assertThat(world.get(entity, AngularDisplacement.class), is(new AngularDisplacement(0.1)));
    }

    @Test
    public void does_not_update_AngularVelocity_of_entities_without_acceleration () {
        world.set(entity, new AngularVelocity(1.0));

        world.update(0.1);

        assertThat(world.get(entity, AngularVelocity.class), is(new AngularVelocity(1.0)));
    }

    @Test
    public void does_not_update_AngularDisplacement_of_entities_without_AngularVelocity () {
        world.set(entity, new AngularDisplacement(0.0));

        world.update(0.1);

        assertThat(world.get(entity, AngularDisplacement.class), is(new AngularDisplacement(0.0)));
    }

}
