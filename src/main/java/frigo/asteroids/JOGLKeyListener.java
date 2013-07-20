
package frigo.asteroids;

import java.util.concurrent.LinkedBlockingQueue;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class JOGLKeyListener implements KeyListener {

    private LinkedBlockingQueue<KeyEvent> keyEvents;

    public JOGLKeyListener (LinkedBlockingQueue<KeyEvent> keyEvents) {
        this.keyEvents = keyEvents;
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

}
