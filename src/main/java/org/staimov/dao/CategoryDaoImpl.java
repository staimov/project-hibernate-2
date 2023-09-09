package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.staimov.entity.Category;

public class CategoryDaoImpl extends GenericDaoImpl<Category, Byte> implements CategoryDao {
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
