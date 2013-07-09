
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PositionTest {

    @Test
    public void too_low_coordinates_are_wrapped () {
        Position position = new Position(-9, -11);
        assertThat(position, is(new Position(-1, -1)));
    }

    @Test
    public void too_high_coordinate_are_wrapped () {
        Position position = new Position(8.5, 10.5);
        assertThat(position, is(new Position(0.5, 0.5)));
    }

    @Test
    public void lower_limit_stays_the_same () {
        Position position = new Position(-1, -1);
        assertThat(position, is(new Position(-1, -1)));
    }

    @Test
    public void upper_limit_is_wrapped () {
        Position position = new Position(1, 1);
        assertThat(position, is(new Position(-1, -1)));
    }

    @Test
    public void coordinates_are_added_properly_considering_elapsed_time () {
        Position position = new Position(0.5, 0.5);
        Velocity velocity = new Velocity(0.1, -0.1);
        double elapsed = 0.1;
        assertThat(position.add(velocity, elapsed), is(new Position(0.51, 0.49)));
    }

}
