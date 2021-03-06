
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PlayerControllableTest {

    @Test
    public void constructor_stores_thrust () {
        Thrustable controllable = new Thrustable(1, 2);
        assertThat(controllable.thrust, is(1.0));
    }

    @Test
    public void constructor_stores_rotation () {
        Thrustable controllable = new Thrustable(1, 2);
        assertThat(controllable.angularThrust, is(2.0));
    }

}
