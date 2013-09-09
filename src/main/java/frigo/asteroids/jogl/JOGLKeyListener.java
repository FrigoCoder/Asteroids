
package frigo.asteroids.jogl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import frigo.asteroids.core.World;
import frigo.asteroids.message.KeyHeld;
import frigo.asteroids.message.KeyPressed;
import frigo.asteroids.message.KeyReleased;

public class JoglKeyListener implements KeyListener, GLEventListener {

    private World world;
    private LinkedBlockingQueue<KeyEvent> keyEvents = new LinkedBlockingQueue<>();
    private Map<Short, KeyEvent> held = new HashMap<>();

    public JoglKeyListener (World world) {
        this.world = world;
    }

    @Override
    public void keyPressed (KeyEvent event) {
        if( !event.isAutoRepeat() ){
            keyEvents.add(event);
        }
    }

    @Override
    public void keyReleased (KeyEvent event) {
        if( !event.isAutoRepeat() ){
            keyEvents.add(event);
        }
    }

    @Override
    public void init (GLAutoDrawable drawable) {
    }

    @Override
    public void display (GLAutoDrawable drawable) {
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
