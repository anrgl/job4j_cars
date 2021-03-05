package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private boolean isSold;
    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id",
            nullable = false)
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id",
            nullable = false)
    private Driver driver;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    public static Ad of(String desc, boolean isSold, double price, Car car, Driver driver, Date created) {
        Ad ad = new Ad();
        ad.setDescription(desc);
        ad.setSold(isSold);
        ad.setPrice(price);
        ad.setCar(car);
        ad.setDriver(driver);
        ad.setCreated(created);
        return ad;
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ad ad = (Ad) o;
        return id == ad.id
                && isSold == ad.isSold
                && Double.compare(ad.price, price) == 0
                && Objects.equals(description, ad.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isSold, price);
    }
}
