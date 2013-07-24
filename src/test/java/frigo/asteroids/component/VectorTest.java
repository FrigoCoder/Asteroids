
package frigo.asteroids.component;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VectorTest {

    @Test
    public void length_of_a_unit_x_vector_is_one () {
        Vector vector = new Vector(1.0, 0.0);
        assertThat(vector.length(), is(1.0));
    }

    @Test
    public void length_of_a_unit_y_vector_is_one () {
        Vector vector = new Vector(0.0, 1.0);
        assertThat(vector.length(), is(1.0));
    }

    @Test
    public void length_of_a_45_degree_unit_vector_is_one () {
        Vector vector = new Vector(cos(PI / 4), sin(PI / 4));
        assertThat(vector.length(), is(1.0));
    }

    @Test
    public void vectors_are_added_correctly () {
        Vector augend = new Vector(1.0, 2.0);
        Vector addend = new Vector(3.0, 3.0);
        assertThat(augend.add(addend), is(new Vector(4.0, 5.0)));
    }

    @Test
    public void vector_multiplied_by_a_scalar_equals_to_vector_of_coordinates_multiplied_by_scalar () {
        Vector vector = new Vector(1.0, 2.0);
        assertThat(vector.mul(3.0), is(new Vector(3.0, 6.0)));
    }

}
