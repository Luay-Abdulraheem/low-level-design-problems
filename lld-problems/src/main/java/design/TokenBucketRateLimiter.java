package design;

import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {
    private final double capacity;  // Total tokens the bucket can hold
    private final double refillRate; // Tokens added per time unit
    private double currentTokens; // Current number of tokens in the bucket
    private long lastRefillTime; // Last time the bucket was refilled

    public TokenBucketRateLimiter(double capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.currentTokens = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest(int tokensRequested) {
        refillTokens();

        if (currentTokens >= tokensRequested) {
            currentTokens -= tokensRequested;
            return true; // Request is allowed
        } else {
            return false; // Request is denied
        }
    }

    private void refillTokens() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRefillTime;

        // Calculate the tokens to add based on elapsed time and refill rate
        double tokensToAdd = (elapsedTime / 1000.0) * refillRate;

        // Ensure the tokens do not exceed the capacity
        currentTokens = Math.min(capacity, currentTokens + tokensToAdd);

        lastRefillTime = currentTime;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(5, 1);

        for (int i = 0; i < 20; i++) {
            if (rateLimiter.allowRequest(2)) {
                System.out.println("Request " + (i + 1) + ": Allowed");
            } else {
                System.out.println("Request " + (i + 1) + ": Denied");
            }

            TimeUnit.SECONDS.sleep(1);
        }
    }
}
