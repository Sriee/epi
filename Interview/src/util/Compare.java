package util;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;

public class Compare {

    public Integer[] toObject(int[] arr){
        Integer result[] = new Integer[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    public Long[] toObject(long[] arr){
        Long result[] = new Long[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    public Float[] toObject(float[] arr){
        Float result[] = new Float[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    public Double[] toObject(double[] arr){
        Double result[] = new Double[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    public Boolean[] toObject(boolean[] arr){
        Boolean result[] = new Boolean[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    public Character[] toObject(char[] arr){
        Character result[] = new Character[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    public <T> boolean compare(T first[], T second[]){

        if(first == null) return false;
        if(second == null) return false;
        if(first.length != second.length) return false;

        for(int i = 0; i < first.length; i++){
            if(first[i] != second[i]) return false;
        }
        return true;
    }

    public <T> boolean compare(List<T> first, List<T> second){
        if(first == null) return false;
        if(second == null) return false;

        if(first.size() != second.size()) return false;

        for(T item : first){
            if(!second.contains(item)) return false;
        }
        return true;
    }

    public <S> boolean compare(Set<S> first, Set<S> second){
        if(first == null) return false;
        if(second == null) return false;
        if(first.size() != second.size()) return false;
        return first.containsAll(second);
    }

    public <K, V> boolean compare(Map<K, V> first, Map<K, V> second){
        if(first == null) return false;
        if(second == null) return false;
        if(first.size() != second.size()) return false;

        if(!first.keySet().containsAll(second.keySet()))
            return false;

        Set<Map.Entry<K, V>> entrySet = first.entrySet();
        for(Entry<K, V> entry: entrySet){
            K key = entry.getKey();

            if(!second.containsKey(key)) return false;

            if(entry.getValue() != second.get(key)) return false;
        }

        return true;
    }
}
