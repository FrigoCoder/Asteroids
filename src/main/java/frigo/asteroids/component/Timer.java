
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Timer extends Component {

    public Component component;
    public double seconds;

    public Timer (Component component, double seconds) {
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
