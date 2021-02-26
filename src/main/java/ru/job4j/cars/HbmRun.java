package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Brand;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        List<Brand> brands = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            brands = session.createQuery("select distinct b from Brand b join fetch b.cars")
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }
}
