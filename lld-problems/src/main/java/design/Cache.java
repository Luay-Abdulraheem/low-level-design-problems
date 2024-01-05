package design;

import java.util.Optional;

public interface Cache {

    void add(String key, Object value, long periodInMillis);

    void remove(String key);

    Optional<Object> get(String key);

    void clear();

    int size();
}
