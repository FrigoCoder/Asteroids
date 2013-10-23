
package frigo.asteroids.core;

public final class Entity extends Identity {

    private transient EntityComponentMap map;
    public final int id;

    Entity (EntityComponentMap map, int id) {
        this.map = map;
        this.id = id;
    }

    public <T extends Component> boolean has (Class<T> type) {
        return map.has(this, type);
    }

    public <T extends Component> T get (Class<T> type) {
        return map.get(this, type);
    }

    public <T extends Component> void set (T component) {
        map.set(this, component);
    }

}
