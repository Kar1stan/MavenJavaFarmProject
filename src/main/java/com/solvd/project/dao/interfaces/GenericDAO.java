package com.solvd.project.dao.interfaces;

import java.util.List;

public interface GenericDAO<T, ID> {
    T getById(ID id);

    List<T> getAll();

    void insert(T entity);

    void update(T entity);

    void delete(ID id);
}
