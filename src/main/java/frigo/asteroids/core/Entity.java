
package frigo.asteroids.core;

public final class Entity extends Value {

    private transient EntityManager manager;
    public final int id;

    Entity (EntityManager manager, int id) {
        this.manager = manager;
        this.id = id;
    }

    public <T extends Component> boolean has (Class<T> type) {
        return manager.innerHas(this, type);
    }

    public <T extends Component> T get (Class<T> type) {
        return manager.innerGet(this, type);
    }

    public <T extends Component> void set (T component) {
        manager.innerSet(this, component);
    }

}
