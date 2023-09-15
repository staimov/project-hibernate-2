package org.staimov.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<V, K extends Serializable> {
    V getOne(K id);

    V getAny();

    List<V> getAll();

    List<V> getPage(int offset, int count);

    V save(V entity);

    V update(V entity);

    void delete(V entity);
}
