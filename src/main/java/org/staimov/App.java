package org.staimov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.staimov.config.DBConstants;
import org.staimov.dao.*;
import org.staimov.entity.*;

import java.time.LocalDateTime;
import java.util.Properties;

public class App {
    private final SessionFactory sessionFactory;
    private final FilmDao filmDao;
    private final CustomerDao customerDao;
    private final AddressDao addressDao;
    private final CityDao cityDao;
    private final StoreDao storeDao;
    private final RentalDao rentalDao;

    public App() {
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

        filmDao = new FilmDaoImpl(sessionFactory);
        customerDao = new CustomerDaoImpl(sessionFactory);
        addressDao = new AddressDaoImpl(sessionFactory);
        cityDao = new CityDaoImpl(sessionFactory);
        storeDao = new StoreDaoImpl(sessionFactory);
        rentalDao = new RentalDaoImpl(sessionFactory);
    }

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        //addCustomerExample();
        returnRentedFilmExample();
    }

    public void addCustomerExample() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Store store = storeDao.getAny();

            City city = cityDao.getByName("London", "United Kingdom");

            if (store != null && city != null) {
                Address address = new Address();
                address.setAddress("221B Baker Street");
                address.setDistrict("Marylebone");
                address.setPostalCode("NW1 6XE");
                address.setPhone("+44-20-7224-3688");
                address.setCity(city);

                Customer customer = new Customer();
                customer.setFirstName("SHERLOCK");
                customer.setLastName("HOLMES");
                customer.setEmail("info@sherlock-holmes.co.uk");
                customer.setAddress(address);
                customer.setActive(true);
                customer.setStore(store);

                addressDao.save(address);
                customerDao.save(customer);

                transaction.commit();

                System.out.println(customer);
            }
            else {
                System.out.println("Store or City not found");
                transaction.rollback();
            }
        }
    }

    public void returnRentedFilmExample() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Rental rental = rentalDao.getAnyUnreturned();
            if (rental != null) {
                System.out.println(rental);
                rental.setReturnDate(LocalDateTime.now());
                rentalDao.save(rental);

                transaction.commit();

                System.out.println(rental);
            }
            else {
                System.out.println("Rental not found");
                transaction.rollback();
            }
        }
    }
}
