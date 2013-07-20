
package frigo.asteroids.message;

import frigo.asteroids.core.Message;

public class KeyHeld extends Message {

    public final short key;

    public KeyHeld (short key) {
        this.key = key;
    }

}
