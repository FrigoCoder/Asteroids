
package frigo.asteroids.core;

public abstract class Logic {

    public World world;

    @SuppressWarnings("hiding")
    public void init (World world) {
        this.world = world;
    }

    public abstract void update (double elapsedSeconds);

}
