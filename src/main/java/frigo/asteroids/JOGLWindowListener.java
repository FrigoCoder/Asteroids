
package frigo.asteroids;

import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;
import com.jogamp.opengl.util.FPSAnimator;

public class JOGLWindowListener implements WindowListener {

    private FPSAnimator animator;

    public JOGLWindowListener (FPSAnimator animator) {
        this.animator = animator;
    }

    @Override
    public void windowResized (WindowEvent e) {
    }

    @Override
    public void windowMoved (WindowEvent e) {
    }

    @Override
    public void windowDestroyNotify (WindowEvent e) {
        animator.stop();
    }

    @Override
    public void windowDestroyed (WindowEvent e) {
        animator.stop();
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
