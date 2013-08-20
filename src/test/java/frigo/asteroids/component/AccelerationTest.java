
package frigo.asteroids.component;

import static java.lang.Math.PI;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AccelerationTest {

    @Test
    public void add_adds_coordinates () {
        Acceleration augend = new Acceleration(1.0, 2.0);
        Acceleration addend = new Acceleration(3.0, 3.0);
        assertThat(augend.add(addend), is(new Acceleration(4.0, 5.0)));
    }

    @Test
    public void sub_subtracts_coordinates () {
        Acceleration minuend = new Acceleration(1.0, 2.0);
        Acceleration subtrahend = new Acceleration(3.0, 3.0);
        assertThat(minuend.sub(subtrahend), is(new Acceleration(-2.0, -1.0)));
    }

    @Test
    public void mul_multiplies_coordinates () {
        Acceleration position = new Acceleration(1.0, 2.0);
        assertThat(position.mul(3.0), is(new Acceleration(3.0, 6.0)));
    }

    @Test
    public void unit_x_rotated_by_zero () {
        Acceleration position = new Acceleration(1.0, 0.0);
        assertThat(position.rotate(0), is(position));
    }

    @Test
    public void unit_x_rotated_by_pi_per_2 () {
        Acceleration position = new Acceleration(1.0, 0.0);
        assertThat(position.rotate(PI / 2).x, closeTo(0.0, 1E-16));
        assertThat(position.rotate(PI / 2).y, is(1.0));
    }

    @Test
    public void unit_y_rotated_by_zero () {
        Acceleration position = new Acceleration(0.0, 1.0);
        assertThat(position.rotate(0), is(position));
    }

    @Test
    public void unit_y_rotated_by_pi_per_2 () {
        Acceleration position = new Acceleration(0.0, 1.0);
        assertThat(position.rotate(PI / 2).x, is(-1.0));
        assertThat(position.rotate(PI / 2).y, closeTo(0.0, 1E-16));
    }

}
