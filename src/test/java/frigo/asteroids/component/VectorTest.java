
package frigo.asteroids.component;

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
        Vector vector = new Vector(1.0, 0.0);
        assertThat(vector.length(), is(1.0));
    }

    @Test
    public void length_of_unit_y_is_one () {
        Vector vector = new Vector(0.0, 1.0);
        assertThat(vector.length(), is(1.0));
    }

    @Test
    public void length_of_45_degree_unit_is_one () {
        Vector vector = new Vector(cos(PI / 4), sin(PI / 4));
        assertThat(vector.length(), is(1.0));
    }

    @Test
    public void add_adds_coordinates () {
        Vector augend = new Vector(1.0, 2.0);
        Vector addend = new Vector(3.0, 3.0);
        assertThat(augend.add(addend), is(new Vector(4.0, 5.0)));
    }

    @Test
    public void sub_subtracts_coordinates () {
        Vector minuend = new Vector(1.0, 2.0);
        Vector subtrahend = new Vector(3.0, 3.0);
        assertThat(minuend.sub(subtrahend), is(new Vector(-2.0, -1.0)));
    }

    @Test
    public void mul_multiplies_coordinates () {
        Vector vector = new Vector(1.0, 2.0);
        assertThat(vector.mul(3.0), is(new Vector(3.0, 6.0)));
    }

    @Test
    public void div_divides_coordinates () {
        Vector vector = new Vector(1.0, 2.0);
        assertThat(vector.div(2.0), is(new Vector(0.5, 1.0)));
    }

    @Test
    public void unit_x_rotated_by_zero () {
        Vector vector = new Vector(1.0, 0.0);
        assertThat(vector.rotate(0), is(vector));
    }

    @Test
    public void unit_x_rotated_by_pi_per_2 () {
        Vector vector = new Vector(1.0, 0.0);
        assertThat(vector.rotate(PI / 2).x, closeTo(0.0, 1E-16));
        assertThat(vector.rotate(PI / 2).y, is(1.0));
    }

    @Test
    public void unit_y_rotated_by_zero () {
        Vector vector = new Vector(0.0, 1.0);
        assertThat(vector.rotate(0), is(vector));
    }

    @Test
    public void unit_y_rotated_by_pi_per_2 () {
        Vector vector = new Vector(0.0, 1.0);
        assertThat(vector.rotate(PI / 2).x, is(-1.0));
        assertThat(vector.rotate(PI / 2).y, closeTo(0.0, 1E-16));
    }

}
