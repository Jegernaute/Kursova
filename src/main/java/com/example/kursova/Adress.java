package com.example.kursova;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Adress extends  BD {
String street;
int house;
int apartment;
int user_id;

    public Adress(String street, int house, int apartment, int user_id) {
        if (house < 0 || apartment < 0) {
            throw new IllegalArgumentException("Невірний номер будинку чи квартири");
        }
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.user_id = user_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Adress other = (Adress) obj;
        return house == other.house &&
                apartment == other.apartment &&
                Objects.equals(street, other.street) &&
                user_id == other.user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, house, apartment, user_id);
    }

}
