
package frigo.asteroids.core;

import com.carrotsearch.hppc.IntObjectOpenHashMap;

public class ComponentDatabase {

    private IntObjectOpenHashMap<IntObjectOpenHashMap<Component>> map = new IntObjectOpenHashMap<>();

    public boolean has (int entity, int type) {
        try{
            return getColumn(type).containsKey(entity);
        }catch( NullPointerException e ){
            return false;
        }
    }

    public <T extends Component> T get (int entity, int type) {
        try{
            return (T) getColumn(type).get(entity);
        }catch( NullPointerException e ){
            return null;
        }
    }

    public void add (int entity, int type, Component component) {
        try{
            getColumn(type).put(entity, component);
        }catch( NullPointerException e ){
            createColumn(type);
            getColumn(type).put(entity, component);
        }
    }

    public void remove (int entity, int type) {
        try{
            getColumn(type).remove(entity);
        }catch( NullPointerException e ){
        }
    }

    private IntObjectOpenHashMap<Component> getColumn (int type) {
        return map.get(type);
    }

    private void createColumn (int hash) {
        map.put(hash, new IntObjectOpenHashMap<Component>());
    }

}
