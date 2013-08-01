
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.logic.gravity.FunGravity.G;
import static java.lang.Math.sqrt;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Entity;

public class FunGravityTest {

    private Entity attractor1 = new Entity(new Mass(100), new Position(-0.1, 0.0));
    private Entity attracted1 = new Entity(new Mass(10), new Position(0.1, 0.0));
    private Entity attracted2 = new Entity(new Mass(1), new Position(0.0, 0.1));
    private Entity attracted3 = new Entity(new Mass(1), new Position(-0.1, 0.0));

    private GravityCalculator gravity = new FunGravity();

    @Test
    public void directional_acceleration_is_calculated_properly () {
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted1),
            is(new Vector(-0.2, 0.0).mul(G * 100 / 0.2)));
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted2),
            is(new Vector(-0.1, -0.1).mul(G * 100 / sqrt(0.02))));
    }

    @Test
    public void direction_acceleration_is_zero_for_objects_at_same_place () {
        assertThat(gravity.getDirectionalAcceleration(attractor1, attracted3), is(new Vector(0, 0)));
    }

}
