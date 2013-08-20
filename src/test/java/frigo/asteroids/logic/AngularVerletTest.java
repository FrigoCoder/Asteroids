
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.component.AngularAcceleration;
import frigo.asteroids.component.AngularDisplacement;
import frigo.asteroids.component.AngularVelocity;
import frigo.asteroids.logic.rotation.AngularVerlet;

public class AngularVerletTest {

    private AngularAcceleration zeroAcceleration = new AngularAcceleration(0);
    private AngularAcceleration acceleration = new AngularAcceleration(0.6);
    private AngularVelocity velocity = new AngularVelocity(0.2);
    private AngularDisplacement position = new AngularDisplacement(0.4);
    private AngularVerlet verlet;

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        verlet = new AngularVerlet(zeroAcceleration, velocity, position);
        assertThat(verlet.getVelocity(1.0), is(velocity));
        assertThat(verlet.getDisplacement(1.0), is(position.add(velocity)));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new AngularVerlet(zeroAcceleration, velocity, position);
        assertThat(verlet.getVelocity(0.1), is(velocity));
        assertThat(verlet.getDisplacement(0.1), is(position.add(velocity.mul(0.1))));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        verlet = new AngularVerlet(acceleration, velocity, position);
        assertThat(verlet.getVelocity(1.0), is(velocity.add(acceleration)));
        assertThat(verlet.getDisplacement(1.0), is(position.add(velocity.add(acceleration.mul(0.5)))));
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        verlet = new AngularVerlet(acceleration, velocity, position);
        assertThat(verlet.getVelocity(0.1), is(velocity.add(acceleration.mul(0.1))));
        assertThat(verlet.getDisplacement(0.1),
            is(position.add(velocity.mul(0.1).add(acceleration.mul(0.5 * 0.1 * 0.1)))));
    }

}
