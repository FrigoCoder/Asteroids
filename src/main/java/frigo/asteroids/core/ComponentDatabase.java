
package frigo.asteroids.core;

import com.carrotsearch.hppc.IntObjectOpenHashMap;

public class ComponentDatabase {

    private IntObjectOpenHashMap<IntObjectOpenHashMap<Component>> map = new IntObjectOpenHashMap<>();

    public <T extends Component> boolean has (int entity, int type) {
        try{
            return column(type).containsKey(entity);
        }catch( NullPointerException e ){
            return false;
        }
    }

    public <T extends Component> T get (int entity, int type) {
        try{
            return (T) column(type).get(entity);
        }catch( NullPointerException e ){
            return null;
        }
    }

    public <T extends Component> void add (int entity, int type, T component) {
        try{
            column(type).put(entity, component);
        }catch( NullPointerException e ){
            createColumn(type);
            column(type).put(entity, component);
        }
    }

    public <T extends Component> void remove (int entity, int type) {
        try{
            column(type).remove(entity);
        }catch( NullPointerException e ){
        }
    }

    private IntObjectOpenHashMap<Component> column (int type) {
        return map.get(type);
    }

    private void createColumn (int hash) {
        map.put(hash, new IntObjectOpenHashMap<Component>());
    }

}
