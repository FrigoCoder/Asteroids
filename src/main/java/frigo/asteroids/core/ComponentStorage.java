
package frigo.asteroids.core;

public interface ComponentStorage<T extends Component> {

    boolean has (Entity entity);

    void set (Entity entity, T component);

    T get (Entity entity);

}
