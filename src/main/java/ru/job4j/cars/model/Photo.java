package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;

    public static Photo of(String url) {
        Photo photo = new Photo();
        photo.setUrl(url);
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Photo photo = (Photo) o;
        return id == photo.id
                && Objects.equals(url, photo.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }
}
