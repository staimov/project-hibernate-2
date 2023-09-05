package org.staimov.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<V, K extends Serializable> {
    V getOne(K id);

    List<V> getAll();

    V save(V entity);

    V update(V entity);

    void delete(V entity);

    void deleteById(K id);
}
