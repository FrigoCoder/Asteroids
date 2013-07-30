
package frigo.asteroids.util;

public class Rethrow {

    public static RuntimeException unchecked (Throwable e) {
        if( e instanceof RuntimeException ){
            return (RuntimeException) e;
        }
        return new RuntimeException(e);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> T dirtyGenericTrick (Throwable e) {
        return (T) e;
    }

}
