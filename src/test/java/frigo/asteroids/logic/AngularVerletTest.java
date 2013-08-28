
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.Angular;
import frigo.asteroids.logic.rotation.AngularVerlet;

public class AngularVerletTest {

    private double zeroAcceleration = 0;
    private double acceleration = 0.6;
    private double velocity = 0.2;
    private double position = 0.4;
    private AngularVerlet verlet;

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        verlet = new AngularVerlet(new Angular(position, velocity, zeroAcceleration));
        assertThat(verlet.getVelocity(1.0), is(velocity));
        assertThat(verlet.getDisplacement(1.0), is(position + velocity));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new AngularVerlet(new Angular(position, velocity, zeroAcceleration));
        assertThat(verlet.getVelocity(0.1), is(velocity));
        assertThat(verlet.getDisplacement(0.1), is(position + velocity * 0.1));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        verlet = new AngularVerlet(new Angular(position, velocity, acceleration));
        assertThat(verlet.getVelocity(1.0), is(velocity + acceleration));
        assertThat(verlet.getDisplacement(1.0), is(position + (velocity + acceleration * 0.5)));
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new AngularVerlet(new Angular(position, velocity, acceleration));
        assertThat(verlet.getVelocity(0.1), is(velocity + acceleration * 0.1));
        assertThat(verlet.getDisplacement(0.1), is(position + (velocity * 0.1 + acceleration * (0.5 * 0.1 * 0.1))));
    }

}
