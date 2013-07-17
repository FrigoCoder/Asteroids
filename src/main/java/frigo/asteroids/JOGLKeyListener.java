
package frigo.asteroids;

import java.util.concurrent.CopyOnWriteArrayList;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class JOGLKeyListener implements KeyListener {

    private JOGLRunner joglRunner;
    private CopyOnWriteArrayList<KeyEvent> keyEvents;

    public JOGLKeyListener (JOGLRunner joglRunner, CopyOnWriteArrayList<KeyEvent> keyEvents) {
        this.joglRunner = joglRunner;
        this.keyEvents = keyEvents;
    }

    @Override
    public void keyPressed (KeyEvent e) {
        keyEvents.add(e);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        keyEvents.add(e);
    }

}
