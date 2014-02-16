
package frigo.asteroids.core;

public class ComponentId<T extends Component> {

    public final int id;

    public ComponentId (Class<T> type) {
        id = System.identityHashCode(type);
    }

}
