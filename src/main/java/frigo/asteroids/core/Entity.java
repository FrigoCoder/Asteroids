
package frigo.asteroids.core;

import com.carrotsearch.hppc.cursors.IntCursor;

public final class Entity extends Identity {

    public final int id;
    public final transient ComponentDatabase db;

    public Entity (ComponentDatabase db) {
        id = System.identityHashCode(this);
        this.db = db;
    }

    public <T extends Component> boolean has (ComponentId<T> type) {
        return db.has(id, type.id);
    }

    public <T extends Component> T get (ComponentId<T> type) {
        return db.get(id, type.id);
    }

    public <T extends Component> void add (ComponentId<? extends Component> type, T component) {
        db.add(id, type.id, component);
    }

    public <T extends Component> void remove (ComponentId<T> type) {
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
