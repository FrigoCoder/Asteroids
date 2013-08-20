
package frigo.asteroids.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessageManager {

    private Map<Class<?>, List<Object>> messages = new HashMap<>();

    public void add (Object message) {
        Class<?> clazz = message.getClass();
        if( !messages.containsKey(clazz) ){
            messages.put(clazz, new LinkedList<>());
        }
        messages.get(clazz).add(message);
    }

    public <T> List<T> get (Class<? extends T> clazz) {
        if( !messages.containsKey(clazz) ){
            return Collections.EMPTY_LIST;
        }
        return (List<T>) messages.get(clazz);
    }

    public void clear () {
        messages.clear();
    }
}
