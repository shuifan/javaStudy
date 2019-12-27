package algorithm.cuckoo;

public interface HashFamily<T> {

    void generateNewHashFunction();

    int getFunctionCount();

    int hash(T t, int functionIndex);
}
