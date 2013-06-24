
package frigo.asteroids.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import frigo.asteroids.util.BooleanLatch;

public class BooleanLatchTest {

    @Rule
    public Timeout timeout = new Timeout(1000);

    private BooleanLatch latch = new BooleanLatch();
    private ExecutorService executor = Executors.newCachedThreadPool();
    private Future<?> future;
    private Future<?> future2;

    @Before
    public void setUp () {
        future = executor.submit(createAwaiter());
        future2 = executor.submit(createAwaiter());
    }

    @After
    public void tearDown () {
        latch.release();
    }

    @Test
    public void thread_does_not_stop_until_latch_is_released () throws InterruptedException {
        // when
        Thread.sleep(100);
        // then
        assertThat(future.isDone(), is(false));
    }

    @Test
    public void thread_stops_when_latch_is_released () throws Exception {
        // when
        latch.release();
        // then
        future.get();
        assertThat(future.isDone(), is(true));
    }

    @Test
    public void two_threads_do_not_stop_until_latch_is_released () throws InterruptedException {
        // when
        Thread.sleep(100);
        // then
        assertThat(future.isDone(), is(false));
        assertThat(future2.isDone(), is(false));
    }

    @Test
    public void two_threads_stop_when_latch_is_released () throws Exception {
        // when
        latch.release();
        // then
        future.get();
        future2.get();
        assertThat(future.isDone(), is(true));
        assertThat(future2.isDone(), is(true));
    }

    private Runnable createAwaiter () {
        return new Runnable() {

            @Override
            public void run () {
                latch.await();
            }
        };
    }

}
