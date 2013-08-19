
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;
import frigo.asteroids.logic.movement.VelocityVerlet;

public class VelocityVerletTest {

    private Acceleration zeroAcceleration = new Acceleration(0, 0);
    private Acceleration acceleration = new Acceleration(0.5, 0.6);
    private Velocity velocity = new Velocity(0.1, 0.2);
    private Position position = new Position(0.3, 0.4);
    private VelocityVerlet verlet;

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        verlet = new VelocityVerlet(zeroAcceleration, velocity, position);
        assertVelocity(1.0, velocity, 0);
        assertPosition(1.0, position.add(velocity), 0);
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new VelocityVerlet(zeroAcceleration, velocity, position);
        assertVelocity(0.1, velocity, 0);
        assertPosition(0.1, position.add(velocity, 0.1), 0);
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        verlet = new VelocityVerlet(acceleration, velocity, position);
        assertVelocity(1.0, velocity.add(acceleration), 0);
        assertPosition(1.0, position.add(velocity).add(acceleration, 0.5), 1E-15);
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new VelocityVerlet(acceleration, velocity, position);
        assertVelocity(0.1, velocity.add(acceleration.mul(0.1)), 0);
        assertPosition(0.1, position.add(velocity, 0.1).add(acceleration, 0.5 * 0.1 * 0.1), 0);
    }

    private void assertVelocity (double elapsed, Velocity expected, double tolerance) {
        Velocity actual = verlet.getVelocity(elapsed);
        assertThat(actual.x, closeTo(expected.x, tolerance));
        assertThat(actual.y, closeTo(expected.y, tolerance));
    }

    private void assertPosition (double elapsed, Position expected, double tolerance) {
        Position actual = verlet.getPosition(elapsed);
        assertThat(actual.x, closeTo(expected.x, tolerance));
        assertThat(actual.y, closeTo(expected.y, tolerance));
    }

}
