
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SizeTest {

    @Test
    public void size_is_stored () {
        Size size = new Size(1.0);
        assertThat(size.size, is(1.0));
    }

}
