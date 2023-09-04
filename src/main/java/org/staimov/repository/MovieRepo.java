package org.staimov.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.staimov.config.DBConstants;
import org.staimov.entity.*;

import java.util.Properties;

public class MovieRepo {
    private final SessionFactory sessionFactory;

    public MovieRepo() {
        Properties properties = new Properties();

        properties.put(Environment.DIALECT, DBConstants.DB_DIALECT);
        properties.put(Environment.USER, DBConstants.DB_USER);
        properties.put(Environment.PASS, DBConstants.DB_PASS);
        properties.put(Environment.HBM2DDL_AUTO, DBConstants.DB_HBM2DDL_AUTO);
        properties.put(Environment.DRIVER, DBConstants.DB_DRIVER);
        properties.put(Environment.URL, DBConstants.DB_URL);
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, DBConstants.CURRENT_SESSION_CONTEXT_CLASS);

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();
    }

    public Film getFilm(short id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Film.class, id);
        }
    }

    public Actor getActor(short id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Actor.class, id);
        }
    }

}
