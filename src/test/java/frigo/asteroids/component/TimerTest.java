
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class TimerTest {

    @Test
    public void timer_is_not_elapsed () {
        Timer timer = new Timer(SelfDestruct.ID, SelfDestruct.SELF_DESTRUCT, 1.1);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(false));
    }

    @Test
    public void timer_is_just_elapsed () {
        Timer timer = new Timer(SelfDestruct.ID, SelfDestruct.SELF_DESTRUCT, 1.0);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(true));
    }

    @Test
    public void timer_is_elapsed () {
        Timer timer = new Timer(SelfDestruct.ID, SelfDestruct.SELF_DESTRUCT, 0.9);
        timer.countDown(1.0);
        assertThat(timer.elapsed(), is(true));
    }

    @Test
    public void returns_component () {
        ComponentId<?> id = Attractable.ID;
        Component component = Attractable.ATTRACTABLE;
        Timer timer = new Timer(id, component, 1.0);
        assertThat(timer.emitted(), sameInstance(component));
    }

}
