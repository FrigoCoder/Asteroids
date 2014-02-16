
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.ComponentId;

public class Timer extends Component {

    public static final ComponentId<Timer> ID = new ComponentId<>(Timer.class);

    public ComponentId<? extends Component> id;
    public Component component;
    public double seconds;

    public Timer (ComponentId<? extends Component> id, Component component, double seconds) {
        this.id = id;
        this.component = component;
        this.seconds = seconds;
    }

    public void countDown (double elapsed) {
        seconds -= elapsed;
    }

    public boolean elapsed () {
        return seconds <= 0;
    }

    public Component emitted () {
        return component;
    }

}
