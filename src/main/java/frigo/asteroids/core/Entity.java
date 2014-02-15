
package frigo.asteroids.core;

public final class Entity extends Identity {

    public final int id;
    private transient ComponentDatabase db;

    public Entity (ComponentDatabase db) {
        id = hash(this);
        this.db = db;
    }

    public <T extends Component> boolean has (Class<T> type) {
        return db.has(id, hash(type));
    }

    public <T extends Component> T get (Class<T> type) {
        return db.get(id, hash(type));
    }

    public <T extends Component> void add (T component) {
        db.add(id, hash(component.getClass()), component);
    }

    public <T extends Component> void remove (Class<T> type) {
        db.remove(id, hash(type));
    }

    private static int hash (Object object) {
        return System.identityHashCode(object);
    }

}
