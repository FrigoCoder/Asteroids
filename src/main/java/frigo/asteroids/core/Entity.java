
package frigo.asteroids.core;

import com.carrotsearch.hppc.cursors.IntCursor;

import frigo.asteroids.core.component.ComponentDatabase;

public final class Entity extends Identity {

    public final int id;
    public final transient ComponentDatabase db;

    public Entity (ComponentDatabase db) {
        id = System.identityHashCode(this);
        this.db = db;
    }

    public boolean has (int type) {
        return db.has(id, type);
    }

    public <T> T get (int type) {
        return db.get(id, type);
    }

    public void set (int type, Object component) {
        db.set(id, type, component);
    }

    public void remove (int type) {
        db.remove(id, type);
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
