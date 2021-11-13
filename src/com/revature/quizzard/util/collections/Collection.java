package com.revature.quizzard.util.collections;

public interface Collection<T> {
    void add(T element);
    boolean contains(T element);
    boolean isEmpty();
    boolean remove(T element);
    int size();
}
