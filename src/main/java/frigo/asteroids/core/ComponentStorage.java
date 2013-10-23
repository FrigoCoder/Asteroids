
package frigo.asteroids.core;

public interface ComponentStorage<T extends Component> {

    void added (Entity entity);

    void removed (Entity entity);

    boolean has (Entity entity);

    T get (Entity entity);

    void set (Entity entity, T component);

}
