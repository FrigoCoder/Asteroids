
package frigo.asteroids.jogl;

import java.util.concurrent.LinkedBlockingQueue;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;

import frigo.asteroids.core.World;

public class JOGLRunner {

    private static final int FPS = 100;

    private GLWindow window;
    private FPSAnimator animator;
    private LinkedBlockingQueue<KeyEvent> keyEvents = new LinkedBlockingQueue<>();

    public JOGLRunner (World world) {
        System.setProperty("jogamp.gluegen.UseTempJarCache", "false");

        window = GLWindow.create(new GLCapabilities(GLProfile.getDefault()));

        window.addGLEventListener(new JOGLGLEventListener(world, keyEvents));
        window.addKeyListener(new JOGLKeyListener(keyEvents));
        window.addWindowListener(new JOGLWindowListener(this));

        window.setSize(1024, 768);
        window.setTitle("Asteroids");
        window.setVisible(true);

        animator = new FPSAnimator(window, FPS, true);
    }

    public void start () {
        animator.start();
    }

    public void stop () {
        animator.stop();
    }

}
