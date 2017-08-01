package generator.number;

import java.util.List;

public interface Generator<T> {

    public T generate(int size);
    public List<T> generateAsList(int size);
}
