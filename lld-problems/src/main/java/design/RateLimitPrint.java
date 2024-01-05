package design;

import java.util.HashMap;
import java.util.Map;

public class RateLimitPrint {
    private final Map<String, Integer> messages;

    /**
     * Initialize your data structure here.
     */
    public RateLimitPrint() {
        this.messages = new HashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!messages.containsKey(message)) {
            messages.put(message, timestamp);
            return true;
        } else {
            int lastTimeStamp = messages.get(message);
            if (timestamp - lastTimeStamp < 10) {
                return false;
            } else {
                messages.put(message, timestamp);
                return true;
            }
        }
    }
}
