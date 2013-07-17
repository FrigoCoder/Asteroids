
package frigo.asteroids;

import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;

public class JOGLWindowListener implements WindowListener {

    private JOGLRunner joglRunner;

    public JOGLWindowListener (JOGLRunner joglRunner) {
        this.joglRunner = joglRunner;
    }

    @Override
    public void windowResized (WindowEvent e) {
    }

    @Override
    public void windowMoved (WindowEvent e) {
    }

    @Override
    public void windowDestroyNotify (WindowEvent e) {
        joglRunner.stop();
    }

    @Override
    public void windowDestroyed (WindowEvent e) {
        joglRunner.stop();
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
