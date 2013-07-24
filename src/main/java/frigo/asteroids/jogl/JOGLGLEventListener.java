
package frigo.asteroids.jogl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.newt.event.KeyEvent;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Renderable;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;
import frigo.asteroids.message.KeyHeld;
import frigo.asteroids.message.KeyPressed;
import frigo.asteroids.message.KeyReleased;

public class JOGLGLEventListener implements GLEventListener {

    private World world;
    private LinkedBlockingQueue<KeyEvent> keyEvents;
    private long lastMillis;
    private Map<Short, KeyEvent> held = new HashMap<>();

    public JOGLGLEventListener (World world, LinkedBlockingQueue<KeyEvent> keyEvents) {
        this.world = world;
        this.keyEvents = keyEvents;
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
        injectInputSnapshot();
        world.update(elapsedSeconds);
        render(drawable);
        lastMillis = currentMillis;
    }

    private void render (GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();

        Aspect aspect = new Aspect().all(Position.class, Renderable.class);
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

    private void injectInputSnapshot () {
        LinkedList<KeyEvent> events = new LinkedList<>();
        keyEvents.drainTo(events);

        LinkedList<KeyEvent> pressed = new LinkedList<>();
        LinkedList<KeyEvent> released = new LinkedList<>();
        for( KeyEvent event : events ){
            switch( event.getEventType() ){
                case KeyEvent.EVENT_KEY_PRESSED:
                    pressed.add(event);
                    break;
                case KeyEvent.EVENT_KEY_RELEASED:
                    released.add(event);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        for( KeyEvent event : released ){
            short key = event.getKeyCode();
            if( held.containsKey(key) ){
                held.remove(key);
                world.addMessage(new KeyReleased(key));
            }
        }

        for( short key : held.keySet() ){
            world.addMessage(new KeyHeld(key));
        }

        for( KeyEvent event : pressed ){
            short key = event.getKeyCode();
            if( !held.containsKey(key) ){
                held.put(key, event);
                world.addMessage(new KeyPressed(key));
            }
        }
    }

    @Override
    public void dispose (GLAutoDrawable drawable) {
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

}
