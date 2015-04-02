
package frigo.asteroids.jogl;

import com.google.common.annotations.VisibleForTesting;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import frigo.asteroids.core.World;

public class JOGLWorldUpdater implements GLEventListener {

    private World world;
    private long lastMillis;

    public JOGLWorldUpdater (World world) {
        this.world = world;
    }

    @Override
    public void init (GLAutoDrawable drawable) {
        world.init();
        lastMillis = getNanoTime();
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        long currentMillis = getNanoTime();
        double elapsedSeconds = (currentMillis - lastMillis) / 1_000_000_000.0;
        world.update(elapsedSeconds);
        lastMillis = currentMillis;
    }

    @VisibleForTesting
    long getNanoTime () {
        return System.nanoTime();
    }

    @Override
    public void dispose (GLAutoDrawable drawable) {
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

}
