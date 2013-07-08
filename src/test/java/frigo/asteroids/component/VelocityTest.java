
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

}
