
package frigo.asteroids.jogl;

import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;

public class JOGLWindowListener implements WindowListener {

    private JOGLRunner runner;

    public JOGLWindowListener (JOGLRunner runner) {
        this.runner = runner;
    }

    @Override
    public void windowResized (WindowEvent e) {
    }

    @Override
    public void windowMoved (WindowEvent e) {
    }

    @Override
    public void windowDestroyNotify (WindowEvent e) {
        runner.stop();
    }

    @Override
    public void windowDestroyed (WindowEvent e) {
        runner.stop();
    }

    @Override
    public void windowGainedFocus (WindowEvent e) {
    }

    @Override
    public void windowLostFocus (WindowEvent e) {
    }

    @Override
    public void windowRepaint (WindowUpdateEvent e) {
    }

}