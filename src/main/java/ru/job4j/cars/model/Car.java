package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public static Car of(String name, Brand brand) {
        Car car = new Car();
        car.setName(name);
        car.setBrand(brand);
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
