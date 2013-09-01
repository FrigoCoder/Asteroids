
package frigo.asteroids.logic;

import static frigo.asteroids.component.Vector.NULL;
import static frigo.asteroids.component.Vector.vector;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Vector;
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
        Vector acceleration = vector(1, 1);
        Vector velocity = vector(1, -1);
        Vector position = vector(0.5, 0.5);
        world.set(entity, new Planar(position, velocity, acceleration));

        world.update(0.1);

        assertThat(world.get(entity, Planar.class).acceleration, is(NULL));
        assertThat(world.get(entity, Planar.class).velocity, is(vector(1.1, -0.9)));
        assertThat(world.get(entity, Planar.class).position, is(vector(0.605, 0.405)));
    }

    @Test
    public void updates_position_of_entities_by_velocity_and_elapsed_seconds () {
        Vector acceleration = NULL;
        Vector velocity = vector(1, 1);
        Vector position = vector(0, 0.1);
        world.set(entity, new Planar(position, velocity, acceleration));

        world.update(0.1);

        assertThat(world.get(entity, Planar.class).acceleration, is(NULL));
        assertThat(world.get(entity, Planar.class).velocity, is(vector(1, 1)));
        assertThat(world.get(entity, Planar.class).position, is(vector(0.1, 0.2)));
    }

}
