package com.example.kursova;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Phonebook extends  BD {
    int user_id;
    String phone_number;
    String first_name;
    String last_name;
    int age;
    String job;
    public Phonebook(int user_id, String phone_number, String first_name, String last_name, int age, String job){

        this.user_id = user_id;
        this.phone_number = phone_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.job = job;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
