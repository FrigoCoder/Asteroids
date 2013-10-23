
package frigo.asteroids.core;

import java.util.List;

import frigo.asteroids.core.storage.TroveComponentStorageFactory;

public class World {

    private EntityManager entities;
    private MessageManager messages = new MessageManager();
    private SystemManager systems = new SystemManager();

    public World () {
        this(new TroveComponentStorageFactory());
    }

    public World (ComponentStorageFactory factory) {
        entities = new EntityManager(factory);
    }

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

    @Deprecated
    public boolean matches (Entity entity, Aspect aspect) {
        return entities.matches(entity, aspect);
    }

}
