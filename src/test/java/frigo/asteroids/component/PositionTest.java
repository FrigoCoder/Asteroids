
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PositionTest {

    @Test
    public void coordinates_are_added_properly_considering_elapsed_time () {
        Position position = new Position(0.5, 0.5);
        Velocity velocity = new Velocity(0.1, -0.1);
        double elapsed = 0.1;
        assertThat(position.add(velocity, elapsed), is(new Position(0.51, 0.49)));
    }

}
