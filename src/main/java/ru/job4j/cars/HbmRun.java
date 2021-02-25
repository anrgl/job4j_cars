package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Car;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Car vaz2101 = Car.of("Vaz 2101");
            Car vaz2110 = Car.of("Vaz 2110");
            Car largus = Car.of("Largus");
            Car xray = Car.of("X-Ray");
            Car vesta = Car.of("Vesta");

            Brand brandVaz = Brand.of("Vaz");
            brandVaz.addCar(vaz2101);
            brandVaz.addCar(vaz2110);
            brandVaz.addCar(largus);
            brandVaz.addCar(xray);
            brandVaz.addCar(vesta);

            session.save(brandVaz);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
