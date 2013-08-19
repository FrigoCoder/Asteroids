
package frigo.asteroids.core;

import java.util.LinkedList;
import java.util.List;

public class EntityManager {

    private int counter;
    private List<Entity> entities = new LinkedList<>();
    private ComponentMapper mapper;

    public EntityManager (ComponentStorageFactory factory) {
        mapper = new ComponentMapper(factory);
    }

    public Entity createEntity (Component... componentsToSet) {
        Entity entity = new Entity(counter++);
        for( Component component : componentsToSet ){
            set(entity, component);
        }
        entities.add(entity);
        return entity;
    }

    public boolean has (Entity entity, Class<? extends Component> type) {
        return mapper.has(entity, type);
    }

    public <T extends Component> T get (Entity entity, Class<T> type) {
        return mapper.get(entity, type);
    }

    public <T extends Component> void set (Entity entity, T component) {
        mapper.set(entity, component);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        EntityMatcher matcher = new EntityMatcher(mapper, aspect);
        List<Entity> result = new LinkedList<>();
        for( Entity entity : entities ){
            if( matcher.matches(entity) ){
                result.add(entity);
            }
        }
        return result;
    }

    @Deprecated
    public boolean matches (Entity entity, Aspect aspect) {
        return new EntityMatcher(mapper, aspect).matches(entity);
    }

}
