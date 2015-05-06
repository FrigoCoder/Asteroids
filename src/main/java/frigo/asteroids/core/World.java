
package frigo.asteroids.core;

import java.util.List;

import frigo.asteroids.core.component.ComponentId;
import frigo.asteroids.core.component.ComponentStore;
import frigo.asteroids.core.component.ObjectStore;

public class World {

    private EntityManager entities = new EntityManager();
    private MessageManager messages = new MessageManager();
    private SystemManager systems = new SystemManager();

    public Entity createEntity () {
        return entities.create();
    }

    public void removeEntity (Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        return entities.getEntitiesFor(aspect);
    }

    public <T extends Component> void register (ComponentId<T> type) {
        register(type, new ObjectStore<>());
    }

    public <T extends Component> void register (ComponentId<T> type, ComponentStore<T> store) {
        entities.register(type.id, store);
    }

    public void addMessage (Message message) {
        messages.add(message);
    }

    public <T extends Message> List<T> getMessages (Class<T> clazz) {
        return messages.get(clazz);
    }

    public void addLogic (Logic logic) {
        systems.addLogic(logic);
    }

    public void init () {
        systems.init(this);
    }

    public void update (double elapsedSeconds) {
        systems.update(elapsedSeconds);
        messages.clear();
    }

}
