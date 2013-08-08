
package frigo.asteroids.core;

import java.util.List;
import java.util.Set;

public class World {

    private EntityManager entities = new EntityManager();
    private ComponentManager componentManager = new ComponentManager();
    private MessageManager messages = new MessageManager();
    private SystemManager systems = new SystemManager();

    public Entity createEntity (Component... components) {
        Entity entity = entities.create();
        for( Component component : components ){
            set(entity, component);
        }
        return entity;
    }

    public void addEntity (Entity entity) {
        entities.addEntity(entity);
    }

    public Set<Entity> getEntities () {
        return entities.getEntitiesFor(new Aspect(this));
    }

    public Set<Entity> getEntitiesFor (Aspect aspect) {
        return entities.getEntitiesFor(aspect);
    }

    public boolean has (Entity entity, Class<? extends Component> type) {
        return componentManager.has(entity, type);
    }

    public <T extends Component> T get (Entity entity, Class<T> type) {
        return componentManager.get(entity, type);
    }

    public <T extends Component> void set (Entity entity, T component) {
        componentManager.set(entity, component);
    }

    public void addMessage (Object message) {
        messages.addMessage(message);
    }

    public <T> List<T> getMessages (Class<? extends T> clazz) {
        return messages.getMessages(clazz);
    }

    public void addLogic (Logic logic) {
        systems.addLogic(logic);
    }

    public void init () {
        systems.init(this);
    }

    public void update (double elapsedSeconds) {
        systems.update(this, elapsedSeconds);
        messages.clear();
    }

}
