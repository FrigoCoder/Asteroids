
package frigo.asteroids.message;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.jogamp.newt.event.KeyEvent;

public class KeyReleasedTest {

    @Test
    public void constructor_sets_key () {
        short key = KeyEvent.VK_UP;
        KeyReleased event = new KeyReleased(key);
        assertThat(event.key, is(key));
    }
}
