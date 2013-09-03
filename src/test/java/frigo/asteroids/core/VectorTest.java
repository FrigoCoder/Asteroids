
package frigo.asteroids.core;

import static frigo.asteroids.core.Vector.UNIT_X;
import static frigo.asteroids.core.Vector.UNIT_Y;
import static frigo.asteroids.core.Vector.vector;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.core.Vector;

public class VectorTest {

    @Test
    public void length_of_unit_x_is_one () {
        assertThat(UNIT_X.length(), is(1.0));
    }

    @Test
    public void length_of_unit_y_is_one () {
        assertThat(UNIT_Y.length(), is(1.0));
    }

    @Test
    public void length_of_45_degree_unit_is_one () {
        assertThat(vector(cos(PI / 4), sin(PI / 4)).length(), is(1.0));
    }

    @Test
    public void add_adds_coordinates () {
        assertThat(vector(1, 2).add(vector(3, 3)), is(vector(4, 5)));
    }

    @Test
    public void sub_subtracts_coordinates () {
        assertThat(vector(1, 2).sub(vector(3, 3)), is(vector(-2, -1)));
    }

    @Test
    public void mul_multiplies_coordinates () {
        assertThat(vector(1, 2).mul(3), is(vector(3, 6)));
    }

    @Test
    public void div_divides_coordinates () {
        assertThat(vector(1, 2).div(2), is(vector(0.5, 1)));
    }

    @Test
    public void unit_x_rotated_by_zero () {
        assertThat(UNIT_X.rotate(0), is(UNIT_X));
    }

    @Test
    public void unit_x_rotated_by_pi_per_2 () {
        assertThat(UNIT_X.rotate(PI / 2).x, closeTo(0, 1E-16));
        assertThat(UNIT_X.rotate(PI / 2).y, is(1.0));
    }

    @Test
    public void unit_y_rotated_by_zero () {
        assertThat(UNIT_Y.rotate(0), is(UNIT_Y));
    }

    @Test
    public void unit_y_rotated_by_pi_per_2 () {
        assertThat(UNIT_Y.rotate(PI / 2).x, is(-1.0));
        assertThat(UNIT_Y.rotate(PI / 2).y, closeTo(0, 1E-16));
    }

    @Test
    public void normalize_normalizes_nonnull_vector () {
        assertThat(vector(3, 4).normalize(), is(vector(0.6, 0.8)));
    }

    @Test
    public void normalize_returns_null_vector_for_null_vector () {
        assertThat(Vector.NULL.normalize(), is(Vector.NULL));
    }
}