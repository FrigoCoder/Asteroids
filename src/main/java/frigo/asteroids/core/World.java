
package frigo.asteroids.core;

import java.util.List;

public class World {

    private EntityManager entities = new EntityManager();
    private MessageManager messages = new MessageManager();
    private SystemManager systems = new SystemManager();

    public Entity createEntity (Component... components) {
        return entities.create(components);
    }

    public void removeEntity (Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities () {
        return entities.getEntitiesFor(Aspect.allOf());
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        return entities.getEntitiesFor(aspect);
    }

    public void addMessage (Object message) {
        messages.add(message);
    }

    public <T> List<T> getMessages (Class<? extends T> clazz) {
        return messages.get(clazz);
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
