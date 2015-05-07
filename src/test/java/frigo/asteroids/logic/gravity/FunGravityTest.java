
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static frigo.asteroids.logic.gravity.FunGravity.G;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class FunGravityTest {

    private World world = new World();
    private Entity attractor1;
    private Entity attracted1;
    private Entity attracted2;
    private Entity attracted3;
    private GravityCalculator gravity = new FunGravity();

    @Before
    public void setUp () {
        world.register(Mass.class);
        world.register(Planar.class);

        attractor1 = world.createEntity();
        attractor1.setDouble(Mass.ID, 100);
        attractor1.set(Planar.ID, new Planar(vector(-0.1, 0), ZERO, ZERO));

        attracted1 = world.createEntity();
        attracted1.setDouble(Mass.ID, 10);
        attracted1.set(Planar.ID, new Planar(vector(0.1, 0), ZERO, ZERO));

        attracted2 = world.createEntity();
        attracted2.setDouble(Mass.ID, 1);
        attracted2.set(Planar.ID, new Planar(vector(0, 0.1), ZERO, ZERO));

        attracted3 = world.createEntity();
        attracted3.setDouble(Mass.ID, 1);
        attracted3.set(Planar.ID, new Planar(vector(-0.1, 0), ZERO, ZERO));
    }

    @Test
    public void directional_acceleration_is_calculated_properly () {
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted1),
            closeTo(vector(-0.2, 0).mul(G * 100 / 0.2), 1E-22));
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted2),
            closeTo(vector(-0.1, -0.1).mul(G * 100 / sqrt(0.02)), 1E-22));
    }

    @Test
    public void direction_acceleration_is_zero_for_objects_at_same_place () {
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted3), closeTo(ZERO));
    }

}
