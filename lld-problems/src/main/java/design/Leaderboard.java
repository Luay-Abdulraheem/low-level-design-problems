package design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Leaderboard {
    private Map<Integer, Integer> scores;
    public Leaderboard() {
        scores = new HashMap<>();
    }

    public void addScore(int playerId, int score) { // Time Complexity: O(1)
        // if the player is not already present, initialize the player's score to 0
        // add the given score to the current score of the player
        scores.put(playerId, scores.getOrDefault(playerId, 0) + score); // O(1)
    }

    public int topQueue(int k) {
        // We are taking a min-heap containing values of the hash map.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int score : scores.values()) { // O(N)
            minHeap.offer(score);
            if (minHeap.size() > k) {
                minHeap.poll(); // extract min because we are interested in Top elements
                // O(logK)
            }
        } // Overall time complexity to execute this foreach loop: O(NlogK)

        int sum = 0;
        for (int a : minHeap) { // O(K)
            sum += a;
        }
        return sum;

        // Time complexity: O(K) + O(NlogK) = O(NlogK)
    }

    public int topStream(int k) {
        // We would sort in descending order and grab the first K entries
        return scores.values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(k)
                .mapToInt(value -> value)
                .sum();
        // Time Complexity: O(NlogN) + O(K) = O(NlogN) where N = total number of players
    }


    public void reset(int playerId) { // Time Complexity: O(1)
        // update score of given player to zero
        scores.put(playerId, 0); // O(1)
    }

    /*
    Space Complexity: O(N + K), where N = total number  of players.
    We are keeping scores of all the players in the map.
    O(K) is used by the heap in top(K) method.
    */

    public static void main(String args[]) {
        String[] apply = {"addScore", "addScore", "addScore","addScore","addScore", "top", "reset", "reset", "addScore", "top"};
        int[][] val = {{1,73}, {2,56}, {3,39}, {4, 51}, {5,4}, {1}, {1}, {2}, {2,51}, {3}};

        Leaderboard leaderboard = new Leaderboard();
        for (int i = 0; i < apply.length; i++) {
            if (apply[i].equalsIgnoreCase("addScore")) {
                leaderboard.addScore(val[i][0], val[i][1]);
            } else if (apply[i].equalsIgnoreCase("reset")) {
                leaderboard.reset(val[i][0]);
            } else if (apply[i].equalsIgnoreCase("top")) {
                System.out.println(leaderboard.topStream(val[i][0]));
                System.out.println(leaderboard.topQueue(val[i][0]));
            }
        }
    }
}
