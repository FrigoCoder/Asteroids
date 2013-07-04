
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SpeedTest {

    @Test
    public void speed_multiplied_result_in_multiplied_coordinates () {
        Speed speed = new Speed(1.0, 2.0);
        assertThat(speed.mul(0.5), is(new Speed(0.5, 1.0)));
    }

}
