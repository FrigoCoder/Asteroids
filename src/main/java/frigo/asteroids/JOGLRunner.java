
package frigo.asteroids;

import java.util.concurrent.CopyOnWriteArrayList;

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
    private CopyOnWriteArrayList<KeyEvent> keyEvents = new CopyOnWriteArrayList<>();

    public JOGLRunner (World world) {
        System.setProperty("jogamp.gluegen.UseTempJarCache", "false");

        window = GLWindow.create(new GLCapabilities(GLProfile.getDefault()));

        window.addGLEventListener(new JOGLGLEventListener(this, world, keyEvents));
        window.addKeyListener(new JOGLKeyListener(this, keyEvents));
        window.addWindowListener(new JOGLWindowListener(this));

        // window.setSize(640, 480);
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
