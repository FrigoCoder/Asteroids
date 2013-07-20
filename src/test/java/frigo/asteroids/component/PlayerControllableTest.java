
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PlayerControllableTest {

    @Test
    public void constructor_stores_thrust () {
        PlayerControllable controllable = new PlayerControllable(1.0);
        assertThat(controllable.thrust, is(1.0));
    }

}
