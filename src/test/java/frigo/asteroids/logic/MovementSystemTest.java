
package frigo.asteroids.logic;

import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;
import frigo.asteroids.logic.movement.MovementSystem;

public class MovementSystemTest {

    private World world = new World();
    private Entity entity = world.createEntity();

    @Before
    public void setUp () {
        world.addLogic(new MovementSystem());
        world.init();
    }

    @Test
    public void properly_updates_velocity_and_position_of_entities_with_acceleration_and_velocity () {
        entity.add(Planar.ID, new Planar(vector(0.5, 0.5), vector(1, -1), vector(1, 1)));
        world.update(0.1);
        assertThat(entity.get(Planar.ID), is(new Planar(vector(0.605, 0.405), vector(1.1, -0.9), ZERO)));
    }

    @Test
    public void updates_position_of_entities_by_velocity_and_elapsed_seconds () {
        entity.add(Planar.ID, new Planar(vector(0, 0.1), vector(1, 1), ZERO));
        world.update(0.1);
        assertThat(entity.get(Planar.ID), is(new Planar(vector(0.1, 0.2), vector(1, 1), ZERO)));
    }
}
