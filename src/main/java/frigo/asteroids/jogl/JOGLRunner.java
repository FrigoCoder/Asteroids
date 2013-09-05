
package frigo.asteroids.jogl;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import net.tribe7.opengl.util.GLBootstrap;

import com.google.common.base.Throwables;
import com.jogamp.common.jvm.JNILibLoaderBase;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;

import frigo.asteroids.core.World;

public class JOGLRunner {

    static{
        try{
            JNILibLoaderBase.setLoadingAction(new GLBootstrap());
        }catch( Exception e ){
            throw Throwables.propagate(e);
        }
    }

    private FPSAnimator animator;

    public JOGLRunner (World world, int xsize, int ysize, int fps) {

        GLWindow window = GLWindow.create(new GLCapabilities(GLProfile.getDefault()));

        JOGLKeyListener keyListener = new JOGLKeyListener(world);
        window.addKeyListener(keyListener);

        window.addGLEventListener(keyListener);
        window.addGLEventListener(new JOGLWorldUpdater(world));
        window.addGLEventListener(new JOGLRenderer(world));

        window.addWindowListener(new JOGLWindowListener(this));

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
