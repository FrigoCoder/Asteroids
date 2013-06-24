
package frigo.asteroids.util;

import static frigo.asteroids.util.Rethrow.unchecked;

import java.util.concurrent.CountDownLatch;

public class BooleanLatch {

    private CountDownLatch latch = new CountDownLatch(1);

    public void await () {
        try{
            latch.await();
        }catch( InterruptedException e ){
            throw unchecked(e);
        }
    }

    public void release () {
        latch.countDown();
    }

}
