
package frigo.asteroids.util;

public class Undeclared {

    public static RuntimeException propagate (Throwable e) {
        Undeclared.<RuntimeException> throwAny(e);
        return null;
    }

    @SuppressWarnings("unused")
    public static <T extends Throwable> void redeclare (Class<T> clazz) throws T {
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> T throwAny (Throwable e) throws T {
        throw (T) e;
    }

}
