
package frigo.asteroids.logic.input;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import frigo.asteroids.core.Logic;
import frigo.asteroids.message.KeyHeld;
import frigo.asteroids.message.KeyMessage;
import frigo.asteroids.message.KeyPressed;

public class InputSystem extends Logic {

    private Map<Short, List<InputAction>> actions = new HashMap<>();

    public void register (Short keyEvent, InputAction action) {
        try{
            actions.get(keyEvent).add(action);
        }catch( NullPointerException e ){
            actions.put(keyEvent, new LinkedList<InputAction>());
            actions.get(keyEvent).add(action);
        }
    }

    @Override
    public void update (double elapsedSeconds) {
        List<KeyMessage> messages = new LinkedList<>();
        messages.addAll(world.getMessages(KeyPressed.class));
        messages.addAll(world.getMessages(KeyHeld.class));

        for( KeyMessage message : messages ){
            if( actions.containsKey(message.key) ){
                for( InputAction action : actions.get(message.key) ){
                    action.run(elapsedSeconds);
                }
            }
        }
    }

}
