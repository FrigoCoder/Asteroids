
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AngularDisplacementTest {

    private AngularDisplacement scalar = new AngularDisplacement(1.0);

    @Test
    public void add_scalar () {
        assertThat(scalar.add(new AngularDisplacement(2.0)), is(new AngularDisplacement(3.0)));
    }

    @Test
    public void add_double () {
        assertThat(scalar.add(2.0), is(new AngularDisplacement(3.0)));
    }

    @Test
    public void mul_by_scalar () {
        assertThat(scalar.mul(new AngularDisplacement(2.0)), is(new AngularDisplacement(2.0)));
    }

    @Test
    public void mul_by_double () {
        assertThat(scalar.mul(2.0), is(new AngularDisplacement(2.0)));
    }

}
