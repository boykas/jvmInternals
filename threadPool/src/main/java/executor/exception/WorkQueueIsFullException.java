package executor.exception;

public class WorkQueueIsFullException extends Exception {
    public WorkQueueIsFullException() {
    }

    public WorkQueueIsFullException(String message) {
        super(message);
    }
}
