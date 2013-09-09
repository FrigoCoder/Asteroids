
package frigo.asteroids.jogl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import frigo.asteroids.core.World;

public class JOGLWorldUpdaterTest {

    private World world = mock(World.class);
    private JoglWorldUpdater updater = spy(new JoglWorldUpdater(world));

    @Test
    public void init_calls_world_init () {
        updater.init(null);
        verify(world).init();
    }

    @Test
    public void display_calls_world_update_with_the_elapsed_time () {
        doReturn(0L).doReturn(10_000_000L).when(updater).getNanoTime();
        updater.init(null);
        updater.display(null);
        verify(world).update(0.01);

    }
}
