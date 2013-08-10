
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityManager {

    private long counter;
    private Set<Entity> entities = new HashSet<>();
    private Map<Class<? extends Component>, ComponentMapper<?>> components = new HashMap<>();

    public Entity createEntity (Component... componentsToSet) {
        Entity entity = new Entity(counter++);
        for( Component component : componentsToSet ){
            set(entity, component);
        }
        return entity;
    }

    public void addEntity (Entity entity) {
        entities.add(entity);
    }

    public Set<Entity> getEntities () {
        return entities;
    }

    public boolean has (Entity entity, Class<? extends Component> type) {
        return getComponentMapper(type).has(entity);
    }

    public <T extends Component> T get (Entity entity, Class<T> type) {
        return getComponentMapper(type).get(entity);
    }

    public <T extends Component> void set (Entity entity, T component) {
        Class<T> clazz = (Class<T>) component.getClass();
        getComponentMapper(clazz).set(entity, component);
    }

    private <T extends Component> ComponentMapper<T> getComponentMapper (Class<T> type) {
        if( !components.containsKey(type) ){
            components.put(type, new ComponentMapper<T>());
        }
        return (ComponentMapper<T>) components.get(type);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        List<Entity> result = new LinkedList<>(entities);
        filterAll(result, aspect);
        filterNone(result, aspect);
        return result;
    }

    private void filterAll (List<Entity> result, Aspect aspect) {
        for( Class<? extends Component> clazz : aspect.all ){
            ComponentMapper<? extends Component> mapper = getComponentMapper(clazz);
            Iterator<Entity> iterator = result.iterator();
            while( iterator.hasNext() ){
                if( !mapper.has(iterator.next()) ){
                    iterator.remove();
                }
            }
        }
    }

    private void filterNone (List<Entity> result, Aspect aspect) {
        for( Class<? extends Component> clazz : aspect.none ){
            ComponentMapper<? extends Component> mapper = getComponentMapper(clazz);
            Iterator<Entity> iterator = result.iterator();
            while( iterator.hasNext() ){
                if( mapper.has(iterator.next()) ){
                    iterator.remove();
                }
            }
        }
    }

    @Deprecated
    public boolean matches (Entity entity, Aspect aspect) {
        for( Class<? extends Component> component : aspect.all ){
            if( !has(entity, component) ){
                return false;
            }
        }
        for( Class<? extends Component> component : aspect.none ){
            if( has(entity, component) ){
                return false;
            }
        }
        return true;
    }

}
