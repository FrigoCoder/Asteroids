
package frigo.asteroids.core.component;

public class UnregisteredComponentException extends RuntimeException {

    public UnregisteredComponentException () {
        super();
    }

    public UnregisteredComponentException (Class<?> type) {
        super(type.getSimpleName());
    }

}
