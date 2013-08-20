
package frigo.asteroids.component;

import static java.lang.Math.PI;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PositionTest {

    @Test
    public void add_adds_coordinates () {
        Position augend = new Position(1.0, 2.0);
        Position addend = new Position(3.0, 3.0);
        assertThat(augend.add(addend), is(new Position(4.0, 5.0)));
    }

    @Test
    public void sub_subtracts_coordinates () {
        Position minuend = new Position(1.0, 2.0);
        Position subtrahend = new Position(3.0, 3.0);
        assertThat(minuend.sub(subtrahend), is(new Position(-2.0, -1.0)));
    }

    @Test
    public void mul_multiplies_coordinates () {
        Position position = new Position(1.0, 2.0);
        assertThat(position.mul(3.0), is(new Position(3.0, 6.0)));
    }

    @Test
    public void unit_x_rotated_by_zero () {
        Position position = new Position(1.0, 0.0);
        assertThat(position.rotate(0), is(position));
    }

    @Test
    public void unit_x_rotated_by_pi_per_2 () {
        Position position = new Position(1.0, 0.0);
        assertThat(position.rotate(PI / 2).x, closeTo(0.0, 1E-16));
        assertThat(position.rotate(PI / 2).y, is(1.0));
    }

    @Test
    public void unit_y_rotated_by_zero () {
        Position position = new Position(0.0, 1.0);
        assertThat(position.rotate(0), is(position));
    }

    @Test
    public void unit_y_rotated_by_pi_per_2 () {
        Position position = new Position(0.0, 1.0);
        assertThat(position.rotate(PI / 2).x, is(-1.0));
        assertThat(position.rotate(PI / 2).y, closeTo(0.0, 1E-16));
    }

}
