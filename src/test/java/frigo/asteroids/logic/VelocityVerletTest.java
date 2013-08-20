
package frigo.asteroids.logic;

import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static org.hamcrest.Matchers.is;
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
        assertThat(verlet.getVelocity(1.0), is(velocity));
        assertThat(verlet.getPosition(1.0), is(position.add(velocity)));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new VelocityVerlet(zeroAcceleration, velocity, position);
        assertThat(verlet.getVelocity(0.1), is(velocity));
        assertThat(verlet.getPosition(0.1), is(position.add(velocity.mul(0.1))));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        verlet = new VelocityVerlet(acceleration, velocity, position);
        assertThat(verlet.getVelocity(1.0), is(velocity.add(acceleration)));
        assertThat(verlet.getPosition(1.0), closeTo(position.add(velocity).add(acceleration.mul(0.5)), 1E-15));
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new VelocityVerlet(acceleration, velocity, position);
        assertThat(verlet.getVelocity(0.1), is(velocity.add(acceleration.mul(0.1))));
        assertThat(verlet.getPosition(0.1), is(position.add(velocity.mul(0.1)).add(acceleration.mul(0.5 * 0.1 * 0.1))));
    }

}
