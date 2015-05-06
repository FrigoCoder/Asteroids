
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Timer extends Component {

    public static final int ID = System.identityHashCode(Timer.class);

    public int type;
    public Component component;
    public double seconds;

    public Timer (int type, Component component, double seconds) {
        this.type = type;
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
