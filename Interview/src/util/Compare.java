package util;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;

public class Compare {

    public <T> boolean compare(T first[], T second[]) {
        if (first == null || second == null || first.length != second.length)
            return false;

        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i])
                return false;
        }
        return true;
    }

    public <T> boolean compare(List<T> first, List<T> second) {
        if (first == null || second == null || first.size() != second.size())
            return false;

        for (T item : first) {
            if (!second.contains(item))
                return false;
        }
        return true;
    }

    public <S> boolean compare(Set<S> first, Set<S> second) {
        return first != null && second != null && first.size() == second.size() && first.containsAll(second);
    }

    public <K, V> boolean compare(Map<K, V> first, Map<K, V> second) {
        if (first == null || second == null || first.size() != second.size()
                || !(first.keySet().containsAll(second.keySet())))
            return false;

        Set<Map.Entry<K, V>> entrySet = first.entrySet();
        for (Entry<K, V> entry : entrySet) {
            K key = entry.getKey();

            if (!second.containsKey(key))
                return false;

            if (entry.getValue() != second.get(key))
                return false;
        }

        return true;
    }
}
