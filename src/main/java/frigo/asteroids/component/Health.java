
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Health extends Component {

    public static final int ID = System.identityHashCode(Health.class);

    public double health;

    public Health (double health) {
        this.health = health;
    }

    public void damage (Damage damage) {
        health -= damage.damage;
    }

    public boolean isDead () {
        return health <= 0;
    }

}
