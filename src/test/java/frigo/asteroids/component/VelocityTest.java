
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VelocityTest {

    @Test
    public void speed_multiplied_result_in_multiplied_coordinates () {
        Velocity velocity = new Velocity(1.0, 2.0);
        assertThat(velocity.mul(0.5), is(new Velocity(0.5, 1.0)));
    }

    @Test
    public void components_are_added_properly_considering_elapsed_time () {
        Velocity velocity = new Velocity(0.5, 0.5);
        Acceleration acceleration = new Acceleration(0.1, -0.1);
        double elapsed = 0.1;
        assertThat(velocity.add(acceleration, elapsed), is(new Velocity(0.51, 0.49)));
    }

}
