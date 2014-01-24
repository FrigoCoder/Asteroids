
package frigo.asteroids.logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;
import frigo.asteroids.message.KeyHeld;
import frigo.asteroids.message.KeyMessage;
import frigo.asteroids.message.KeyPressed;

public class InputSystem extends Logic {

    private Map<Short, InputAction> actions = new HashMap<>();

    public void register (Short keyEvent, InputAction action) {
        actions.put(keyEvent, action);
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        List<KeyMessage> messages = new LinkedList<>();
        messages.addAll(world.getMessages(KeyPressed.class));
        messages.addAll(world.getMessages(KeyHeld.class));

        for( KeyMessage message : messages ){
            if( actions.containsKey(message.key) ){
                InputAction action = actions.get(message.key);
                action.run(world, elapsedSeconds);
            }
        }
    }

}
