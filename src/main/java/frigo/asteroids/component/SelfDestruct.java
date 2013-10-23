
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class SelfDestruct extends Component {

    public final double seconds;

    public SelfDestruct (double seconds) {
        this.seconds = seconds;
    }

    public boolean dies (double elapsed) {
        return seconds <= elapsed;
    }

    public SelfDestruct countDown (double elapsed) {
        return new SelfDestruct(seconds - elapsed);
    }

}
