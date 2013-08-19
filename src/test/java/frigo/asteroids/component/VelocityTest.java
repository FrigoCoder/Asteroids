
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VelocityTest {

    @Test
    public void components_are_added_properly_considering_elapsed_time () {
        Velocity velocity = new Velocity(0.5, 0.5);
        Acceleration acceleration = new Acceleration(0.1, -0.1);
        double elapsed = 0.1;
        assertThat(velocity.add(acceleration.mul(elapsed)), is(new Velocity(0.51, 0.49)));
    }

}
