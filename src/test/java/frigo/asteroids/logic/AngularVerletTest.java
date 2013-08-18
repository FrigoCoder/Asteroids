
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.AngularAcceleration;
import frigo.asteroids.component.AngularDisplacement;
import frigo.asteroids.component.AngularVelocity;

public class AngularVerletTest {

    private AngularAcceleration zeroAcceleration = new AngularAcceleration(0);
    private AngularAcceleration acceleration = new AngularAcceleration(0.6);
    private AngularVelocity velocity = new AngularVelocity(0.2);
    private AngularDisplacement position = new AngularDisplacement(0.4);
    private AngularVerlet verlet;

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        verlet = new AngularVerlet(zeroAcceleration, velocity, position);
        assertVelocity(1.0, velocity, 0);
        assertPosition(1.0, position.add(velocity), 0);
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new AngularVerlet(zeroAcceleration, velocity, position);
        assertVelocity(0.1, velocity, 0);
        assertPosition(0.1, position.add(velocity.mul(0.1)), 0);
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        verlet = new AngularVerlet(acceleration, velocity, position);
        assertVelocity(1.0, velocity.add(acceleration), 0);
        assertPosition(1.0, position.add(velocity.add(acceleration.mul(0.5))), 1E-15);
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new AngularVerlet(acceleration, velocity, position);
        assertVelocity(0.1, velocity.add(acceleration.mul(0.1)), 0);
        assertPosition(0.1, position.add(velocity.mul(0.1).add(acceleration.mul(0.5 * 0.1 * 0.1))), 0);
    }

    private void assertVelocity (double elapsed, AngularVelocity expected, double tolerance) {
        assertThat(verlet.getVelocity(elapsed).value, closeTo(expected.value, tolerance));
    }

    private void assertPosition (double elapsed, AngularDisplacement expected, double tolerance) {
        assertThat(verlet.getDisplacement(elapsed).value, closeTo(expected.value, tolerance));
    }

}
