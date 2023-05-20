package com.example.kursova;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Second_phone extends  BD {
    String phone;;
    int user_id;

    public Second_phone(String phone, int user_id) {
        this.phone = phone;
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

