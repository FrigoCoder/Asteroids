
package frigo.asteroids.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class SystemManagerTest {

    private World world = new World();
    private Logic logic = mock(Logic.class);

    @Test
    public void logic_is_initialized_at_init_call () {
        world.addLogic(logic);
        world.init();
        verify(logic).init(world);
    }

    @Test
    public void logic_is_updated_at_update_call () {
        world.addLogic(logic);
        world.update(1);
        verify(logic).update(1);
    }

    @Test
    public void logic_is_updated_by_the_elapsed_seconds () {
        world.addLogic(logic);
        world.update(0.01);
        verify(logic).update(0.01);
    }

}
