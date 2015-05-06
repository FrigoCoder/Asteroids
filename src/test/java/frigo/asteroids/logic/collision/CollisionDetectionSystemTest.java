
package frigo.asteroids.logic.collision;

import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Damage;
import frigo.asteroids.component.Health;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Size;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class CollisionDetectionSystemTest {

    private World world = new World();
    private Entity entity1 = world.createEntity();
    private Entity entity2 = world.createEntity();
    private CollisionAction action = mock(CollisionAction.class);

    @Before
    public void setUp () {
        world.register(Planar.class);
        world.register(Damage.class);
        world.register(Health.class);
        world.register(Size.class);

        entity1.add(Planar.class, new Planar(vector(0, 0), ZERO, ZERO));
        entity1.add(Damage.class, new Damage(1));
        entity2.add(Planar.class, new Planar(vector(1, 0), ZERO, ZERO));
        entity2.add(Health.class, new Health(1));

        CollisionDetectionSystem collisionSystem = new CollisionDetectionSystem();
        collisionSystem.register(action);

        world.addLogic(collisionSystem);
        world.init();
    }

    @Test
    public void non_intersecting_entities_do_not_emit_collision_message () {
        entity1.add(Size.class, new Size(0.4));
        entity2.add(Size.class, new Size(0.4));

        world.update(1.0);

        verifyZeroInteractions(action);
    }

    @Test
    public void touching_entities_emit_collision_message () {
        entity1.add(Size.class, new Size(0.5));
        entity2.add(Size.class, new Size(0.5));

        world.update(1.0);

        verify(action).collision(entity1, entity2);
    }

    @Test
    public void intersecting_entities_emit_collision_message () {
        entity1.add(Size.class, new Size(0.6));
        entity2.add(Size.class, new Size(0.6));

        world.update(1.0);

        verify(action).collision(entity1, entity2);
    }

}
