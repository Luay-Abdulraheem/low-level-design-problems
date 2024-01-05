package design;

import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

public class HitCounter {
    // Use TreeMap to store hits at different timestamps
    SortedMap<Integer, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {
        map = new TreeMap<>();
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        // Put the hit in the TreeMap with its timestamp as the key
        // and the number of hits as the value
        map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        // Calculate the start timestamp for the past 5 minutes
        int start = timestamp - 300;
        // If the start timestamp is less than 0, set it to 0
        if (start < 0) {
            start = 0;
        }
        // Iterate over the tail map of the TreeMap starting from
        // the start timestamp and add up the number of hits at each timestamp
        int count = 0;
        for (int key : map.tailMap(start).keySet()) {
            count += map.get(key);
        }
        // Return the total number of hits
        return count;
    }

    Queue<Integer> timeQueue;

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds
     *     granularity).
     */
    public void hit2(int timestamp) { timeQueue.offer(timestamp); }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds
     *     granularity).
     */
    public int getHits2(int timestamp)
    {
        while (!timeQueue.isEmpty()
                && timestamp - timeQueue.peek() >= 300) {
            timeQueue.poll();
        }
        return timeQueue.size();
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        int hits1 = counter.getHits(4); // should return 3
        counter.hit(300);
        int hits2 = counter.getHits(300); // should return 4
        int hits3 = counter.getHits(301); // should return 3
        System.out.println(hits1);
        System.out.println(hits2);
        System.out.println(hits3);
    }
}
