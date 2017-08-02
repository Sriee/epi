package util.generator;

import java.util.List;

public interface Generator<T> {
    public List<T> generateAsList(int size);
}
