package org.staimov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.staimov.config.DBConstants;
import org.staimov.dao.FilmDao;
import org.staimov.entity.*;

import java.util.Properties;

public class Main {
    private final SessionFactory sessionFactory;
    private final FilmDao filmDao;

    public Main() {
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
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();

        filmDao = new FilmDao(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();

        try (Session session = main.sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Film film = main.filmDao.findOne((short) 1);
            System.out.println(film);
            transaction.commit();
        }
    }
}
