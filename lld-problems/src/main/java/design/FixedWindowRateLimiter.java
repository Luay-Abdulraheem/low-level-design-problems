package design;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class FixedWindowRateLimiter {
    private final int capacity; // Maximum number of requests allowed in the window
    private final long windowSize; // Time window duration in milliseconds
    private final Queue<Long> requestQueue; // Queue to track request timestamps

    public FixedWindowRateLimiter(int capacity, long windowSize) {
        this.capacity = capacity;
        this.windowSize = windowSize;
        this.requestQueue = new ArrayDeque<>();
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // Remove requests from the queue that are outside the current window
        while (!requestQueue.isEmpty() && currentTime - requestQueue.peek() >= windowSize) {
            requestQueue.poll();
        }

        // Check if the number of requests in the window is within the allowed capacity
        if (requestQueue.size() < capacity) {
            requestQueue.offer(currentTime); // Record the current request timestamp
            return true; // Request is allowed
        } else {
            return false; // Request is denied
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter(5, 1000);

        for (int i = 0; i < 10; i++) {
            if (rateLimiter.allowRequest()) {
                System.out.println("Request " + (i + 1) + ": Allowed");
            } else {
                System.out.println("Request " + (i + 1) + ": Denied");
            }

            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
