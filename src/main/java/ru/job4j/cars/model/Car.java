package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "body_id")
    private Body body;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
        joinColumns = {@JoinColumn(name = "driver_id", nullable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private Set<Driver> drivers;

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public static Car of(String name, Brand brand, Engine engine, Body body, Driver driver) {
        Car car = new Car();
        car.setName(name);
        car.setBrand(brand);
        car.setEngine(engine);
        car.setBody(body);
        car.setDriver(driver);
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", brand=" + brand.getName()
                + '}';
    }
}
