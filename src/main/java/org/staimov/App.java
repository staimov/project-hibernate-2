package org.staimov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.staimov.config.DBConstants;
import org.staimov.dao.*;
import org.staimov.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

public class App {
    private final SessionFactory sessionFactory;
    private final FilmDao filmDao;
    private final CustomerDao customerDao;
    private final AddressDao addressDao;
    private final CityDao cityDao;
    private final StoreDao storeDao;
    private final RentalDao rentalDao;
    private final PaymentDao paymentDao;
    private final InventoryDao inventoryDao;

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
        paymentDao = new PaymentDaoImpl(sessionFactory);
        inventoryDao = new InventoryDaoImpl(sessionFactory);
    }

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        //addCustomerExample();
        //returnRentedInventoryExample();
        rentInventoryItemExample();
    }

    public void addCustomerExample() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Store store = storeDao.getAny();
            if (store == null) {
                System.out.println("Store not found");
                transaction.rollback();
                return;
            }

            City city = cityDao.getByName("London", "United Kingdom");
            if (city == null) {
                System.out.println("City not found");
                transaction.rollback();
                return;
            }

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
    }

    public void returnRentedInventoryExample() {
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

    public void rentInventoryItemExample() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            List<Inventory> availableItems = inventoryDao.getAvailableInventoryItemsForRent();
            if (availableItems.isEmpty()) {
                System.out.println("Available Inventory for rent not found");
                transaction.rollback();
                return;
            }

            Inventory firstAvailableItem = availableItems.get(0);

            System.out.println(firstAvailableItem);

            Staff staff;
            if (!firstAvailableItem.getStore().getStaff().isEmpty()) {
                staff = firstAvailableItem.getStore().getStaff().iterator().next();
            }
            else {
                staff = firstAvailableItem.getStore().getManager();
            }

            Customer customer;
            customer = customerDao.getAny();
            if (customer == null) {
                System.out.println("Customer not found");
                transaction.rollback();
                return;
            }

            Rental newRental = new Rental();
            newRental.setInventory(firstAvailableItem);
            newRental.setStaff(staff);
            newRental.setCustomer(customer);
            newRental.setRentalDate(LocalDateTime.now());

            Payment newPayment = new Payment();
            newPayment.setCustomer(customer);
            newPayment.setStaff(staff);
            newPayment.setRental(newRental);
            newPayment.setPaymentDate(LocalDateTime.now());
            BigDecimal renalRate = firstAvailableItem.getFilm().getRentalRate();
            BigDecimal rentalDuration = BigDecimal.valueOf(firstAvailableItem.getFilm().getRentalDuration());
            newPayment.setAmount(renalRate.multiply(rentalDuration));

            rentalDao.save(newRental);
            paymentDao.save(newPayment);

            transaction.commit();

            System.out.println(newRental);
            System.out.println(newPayment);
        }
    }
}
