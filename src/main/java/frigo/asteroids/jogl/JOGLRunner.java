
package frigo.asteroids.jogl;

import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public class JOGLRunner implements WindowListener {

    private FPSAnimator animator;

    public JOGLRunner (GLEventListener listener, int xsize, int ysize, int fps) {
        GLWindow window = GLWindow.create(new GLCapabilities(GLProfile.getDefault()));
        window.addGLEventListener(listener);
        if( listener instanceof KeyListener ){
            window.addKeyListener((KeyListener) listener);
        }
        window.addWindowListener(this);
        window.setSize(xsize, ysize);
        window.setTitle("Asteroids");
        window.setVisible(true);
        animator = new FPSAnimator(window, fps, true);
    }

    public void init () {
        animator.start();
    }

    @Override
    public void windowDestroyed (WindowEvent e) {
        animator.stop();
    }

    @Override
    public void windowDestroyNotify (WindowEvent e) {
        animator.stop();
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
