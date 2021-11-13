package com.revature.quizzard.repositories;

import com.revature.quizzard.util.collections.List;

public interface CrudRepository<T, I> {
    List<T> findAll();
    T findById(I id);
    T save(T newObj);
    boolean update(T updatedObj);
    boolean removeById(I id);
}
