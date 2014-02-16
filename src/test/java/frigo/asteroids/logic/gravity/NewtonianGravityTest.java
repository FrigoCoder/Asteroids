
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static frigo.asteroids.logic.gravity.NewtonianGravity.G;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class NewtonianGravityTest {

    private World world = new World();
    private Entity attractor1;
    private Entity attracted1;
    private Entity attracted2;
    private Entity attracted3;
    private GravityCalculator gravity = new NewtonianGravity();

    @Before
    public void setUp () {
        attractor1 = world.createEntity();
        attractor1.add(Mass.ID, new Mass(100));
        attractor1.add(Planar.ID, new Planar(vector(-0.1, 0), ZERO, ZERO));

        attracted1 = world.createEntity();
        attracted1.add(Mass.ID, new Mass(10));
        attracted1.add(Planar.ID, new Planar(vector(0.1, 0), ZERO, ZERO));

        attracted2 = world.createEntity();
        attracted2.add(Mass.ID, new Mass(1));
        attracted2.add(Planar.ID, new Planar(vector(0, 0.1), ZERO, ZERO));

        attracted3 = world.createEntity();
        attracted3.add(Mass.ID, new Mass(1));
        attracted3.add(Planar.ID, new Planar(vector(-0.1, 0), ZERO, ZERO));
    }

    @Test
    public void directional_acceleration_is_calculated_properly () {
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted1), closeTo(vector(-0.2, 0).mul(
            G * 100 / 0.04), 1E-22));
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted2), closeTo(vector(-0.1, -0.1).mul(
            G * 100 / 0.02), 1E-22));
    }

    @Test
    public void direction_acceleration_is_zero_for_objects_at_same_place () {
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted3), closeTo(ZERO));
    }

}
