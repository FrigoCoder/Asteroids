
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AngularVerletTest {

    private double zeroAcceleration = 0;
    private double acceleration = 0.6;
    private double velocity = 0.2;
    private double position = 0.4;
    private AngularVerlet verlet;

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        verlet = new AngularVerlet(zeroAcceleration, velocity, position);
        assertVelocity(1.0, velocity, 0);
        assertPosition(1.0, position + velocity, 0);
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new AngularVerlet(zeroAcceleration, velocity, position);
        assertVelocity(0.1, velocity, 0);
        assertPosition(0.1, position + velocity * 0.1, 0);
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        verlet = new AngularVerlet(acceleration, velocity, position);
        assertVelocity(1.0, velocity + acceleration, 0);
        assertPosition(1.0, position + velocity + acceleration * 0.5, 1E-15);
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new AngularVerlet(acceleration, velocity, position);
        assertVelocity(0.1, velocity + acceleration * 0.1, 0);
        assertPosition(0.1, position + velocity * 0.1 + acceleration * 0.5 * 0.1 * 0.1, 0);
    }

    private void assertVelocity (double elapsed, double expected, double tolerance) {
        assertThat(verlet.getVelocity(elapsed), closeTo(expected, tolerance));
    }

    private void assertPosition (double elapsed, double expected, double tolerance) {
        assertThat(verlet.getDisplacement(elapsed), closeTo(expected, tolerance));
    }

}
