
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ScalarTest {

    private Scalar scalar = new Scalar(1.0);

    @Test
    public void constructor_sets_value () {
        assertThat(scalar.value, is(1.0));
    }

    @Test
    public void add_scalar () {
        assertThat(scalar.add(new Scalar(2.0)), is(new Scalar(3.0)));
    }

    @Test
    public void add_double () {
        assertThat(scalar.add(2.0), is(new Scalar(3.0)));
    }

    @Test
    public void mul_by_scalar () {
        assertThat(scalar.mul(new Scalar(2.0)), is(new Scalar(2.0)));
    }

    @Test
    public void mul_by_double () {
        assertThat(scalar.mul(2.0), is(new Scalar(2.0)));
    }

}
