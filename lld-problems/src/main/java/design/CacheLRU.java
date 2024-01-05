package design;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class CacheLRU {
    Set<Integer> cache;
    int capacity;

    public CacheLRU(int capacity) {
        // Set s = Collections.synchronizedSet(new LinkedHashSet(...));
        this.cache = new LinkedHashSet<>(capacity); // or LinkedHashMap to preserve insertion order
        this.capacity = capacity;
    }

    public boolean get(int key) {
        if (!cache.contains(key)) {
            return false;
        }
        cache.remove(key);
        cache.add(key);
        return true;
    }

    public void getValue(int key) {
        if (!get(key)) {
            put(key);
        }
    }

    public void display() {
        cache.stream().collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(System.out::println);
    }

    public void put(int key) {
        if (cache.size() == capacity) {
            cache.remove(cache.stream().findFirst().get());
        }

        cache.add(key);
    }

    public static void main(String[] args) {
        CacheLRU obj = new CacheLRU(3);
        obj.getValue(4);
        obj.getValue(5);
        obj.getValue(6);
        obj.getValue(4);
        obj.getValue(7);
        obj.getValue(5);
        obj.display();
    }
}
