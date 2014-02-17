
package frigo.asteroids.message;

import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Message;

public class Collision extends Message {

    public final Entity entity1;
    public final Entity entity2;

    public Collision (Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

}
