package executor;

import executor.exception.WorkQueueIsFullException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MyExecutorTest {

    @Test
    public void newFixedThreadPool() throws WorkQueueIsFullException, InterruptedException {
        //given
        ConcurrentSkipListSet<String> threadNames = new ConcurrentSkipListSet<>();
        int poolSize = 5;
        MyExecutorService myExecutor = MyExecutor.newFixedThreadPool(poolSize);

        //when
        for (int i = 0; i < poolSize; i++) {
            myExecutor.execute(() -> {
                threadNames.add(Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(2000);
        //then
        assertEquals(poolSize, threadNames.size());
        myExecutor.shutdownNow();
    }

    @Test(expected = WorkQueueIsFullException.class)
    public void execute() throws WorkQueueIsFullException {
        //given
        MyExecutorService myExecutor = MyExecutor.newFixedThreadPool(3);
        //when
        for (int i = 0; i < 1000; i++) {
            myExecutor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //then
    }

    @Test
    public void testCount() throws WorkQueueIsFullException, InterruptedException {
        //given
        MyExecutorService myExecutorService = MyExecutor.newFixedThreadPool(5);
        AtomicInteger atomicInteger = new AtomicInteger();
        int result = 10;
        //when
        for (int i = 0; i < 10; i++) {
            myExecutorService.execute(() -> atomicInteger.getAndIncrement());
        }

        Thread.sleep(1000);
        assertEquals(result, atomicInteger.get());
    }
}