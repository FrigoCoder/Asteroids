
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.core.Component;

public class TimerTest {

    @Test
    public void timer_is_not_elapsed () {
        Timer timer = new Timer(new SelfDestruct(), 1.1);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(false));
    }

    @Test
    public void timer_is_just_elapsed () {
        Timer timer = new Timer(new SelfDestruct(), 1.0);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(true));
    }

    @Test
    public void timer_is_elapsed () {
        Timer timer = new Timer(new SelfDestruct(), 0.9);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(true));
    }

    @Test
    public void returns_component () {
        Component component = new Attractable();
        Timer timer = new Timer(component, 1.0);
        assertThat(timer.emitted(), sameInstance(component));
    }

}
