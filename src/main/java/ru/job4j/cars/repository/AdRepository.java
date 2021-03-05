package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Ad;

import java.util.Date;
import java.util.List;

public class AdRepository {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public void save(Ad ad) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(ad);
            session.getTransaction().commit();
        }
    }

    public List<Ad> findAdsForLastDay() {
        List<Ad> ads;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            ads = session.createQuery("from Ad where created > :date", Ad.class)
                    .setParameter("date", new Date(
                            System.currentTimeMillis() - 24 * 60 * 60 * 1000))
                    .list();
            session.getTransaction().commit();
        }
        return ads;
    }

    public List<Ad> findAdsWithPhoto() {
        List<Ad> ads;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            ads = session.createQuery("from Ad where photos.size != 0", Ad.class).list();
            session.getTransaction().commit();
        }
        return ads;
    }

    public List<Ad> findAdsForBrand(String brandName) {
        List<Ad> ads;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            ads = session.createQuery("from Ad where car.brand.name = :brand", Ad.class)
                    .setParameter("brand", brandName)
                    .list();
            session.getTransaction().commit();
        }
        return ads;
    }
}
