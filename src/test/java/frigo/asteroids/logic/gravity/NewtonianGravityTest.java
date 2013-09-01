
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.component.Planar.planar;
import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static frigo.asteroids.core.Vector.NULL;
import static frigo.asteroids.core.Vector.vector;
import static frigo.asteroids.logic.gravity.NewtonianGravity.G;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Mass;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class NewtonianGravityTest {

    private World world = new World();
    private Entity attractor1 = world.createEntity(new Mass(100), planar().position(-0.1, 0));
    private Entity attracted1 = world.createEntity(new Mass(10), planar().position(0.1, 0));
    private Entity attracted2 = world.createEntity(new Mass(1), planar().position(0, 0.1));
    private Entity attracted3 = world.createEntity(new Mass(1), planar().position(-0.1, 0));
    private GravityCalculator gravity = new NewtonianGravity(world);

    @Test
    public void directional_acceleration_is_calculated_properly () {
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted1),
            closeTo(vector(-0.2, 0).mul(G * 100 / 0.04)));
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted2),
            closeTo(vector(-0.1, -0.1).mul(G * 100 / 0.02)));
    }

    @Test
    public void direction_acceleration_is_zero_for_objects_at_same_place () {
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted3), closeTo(NULL));
    }

}
