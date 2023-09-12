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
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static java.util.Objects.nonNull;
import static org.staimov.entity.SpecialFeature.TRAILERS;

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
    private final ActorDao actorDao;
    private final CategoryDao categoryDao;
    private final LanguageDao languageDao;
    private final FilmTextDao filmTextDao;

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
        actorDao = new ActorDaoImpl(sessionFactory);
        categoryDao = new CategoryDaoImpl(sessionFactory);
        languageDao = new LanguageDaoImpl(sessionFactory);
        filmTextDao = new FilmTextDaoImpl(sessionFactory);
    }

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        try {
            addCustomerExample();
            returnRentedInventoryExample();
            rentInventoryItemExample();
            addFilmExample();
        } finally {
            shutdown();
        }
    }

    private void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
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

            System.out.println(store);

            City city = cityDao.getByName("London", "United Kingdom");
            if (city == null) {
                System.out.println("City not found");
                transaction.rollback();
                return;
            }

            Address newAddress = new Address();
            newAddress.setAddress("221B Baker Street");
            newAddress.setDistrict("Marylebone");
            newAddress.setPostalCode("NW1 6XE");
            newAddress.setPhone("+44-20-7224-3688");
            newAddress.setCity(city);

            Customer newCustomer = new Customer();
            newCustomer.setFirstName("SHERLOCK");
            newCustomer.setLastName("HOLMES");
            newCustomer.setEmail("info@sherlock-holmes.co.uk");
            newCustomer.setAddress(newAddress);
            newCustomer.setActive(true);
            newCustomer.setStore(store);

            addressDao.save(newAddress);
            customerDao.save(newCustomer);

            transaction.commit();

            System.out.println(newCustomer);
        }
    }

    public void returnRentedInventoryExample() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Rental rentalToUpdate = rentalDao.getAnyUnreturned();
            if (rentalToUpdate != null) {
                System.out.println(rentalToUpdate);
                rentalToUpdate.setReturnDate(LocalDateTime.now());
                rentalDao.save(rentalToUpdate);

                transaction.commit();

                System.out.println(rentalToUpdate);
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

    public void addFilmExample() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            List<Actor> actors = actorDao.getPage(0, 5);
            if (actors.isEmpty()) {
                System.out.println("Actors not found");
                transaction.rollback();
                return;
            }

            List<Category> categories = categoryDao.getPage(0, 2);
            if (actors.isEmpty()) {
                System.out.println("Categories not found");
                transaction.rollback();
                return;
            }

            Language language = languageDao.getAny();
            if (language == null) {
                System.out.println("Language not found");
                transaction.rollback();
                return;
            }

            Store store = storeDao.getAny();
            if (store == null) {
                System.out.println("Store not found");
                transaction.rollback();
                return;
            }

            // Добавим фильм
            Film newFilm = new Film();
            newFilm.setActors(new HashSet<>(actors));
            newFilm.setCategories(new HashSet<>(categories));
            newFilm.setOriginalLanguage(language);
            newFilm.setLanguage(language);
            newFilm.setRating(Rating.PG_13);
            newFilm.setReleaseYear(2025);
            newFilm.setSpecialFeatures(Set.of(SpecialFeature.TRAILERS, SpecialFeature.DELETED_SCENES));
            newFilm.setLength((byte) 5);
            newFilm.setReplacementCost(BigDecimal.valueOf(18.99));
            newFilm.setRentalRate(BigDecimal.valueOf(5.99));
            newFilm.setRentalDuration((byte) 7);
            newFilm.setLength((short) 245);
            newFilm.setTitle("Titanic 2");
            newFilm.setDescription("Sometimes They Come Back");

            // Добавим в инвентарь новый элемент, соответствующий новому фильму,
            // чтобы его можно было отдавать в прокат
            Inventory newInventory = new Inventory();
            newInventory.setFilm(newFilm);
            newInventory.setStore(store);

            filmDao.save(newFilm);
            inventoryDao.save(newInventory);

            // Добавим текст фильма
            FilmText newFilmText = new FilmText();
            newFilmText.setFilm(newFilm);
            newFilmText.setTitle(newFilm.getTitle());
            newFilmText.setDescription(newFilm.getDescription());

            filmTextDao.save(newFilmText);

            transaction.commit();

            System.out.println(newFilm);
            System.out.println(newFilmText);
            System.out.println(newInventory);
        }
    }
}
