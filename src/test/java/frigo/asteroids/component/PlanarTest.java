
package frigo.asteroids.component;

import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static frigo.asteroids.core.Vector.NULL;
import static frigo.asteroids.core.Vector.vector;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.core.Vector;

public class PlanarTest {

    private Vector position = vector(0.3, 0.4);
    private Vector velocity = vector(0.1, 0.2);
    private Vector acceleration = vector(0.5, 0.6);

    @Test
    public void accelerate_adds_accelerations () {
        Planar actual = new Planar(position, velocity, NULL);
        actual.accelerate(acceleration);

        Planar expected = new Planar(position, velocity, acceleration);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        Planar actual = new Planar(position, velocity, NULL);
        actual.update(1);

        Planar expected = new Planar(position.add(velocity), velocity, NULL);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        Planar actual = new Planar(position, velocity, NULL);
        actual.update(0.1);

        Planar expected = new Planar(position.add(velocity.mul(0.1)), velocity, NULL);
        assertThat(actual, is(expected));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        Planar actual = new Planar(position, velocity, acceleration);
        actual.update(1);

        Planar expected =
            new Planar(position.add(velocity).add(acceleration.mul(0.5)), velocity.add(acceleration), NULL);
        assertClose(actual, expected, 1E-15);
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        Planar actual = new Planar(position, velocity, acceleration);
        actual.update(0.1);

        Planar expected =
            new Planar(position.add(velocity.mul(0.1)).add(acceleration.mul(0.5 * 0.1 * 0.1)), velocity
                .add(acceleration.mul(0.1)), NULL);
        assertClose(actual, expected, 1E-15);
    }

    private void assertClose (Planar actual, Planar expected, double tolerance) {
        assertThat(actual.position, closeTo(expected.position, tolerance));
        assertThat(actual.velocity, closeTo(expected.velocity, tolerance));
        assertThat(actual.acceleration, closeTo(expected.acceleration, tolerance));
    }

}
