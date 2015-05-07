
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimerTest {

    @Test
    public void timer_is_not_elapsed () {
        Timer timer = new Timer(1.1, null);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(false));
    }

    @Test
    public void timer_is_just_elapsed () {
        Timer timer = new Timer(1.0, null);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(true));
    }

    @Test
    public void timer_is_elapsed () {
        Timer timer = new Timer(0.9, null);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(true));
    }

}
