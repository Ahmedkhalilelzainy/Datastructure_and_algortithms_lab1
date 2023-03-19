public interface treeInterface <T extends Comparable<T>> {
    void getRoot();
    boolean isEmpty();
    void clear();
    boolean contain(T value);
    boolean search(T value);
    boolean insert(T value);
    void delete(T value);
}
