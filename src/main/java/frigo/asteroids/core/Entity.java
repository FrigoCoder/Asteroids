
package frigo.asteroids.core;

import com.carrotsearch.hppc.cursors.IntCursor;

public final class Entity extends Identity {

    public final int id;
    public final transient ComponentDatabase db;

    public Entity (ComponentDatabase db) {
        id = System.identityHashCode(this);
        this.db = db;
    }

    public boolean has (ComponentId<?> type) {
        return db.has(id, type.id);
    }

    public <T extends Component> T get (ComponentId<T> type) {
        return db.get(id, type.id);
    }

    public void add (ComponentId<?> type, Component component) {
        db.add(id, type.id, component);
    }

    public void remove (ComponentId<?> type) {
        db.remove(id, type.id);
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
