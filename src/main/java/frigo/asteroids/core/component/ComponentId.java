
package frigo.asteroids.core.component;

public class ComponentId<T> {

    public final int id;
    public Class<T> clazz;

    public ComponentId (Class<T> clazz) {
        id = System.identityHashCode(clazz);
        this.clazz = clazz;
    }

}
