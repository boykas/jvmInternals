package executor;

import executor.exception.WorkQueueIsFullException;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class MyExecutor implements MyExecutorService {

    private static final Logger LOG = Logger.getLogger(MyExecutor.class.getSimpleName());

    private BlockingQueue<Runnable> queue;
    private Set<Thread> poolOfThreads;

    private MyExecutor(int poolSize, int workQueueSize) {
        this.queue = new BlockingQueue<>(workQueueSize);
        this.poolOfThreads = new HashSet<>();
        for (int i = 0; i < poolSize; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    Runnable runnable = this.queue.poll();
                    LOG.info("Task was started by Thread :" + Thread.currentThread().getName());
                    runnable.run();
                    LOG.info("Task was finished by Thread :" + Thread.currentThread().getName());

                }
            }, i + " Thread");
            thread.start();
            this.poolOfThreads.add(thread);
        }
    }

    /**
     * Creates a new ThreadPool with the given initial number of threads and work queue size
     *
     * @param poolSize      the number of threads to keep in the pool, even
     *                      if they are idle
     * @param workQueueSize the queue to use for holding tasks before they are
     *                      executed.  This queue will hold only the {@code Runnable}
     *                      tasks submitted by the {@code execute} method.
     */
    public static MyExecutorService newFixedThreadPool(int poolSize, int workQueueSize) {
        return new MyExecutor(poolSize, workQueueSize);
    }

    public static MyExecutorService newFixedThreadPool(int poolSize) {
        return newFixedThreadPool(poolSize, 10);
    }

    @Override
    public void execute(Runnable command) throws WorkQueueIsFullException {
        queue.offer(command);
    }

    @Override
    public void shutdownNow() {
        poolOfThreads.forEach(Thread::interrupt);
    }
}
