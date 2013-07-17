
package frigo.asteroids;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.util.FPSAnimator;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Renderable;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class JOGLGLEventListener implements GLEventListener {

    private World world;
    private long lastMillis;
    private CopyOnWriteArrayList<KeyEvent> keyEvents;
    private FPSAnimator animator;

    public JOGLGLEventListener (World world, CopyOnWriteArrayList<KeyEvent> keyEvents, FPSAnimator animator) {
        this.world = world;
        this.keyEvents = keyEvents;
        this.animator = animator;
    }

    @Override
    public void init (GLAutoDrawable drawable) {
        world.init();
        lastMillis = System.nanoTime();

        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1, 1, 1, -1, -1, 1);

        gl.glEnable(GL2ES1.GL_POINT_SMOOTH);
        gl.glHint(GL2ES1.GL_POINT_SMOOTH, GL.GL_NICEST);
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        long currentMillis = System.nanoTime();
        double elapsedSeconds = (currentMillis - lastMillis) / 1_000_000_000.0;
        handleInput();
        world.update(elapsedSeconds);
        render(drawable);
        lastMillis = currentMillis;
    }

    private void render (GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();

        Aspect aspect = Aspect.all(Position.class, Renderable.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Position position = entity.get(Position.class);
            Renderable renderable = entity.get(Renderable.class);
            gl.glPointSize((float) renderable.size);
            gl.glColor3d(renderable.r, renderable.g, renderable.b);
            gl.glBegin(GL.GL_POINTS);
            gl.glVertex2d(position.x, position.y);
            gl.glEnd();
        }
    }

    private void handleInput () {
        Aspect aspect = Aspect.all(PlayerControllable.class, Acceleration.class);
        Entity entity = world.getEntitiesFor(aspect).iterator().next();
        double speed = 10.0;
        for( KeyEvent event : keyEvents ){
            switch( event.getEventType() ){
                case KeyEvent.EVENT_KEY_PRESSED:
                    switch( event.getKeyCode() ){
                        case KeyEvent.VK_UP:
                            entity.set(entity.get(Acceleration.class).add(0.0, -speed));
                            break;
                        case KeyEvent.VK_DOWN:
                            entity.set(entity.get(Acceleration.class).add(0.0, speed));
                            break;
                        case KeyEvent.VK_LEFT:
                            entity.set(entity.get(Acceleration.class).add(-speed, 0.0));
                            break;
                        case KeyEvent.VK_RIGHT:
                            entity.set(entity.get(Acceleration.class).add(speed, 0.0));
                            break;
                        case KeyEvent.VK_F4:
                            animator.stop();
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
            keyEvents.remove(event);
        }
    }

    @Override
    public void dispose (GLAutoDrawable drawable) {
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

}
