
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AngularVelocityTest {

    private AngularVelocity scalar = new AngularVelocity(1.0);

    @Test
    public void add_scalar () {
        assertThat(scalar.add(new AngularVelocity(2.0)), is(new AngularVelocity(3.0)));
    }

    @Test
    public void add_double () {
        assertThat(scalar.add(2.0), is(new AngularVelocity(3.0)));
    }

    @Test
    public void mul_by_scalar () {
        assertThat(scalar.mul(new AngularVelocity(2.0)), is(new AngularVelocity(2.0)));
    }

    @Test
    public void mul_by_double () {
        assertThat(scalar.mul(2.0), is(new AngularVelocity(2.0)));
    }

}
