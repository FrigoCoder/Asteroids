
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AccelerationTest {

    private Acceleration acceleration = new Acceleration(2.0, 3.0);

    @Test
    public void add_implicit_vector () {
        assertThat(acceleration.add(1.0, 1.0), is(new Acceleration(3.0, 4.0)));
    }

    @Test
    public void add_explicit_vector () {
        assertThat(acceleration.add(new Vector(1.0, 1.0)), is(new Acceleration(3.0, 4.0)));
    }

}
