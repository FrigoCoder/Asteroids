
package frigo.asteroids.jogl;

import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;

public class JoglWindowListener implements WindowListener {

    private JoglRunner runner;

    public JoglWindowListener (JoglRunner runner) {
        this.runner = runner;
    }

    @Override
    public void windowDestroyed (WindowEvent e) {
        runner.stop();
    }

    @Override
    public void windowDestroyNotify (WindowEvent e) {
        runner.stop();
    }

    @Override
    public void windowGainedFocus (WindowEvent e) {
    }

    @Override
    public void windowLostFocus (WindowEvent e) {
    }

    @Override
    public void windowMoved (WindowEvent e) {
    }

    @Override
    public void windowRepaint (WindowUpdateEvent e) {
    }

    @Override
    public void windowResized (WindowEvent e) {
    }

}
