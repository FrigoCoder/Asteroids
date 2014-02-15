
package frigo.asteroids.core;

import com.carrotsearch.hppc.cursors.IntCursor;

public final class Entity extends Identity {

    private static int hash (Object object) {
        return System.identityHashCode(object);
    }

    public final int id;
    public final transient ComponentDatabase db;

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

    public boolean matches (Aspect aspect) {
        for( IntCursor type : aspect.all ){
            if( !db.has(id, type.value) ){
                return false;
            }
        }
        for( IntCursor type : aspect.none ){
            if( db.has(id, type.value) ){
                return false;
            }
        }
        return true;
    }

}
