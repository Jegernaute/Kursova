package com.example.kursova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
class AdressTest {
    @Test
    public void testConstructor() {
        // Arrange
        String expectedStreet = "Main Street";
        int expectedHouse = 123;
        int expectedApartment = 456;
        int expectedUserId = 789;

        // Act
        Adress adress = new Adress(expectedStreet, expectedHouse, expectedApartment, expectedUserId);

        // Assert
        assertEquals(expectedStreet, adress.getStreet());
        assertEquals(expectedHouse, adress.getHouse());
        assertEquals(expectedApartment, adress.getApartment());
        assertEquals(expectedUserId, adress.getUser_id());
    }

    @Test
    public void testGetters() {
        // Arrange
        String expectedStreet = "Main Street";
        int expectedHouse = 123;
        int expectedApartment = 456;
        int expectedUserId = 789;

        Adress adress = new Adress(expectedStreet, expectedHouse, expectedApartment, expectedUserId);

        // Act
        String actualStreet = adress.getStreet();
        int actualHouse = adress.getHouse();
        int actualApartment = adress.getApartment();
        int actualUserId = adress.getUser_id();

        // Assert
        assertEquals(expectedStreet, actualStreet);
        assertEquals(expectedHouse, actualHouse);
        assertEquals(expectedApartment, actualApartment);
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void testSetters() {
        // Arrange
        Adress adress = new Adress("Old Street", 111, 222, 333);

        String expectedStreet = "New Street";
        int expectedHouse = 444;
        int expectedApartment = 555;
        int expectedUserId = 666;

        // Act
        adress.setStreet(expectedStreet);
        adress.setHouse(expectedHouse);
        adress.setApartment(expectedApartment);
        adress.setUser_id(expectedUserId);

        // Assert
        assertEquals(expectedStreet, adress.getStreet());
        assertEquals(expectedHouse, adress.getHouse());
        assertEquals(expectedApartment, adress.getApartment());
        assertEquals(expectedUserId, adress.getUser_id());
    }

    @Test
    public void testApartmentAndUserIdStability() {
        // Arrange
        String expectedStreet = "Main Street";
        int expectedHouse = 123;
        int expectedApartment = 456;
        int expectedUserId = 789;

        Adress adress = new Adress(expectedStreet, expectedHouse, expectedApartment, expectedUserId);

        // Act
        adress.setApartment(456);
        adress.setUser_id(789);

        // Assert
        assertEquals(expectedApartment, adress.getApartment());
        assertEquals(expectedUserId, adress.getUser_id());
    }

    @Test
    public void testConstructorWithInvalidArguments() {
        // Arrange
        String street = "Main Street";
        int invalidHouse = -1;
        int validApartment = 456;
        int validUserId = 789;

        // Act & Assert
        try {
            new Adress(street, invalidHouse, validApartment, validUserId);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // IllegalArgumentException was thrown as expected
        }
    }

    @Test
    public void testEqualsAndHashCode() {
        Adress adress1 = new Adress("Street", 123, 1, 1);
        Adress adress2 = new Adress("Street", 123, 1, 1);
        Adress adress3 = new Adress("DifferentStreet", 456, 2, 2);

        // Перевірка рівності двох об'єктів з однаковими значеннями властивостей
        assertEquals(adress1, adress2);
        assertEquals(adress2, adress1);

        // Перевірка рівності об'єкта самому собі
        assertEquals(adress1, adress1);

        // Перевірка нерівності двох об'єктів з різними значеннями властивостей
        assertNotEquals(adress1, adress3);
        assertNotEquals(adress3, adress1);

        // Перевірка hashCode для двох об'єктів з однаковими значеннями властивостей
        assertEquals(adress1.hashCode(), adress2.hashCode());

        // Перевірка hashCode для двох об'єктів з різними значеннями властивостей
        assertNotEquals(adress1.hashCode(), adress3.hashCode());
    }

    @Test
    void getStreet() {
    }

    @Test
    void setStreet() {
    }

    @Test
    void getHouse() {
    }

    @Test
    void setHouse() {
    }

    @Test
    void getApartment() {
    }

    @Test
    void setApartment() {
    }

    @Test
    void getUser_id() {
    }

    @Test
    void setUser_id() {
    }
}