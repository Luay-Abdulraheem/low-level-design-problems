package design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SlidingWindowRateLimiter {
    static Map<String, Queue<Long>> holder = new HashMap<>();
    static int TIME_LIMIT = 100;
    static int MAX_HITS = 5;

    public void hitAPI(String clientId, long timeOfHit) {
        boolean result = isAllowed(clientId, timeOfHit);
        if (result) {
            System.out.println("hit @ " + timeOfHit + "ms");
        } else {
            System.out.println("blocked @ " + timeOfHit + "ms");
        }
    }

    public boolean isAllowed(String clientId, long timeOfHit) {
        boolean result = false;
        if (holder.containsKey(clientId)) {
            //client has hit before, keep polling the queue till you reach a time whose diff with timeOfHit is less than 1000
            Queue<Long> timings = holder.get(clientId);
            while (!timings.isEmpty() && timeOfHit - timings.peek() >= TIME_LIMIT) {
                timings.poll();
            }
            //the remaining queue is the number of hits within the 1000 ms
            if (timings.size() < MAX_HITS) {
                //add the timeOfHit
                timings.add(timeOfHit);
                //update the holder
                holder.put(clientId, timings);
                result = true;
            }
        } else {
            //client hitting first time, simply put it in holder
            Queue<Long> timings = new LinkedList<>();
            timings.add(timeOfHit);
            holder.put(clientId, timings);
            result = true;
        }
        return result;
    }
}
