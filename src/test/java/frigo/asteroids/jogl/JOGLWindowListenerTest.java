
package frigo.asteroids.jogl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class JOGLWindowListenerTest {

    private JOGLRunner runner = mock(JOGLRunner.class);
    private JOGLWindowListener listener = new JOGLWindowListener(runner);

    @Test
    public void window_listener_stops_runner_at_window_close_notification () {
        listener.windowDestroyNotify(null);
        verify(runner).stop();
    }

    @Test
    public void window_listener_stops_runner_at_window_closed () {
        listener.windowDestroyed(null);
        verify(runner).stop();
    }

}
