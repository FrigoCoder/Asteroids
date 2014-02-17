
package frigo.asteroids.message;

import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class CollisionTest {

    private World world = new World();

    @Test
    public void constructor_stores_entities () {
        Entity entity1 = world.createEntity();
        Entity entity2 = world.createEntity();
        Collision collision = new Collision(entity1, entity2);
        assertThat(collision.entity1, sameInstance(entity1));
        assertThat(collision.entity2, sameInstance(entity2));
    }

}
