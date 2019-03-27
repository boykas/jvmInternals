package executor;

import executor.exception.WorkQueueIsFullException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

class BlockingQueue<T> {
    private final static Logger LOG = Logger.getLogger(BlockingQueue.class.getName());
    private Queue<T> queue = new LinkedList<>();
    private int maxQueueSize = 0;

    BlockingQueue(int queueSize) {
        this.maxQueueSize = queueSize;
    }

    synchronized void offer(T task) throws WorkQueueIsFullException {
        if (this.queue.size() == maxQueueSize) {
            throw new WorkQueueIsFullException("workQueue is full");
        }
        if (this.queue.isEmpty()) {
            notifyAll();
        }
        this.queue.offer(task);
    }

    synchronized T poll() {
        while (this.queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOG.warning(Thread.currentThread().getName() + " was interrupted");
            }
        }
        if (this.queue.size() == maxQueueSize) {
            notifyAll();
        }
        return this.queue.poll();
    }

}
