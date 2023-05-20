package com.example.kursova;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/notebook?useSSL=false";
    public static final String DB_USER = "root"; // ваш пользователь, у нас root
    public static final String DB_PASSWORD = getPassword(); // ваш пароль (получение из безопасного источника)

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка подключения к базе данных", ex);
        }
    }

    protected static String getPassword() {// Ваш код для получения пароля из безопасного источника
        String password = "1234"; // Пример: пароль получен из безопасного источника
        return password;
    }

}
