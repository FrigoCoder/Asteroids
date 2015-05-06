
package frigo.asteroids.core.component;

public abstract class ComponentStore<T> {

    public abstract boolean has (int entity);

    public abstract void remove (int entity);

    @SuppressWarnings("unused")
    public T get (int entity) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unused")
    public void set (int entity, T component) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unused")
    public boolean getFlag (int entity) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unused")
    public void setFlag (int entity) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unused")
    public double getDouble (int entity) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unused")
    public void setDouble (int entity, double component) {
        throw new UnsupportedOperationException();
    }

}
