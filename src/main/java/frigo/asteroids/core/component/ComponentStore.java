
package frigo.asteroids.core.component;

public abstract class ComponentStore<T> {

    public abstract boolean has (int entity);

    public abstract void remove (int entity);

    public T get (int entity) {
        throw new UnsupportedOperationException();
    }

    public void set (int entity, T component) {
        throw new UnsupportedOperationException();
    }

    public boolean getFlag (int entity) {
        throw new UnsupportedOperationException();
    }

    public void setFlag (int entity) {
        throw new UnsupportedOperationException();
    }

    public double getDouble (int entity) {
        throw new UnsupportedOperationException();
    }

    public void setDouble (int entity, double component) {
        throw new UnsupportedOperationException();
    }

}
