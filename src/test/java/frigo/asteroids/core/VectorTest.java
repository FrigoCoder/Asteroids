
package frigo.asteroids.core;

import static frigo.asteroids.core.Vector.X;
import static frigo.asteroids.core.Vector.Y;
import static frigo.asteroids.core.Vector.vector;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VectorTest {

    @Test
    public void length_of_unit_x_is_one () {
        assertThat(X.length(), is(1.0));
    }

    @Test
    public void length_of_unit_y_is_one () {
        assertThat(Y.length(), is(1.0));
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
        assertThat(X.rotate(0), is(X));
    }

    @Test
    public void unit_x_rotated_by_pi_per_2 () {
        assertThat(X.rotate(PI / 2).x, closeTo(0, 1E-16));
        assertThat(X.rotate(PI / 2).y, is(1.0));
    }

    @Test
    public void unit_y_rotated_by_zero () {
        assertThat(Y.rotate(0), is(Y));
    }

    @Test
    public void unit_y_rotated_by_pi_per_2 () {
        assertThat(Y.rotate(PI / 2).x, is(-1.0));
        assertThat(Y.rotate(PI / 2).y, closeTo(0, 1E-16));
    }

    @Test
    public void normalize_normalizes_nonnull_vector () {
        assertThat(vector(3, 4).normalize(), is(vector(0.6, 0.8)));
    }

    @Test
    public void normalize_returns_null_vector_for_null_vector () {
        assertThat(Vector.ZERO.normalize(), is(Vector.ZERO));
    }

    @Test
    public void opposite_negates_coordinates () {
        assertThat(vector(1, 2).opposite(), is(vector(-1, -2)));
    }
}
