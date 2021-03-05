package ru.job4j.cars.repository;

import org.junit.Test;
import ru.job4j.cars.model.*;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AdRepositoryTest {
    @Test
    public void whenFindAdsForLastDay() {
        Driver driver = Driver.of("Driver");
        Car car = Car.of(
                "2110",
                Brand.of("Vaz"),
                Engine.of("V6"),
                Body.of("Classic"),
                driver);
        Date today = new Date(System.currentTimeMillis() - 12 * 60 * 60 * 1000);
        Date yesterday = new Date(System.currentTimeMillis() - 36 * 60 * 60 * 1000);
        AdRepository repository = new AdRepository();
        Ad adToday = Ad.of("Description", false, 595.99, car, driver, today);
        Ad adYesterday = Ad.of("Description", false, 600.00, car, driver, yesterday);
        repository.save(adToday);
        repository.save(adYesterday);
        List<Ad> lastDaysAds = repository.findAdsForLastDay();
        assertThat(lastDaysAds.size(), is(1));
        assertEquals("Description", lastDaysAds.get(0).getDescription());
    }

    @Test
    public void whenFindAdsWithPhotos() {
        Driver driver = Driver.of("Driver");
        Car car = Car.of(
                "2110",
                Brand.of("Vaz"),
                Engine.of("V6"),
                Body.of("Classic"),
                driver);
        AdRepository repository = new AdRepository();
        Ad adWithPhoto = Ad.of("Description", false, 595.99, car, driver, new Date());
        adWithPhoto.addPhoto(Photo.of("images/front.jpg"));
        repository.save(adWithPhoto);
        assertThat(repository.findAdsWithPhoto().size(), is(1));
    }

    @Test
    public void whenFindAdsForBrand() {
        Driver driver = Driver.of("Driver");
        Car car = Car.of(
                "2110",
                Brand.of("Vaz"),
                Engine.of("V6"),
                Body.of("Classic"),
                driver);
        AdRepository repository = new AdRepository();
        Ad vazAd = Ad.of("Description", false, 595.99, car, driver, new Date());
        repository.save(vazAd);
        repository.findAdsForBrand("Vaz");
        assertThat(repository.findAdsForBrand("Vaz").size(), is(1));
        assertThat(repository.findAdsForBrand("BMW").size(), is(0));
    }
}