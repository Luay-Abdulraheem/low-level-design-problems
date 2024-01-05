package design;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class CacheObj {
    int key;
    String value;

    CacheObj(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class CacheLRU2 {
    static Deque<Integer> q = new LinkedList<>();
    static Map<Integer, CacheObj> map = new HashMap<>();
    int CACHE_CAPACITY = 4;

    public String getElementFromCache(int key) {
        if (map.containsKey(key)) {
            CacheObj current = map.get(key);
            q.remove(current.key);
            q.addFirst(current.key);
            return current.value;
        }
        return "Not exist";
    }

    public void putElementInCache(int key, String value) {
        if (map.containsKey(key)) {
            CacheObj curr = map.get(key);
            q.remove(curr.key);
        } else {
            if (q.size() == CACHE_CAPACITY) {
                int temp = q.removeLast();
                map.remove(temp);
            }
        }
        CacheObj newItem = new CacheObj(key, value);
        q.addFirst(newItem.key);
        map.put(key, newItem);
    }

    public static void main(String[] args) {
        CacheLRU2 cache = new CacheLRU2();
        cache.putElementInCache(1, "Value_1");
        cache.putElementInCache(2, "Value_2");
        cache.putElementInCache(3, "Value_3");
        cache.putElementInCache(4, "Value_4");
        System.out.println(cache.getElementFromCache(2));
        System.out.println();
        System.out.println(q);
        System.out.println();
        System.out.println(cache.getElementFromCache(5));
        cache.putElementInCache(5, "Value_5");
        System.out.println();
        System.out.println(q);
        System.out.println();
    }
}
