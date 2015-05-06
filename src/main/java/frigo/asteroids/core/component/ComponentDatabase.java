
package frigo.asteroids.core.component;

import com.carrotsearch.hppc.IntObjectOpenHashMap;

public class ComponentDatabase {

    private IntObjectOpenHashMap<ComponentStore<?>> map = new IntObjectOpenHashMap<>();

    public <T> void register (ComponentId<T> type, ComponentStore<T> store) {
        map.put(type.id, store);
    }

    public <T> void register (int type, ComponentStore<T> store) {
        map.put(type, store);
    }

    public boolean has (int entity, ComponentId<?> type) {
        return getStore(type).has(entity);
    }

    public boolean has (int entity, int type) {
        return getStore(type).has(entity);
    }

    public void remove (int entity, ComponentId<?> type) {
        getStore(type).remove(entity);
    }

    public void remove (int entity, int type) {
        getStore(type).remove(entity);
    }

    public <T> T get (int entity, ComponentId<?> type) {
        ComponentStore<T> store = getStore(type);
        return store.get(entity);
    }

    public <T> T get (int entity, int type) {
        ComponentStore<T> store = getStore(type);
        return store.get(entity);
    }

    public <T> void set (int entity, ComponentId<?> type, T component) {
        getStore(type).set(entity, component);
    }

    public <T> void set (int entity, int type, T component) {
        getStore(type).set(entity, component);
    }

    public boolean getFlag (int entity, ComponentId<?> type) {
        return getStore(type).getFlag(entity);
    }

    public boolean getFlag (int entity, int type) {
        return getStore(type).getFlag(entity);
    }

    public void setFlag (int entity, ComponentId<?> type) {
        getStore(type).setFlag(entity);
    }

    public void setFlag (int entity, int type) {
        getStore(type).setFlag(entity);
    }

    private <T> ComponentStore<T> getStore (ComponentId<?> type) {
        ComponentStore<T> store = (ComponentStore<T>) map.get(type.id);
        if( store == null ){
            throw new UnregisteredComponentException(type.clazz);
        }
        return store;
    }

    private <T> ComponentStore<T> getStore (int type) {
        ComponentStore<T> store = (ComponentStore<T>) map.get(type);
        if( store == null ){
            throw new UnregisteredComponentException();
        }
        return store;
    }

}
