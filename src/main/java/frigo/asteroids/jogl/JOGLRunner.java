
package frigo.asteroids.jogl;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;

import frigo.asteroids.core.World;

public class JOGLRunner {

    private FPSAnimator animator;

    public JOGLRunner (World world, int fps) {
        System.setProperty("jogamp.gluegen.UseTempJarCache", "false");

        GLWindow window = GLWindow.create(new GLCapabilities(GLProfile.getDefault()));

        JOGLKeyListener keyListener = new JOGLKeyListener(world);
        window.addKeyListener(keyListener);

        window.addGLEventListener(keyListener);
        window.addGLEventListener(new JOGLWorldUpdater(world));
        window.addGLEventListener(new JOGLRenderer(world));

        window.addWindowListener(new JOGLWindowListener(this));

        window.setSize(1024, 768);
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
