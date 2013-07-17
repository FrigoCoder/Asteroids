
package frigo.asteroids;

import java.util.concurrent.LinkedBlockingQueue;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class JOGLKeyListener implements KeyListener {

    private JOGLRunner joglRunner;
    private LinkedBlockingQueue<KeyEvent> keyEvents;

    public JOGLKeyListener (JOGLRunner joglRunner, LinkedBlockingQueue<KeyEvent> keyEvents) {
        this.joglRunner = joglRunner;
        this.keyEvents = keyEvents;
    }

    @Override
    public void keyPressed (KeyEvent e) {
        if( !e.isAutoRepeat() ){
            keyEvents.add(e);
            System.out.println(keyEvents.size());
            System.out.println(e);
        }
    }

    @Override
    public void keyReleased (KeyEvent e) {
        if( !e.isAutoRepeat() ){
            keyEvents.add(e);
            System.out.println(keyEvents.size());
            System.out.println(e);
        }
    }

}
