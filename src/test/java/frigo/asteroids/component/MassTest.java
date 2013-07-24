
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MassTest {

    @Test
    public void constructor_initializes_mass () {
        Mass mass = new Mass(1.0);
        assertThat(mass.kg, is(1.0));
    }

}
