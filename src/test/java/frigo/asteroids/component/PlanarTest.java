
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
    private Planar planar = planar().position(position).velocity(velocity).acceleration(acceleration);

    @Test
    public void position_changes_position () {
        assertEquals(planar.position(NULL), planar().position(NULL).velocity(velocity).acceleration(acceleration));
    }

    @Test
    public void position_with_coordinates_changes_position () {
        assertEquals(planar.position(0, 0), planar().position(NULL).velocity(velocity).acceleration(acceleration));
    }

    @Test
    public void velocity_changes_velocity () {
        assertEquals(planar.velocity(NULL), planar().position(position).velocity(NULL).acceleration(acceleration));
    }

    @Test
    public void velocity_with_coordinates_changes_velocity () {
        assertEquals(planar.velocity(0, 0), planar().position(position).velocity(NULL).acceleration(acceleration));
    }

    @Test
    public void acceleration_changes_acceleration () {
        assertEquals(planar.acceleration(NULL), planar().position(position).velocity(velocity).acceleration(NULL));
    }

    @Test
    public void acceleration_with_coordinates_changes_acceleration () {
        assertEquals(planar.acceleration(0, 0), planar().position(position).velocity(velocity).acceleration(NULL));
    }

    @Test
    public void accelerate_adds_accelerations () {
        planar = planar.acceleration(NULL);
        Planar actual = planar.accelerate(acceleration);
        Planar expected = planar.acceleration(acceleration);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        planar = planar.acceleration(NULL);
        Planar actual = planar.update(1);
        Planar expected = planar().position(position.add(velocity)).velocity(velocity);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        planar = planar.acceleration(NULL);
        Planar actual = planar.update(0.1);
        Planar expected = planar().position(position.add(velocity.mul(0.1))).velocity(velocity).acceleration(NULL);
        assertThat(actual, is(expected));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        Planar actual = planar.update(1);
        Planar expected =
            planar().position(position.add(velocity).add(acceleration.mul(0.5))).velocity(velocity.add(acceleration));
        assertClose(actual, expected, 1E-15);
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        Planar actual = planar.update(0.1);
        Planar expected =
            planar().position(position.add(velocity.mul(0.1)).add(acceleration.mul(0.5 * 0.1 * 0.1))).velocity(
                velocity.add(acceleration.mul(0.1)));
        assertClose(actual, expected, 1E-15);
    }

    private void assertEquals (Planar actual, Planar expected) {
        assertClose(actual, expected, 0);
    }

    private void assertClose (Planar actual, Planar expected, double tolerance) {
        assertThat(actual.position, closeTo(expected.position, tolerance));
        assertThat(actual.velocity, closeTo(expected.velocity, tolerance));
        assertThat(actual.acceleration, closeTo(expected.acceleration, tolerance));
    }

}
