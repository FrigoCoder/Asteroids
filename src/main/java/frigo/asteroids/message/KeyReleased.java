
package frigo.asteroids.message;

import frigo.asteroids.core.Message;

public class KeyReleased extends Message {

    public final short key;

    public KeyReleased (short key) {
        this.key = key;
    }

}
