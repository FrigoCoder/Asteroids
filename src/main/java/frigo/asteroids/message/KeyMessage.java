
package frigo.asteroids.message;

import frigo.asteroids.core.Message;

public class KeyMessage extends Message {

    public final short key;

    public KeyMessage (short key) {
        this.key = key;
    }

}
