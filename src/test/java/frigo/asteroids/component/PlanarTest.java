
package frigo.asteroids.component;

import static frigo.asteroids.component.Planar.planar;
import static frigo.asteroids.component.Vector.NULL;
import static frigo.asteroids.component.Vector.vector;
import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PlanarTest {

    private Vector position = vector(0.3, 0.4);
    private Vector velocity = vector(0.1, 0.2);
    private Vector acceleration = vector(0.5, 0.6);
    private Planar planar = planar().position(position).velocity(velocity);

    @Test
    public void accelerate_adds_accelerations () {
        Planar actual = planar.accelerate(acceleration);
        Planar expected = planar.acceleration(acceleration);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        Planar actual = planar.update(1);
        Planar expected = planar().position(position.add(velocity)).velocity(velocity);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        Planar actual = planar.update(0.1);
        Planar expected = planar().position(position.add(velocity.mul(0.1))).velocity(velocity).acceleration(NULL);
        assertThat(actual, is(expected));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        planar = planar.acceleration(acceleration);
        Planar actual = planar.update(1);
        Planar expected =
            planar().position(position.add(velocity).add(acceleration.mul(0.5))).velocity(velocity.add(acceleration));
        assertThat(actual.acceleration, is(expected.acceleration));
        assertThat(actual.velocity, is(expected.velocity));
        assertThat(actual.position, closeTo(expected.position, 1E-15));
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        planar = planar.acceleration(acceleration);
        Planar actual = planar.update(0.1);
        Planar expected =
            planar().position(position.add(velocity.mul(0.1)).add(acceleration.mul(0.5 * 0.1 * 0.1))).velocity(
                velocity.add(acceleration.mul(0.1)));

        assertThat(actual.acceleration, is(expected.acceleration));
        assertThat(actual.velocity, is(expected.velocity));
        assertThat(actual.position, closeTo(expected.position, 1E-15));
    }

}
