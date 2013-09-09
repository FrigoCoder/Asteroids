
package frigo.asteroids.jogl;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;

import frigo.asteroids.core.World;

public class JoglRunner {

    private FPSAnimator animator;

    public JoglRunner (World world, int xsize, int ysize, int fps) {

        GLWindow window = GLWindow.create(new GLCapabilities(GLProfile.getDefault()));

        JoglKeyListener keyListener = new JoglKeyListener(world);
        window.addKeyListener(keyListener);

        window.addGLEventListener(keyListener);
        window.addGLEventListener(new JoglWorldUpdater(world));
        window.addGLEventListener(new JoglRenderer(world));

        window.addWindowListener(new JoglWindowListener(this));

        window.setSize(xsize, ysize);
        window.setTitle("Asteroids");
        window.setVisible(true);

        animator = new FPSAnimator(window, fps, true);
    }

    public void start () {
        animator.start();
    }

    public void stop () {
        animator.stop();
    }

}
