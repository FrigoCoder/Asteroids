
package frigo.asteroids.util;

public class Rethrow {

    public static RuntimeException unchecked (Throwable e) {
        return dirtyGenericTrick(e);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> T dirtyGenericTrick (Throwable e) {
        return (T) e;
    }

}
