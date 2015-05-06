
package frigo.asteroids.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessageManager {

    private Map<Class<? extends Message>, List<Message>> messages = new HashMap<>();

    public void add (Message message) {
        Class<? extends Message> clazz = message.getClass();
        try{
            messages.get(clazz).add(message);
        }catch( NullPointerException e ){
            messages.put(clazz, new LinkedList<Message>());
            messages.get(clazz).add(message);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Message> List<T> get (Class<T> clazz) {
        List<T> list = (List<T>) messages.get(clazz);
        return list == null ? Collections.EMPTY_LIST : list;
    }

    public void clear () {
        messages.clear();
    }

}
