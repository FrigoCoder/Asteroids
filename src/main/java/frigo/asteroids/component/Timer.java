
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Timer extends Component {

    public static final int ID = System.identityHashCode(Timer.class);

    public double seconds;
    public TimerAction action;

    public Timer (double seconds, TimerAction action) {
        this.seconds = seconds;
        this.action = action;
    }

    public void countDown (double elapsed) {
        seconds -= elapsed;
    }

    public boolean elapsed () {
        return seconds <= 0;
    }

}
