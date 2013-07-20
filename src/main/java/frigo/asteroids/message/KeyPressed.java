
package frigo.asteroids.message;

import frigo.asteroids.core.Message;

public class KeyPressed extends Message {

    public final short key;

    public KeyPressed (short key) {
        this.key = key;
    }

}
