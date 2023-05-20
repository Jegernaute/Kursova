package com.example.kursova;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller  {

    @FXML
    private Button fx_ad1;

    @FXML
    private Button fx_ad2;

    @FXML
    private Button fx_ad3;

    @FXML
    private Button fx_but1;

    @FXML
    private Button fx_but2;

    @FXML
    private Button fx_but3;

    @FXML
    private Button fx_exit;

    @FXML
    private TableView<BD> fx_table;

    @FXML
    private TableColumn<?, ?> fx_1;

    @FXML
    private TableColumn<?, ?> fx_2;

    @FXML
    private TableColumn<?, ?> fx_3;

    @FXML
    private TableColumn<?, ?> fx_4;

    @FXML
    private TableColumn<?, ?> fx_5;

    @FXML
    private TableColumn<?, ?> fx_6;


    public void initialize( ) {

        fx_but1.setOnAction(event -> {
            fx_1.setText("Номер");
            fx_2.setText("Номер телефону");
            fx_3.setText("Ім'я ");
            fx_4.setText("Фамілія");
            fx_5.setText("Вік");
            fx_6.setText("Місце робота");
            // Получаем данные о фильмах из базы данных
            List<Phonebook> phonebooks = fetchPhonebookData();
            // Создаем ObservableList для хранения данных
            ObservableList<BD> phonebookList = FXCollections.observableArrayList(phonebooks);
            // Устанавливаем данные в TableView
            fx_table.setItems(phonebookList);
            // Настраиваем соответствие между столбцами таблицы и свойствами модели данных
            fx_1.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            fx_2.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            fx_3.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            fx_4.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            fx_5.setCellValueFactory(new PropertyValueFactory<>("age"));
            fx_6.setCellValueFactory(new PropertyValueFactory<>("job"));
            handleFxOutPhonebookAction(null);
        });

        fx_but2.setOnAction(event -> {
            fx_1.setText("Другий телефон");
            fx_2.setText("Номер");
            fx_3.setText(" ");
            fx_4.setText(" ");
            fx_5.setText(" ");
            fx_6.setText(" ");
            // Получаем данные о фильмах из базы данных
            List<Second_phone> second_phones = fetchSecond_phoneData();
            // Создаем ObservableList для хранения данных
            ObservableList<BD> second_phoneList = FXCollections.observableArrayList(second_phones);
            // Устанавливаем данные в TableView
            fx_table.setItems(second_phoneList);
            // Настраиваем соответствие между столбцами таблицы и свойствами модели данных
            fx_1.setCellValueFactory(new PropertyValueFactory<>("phone"));
            fx_2.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            handleFxOutSecond_phoneAction(null);
        });


            fx_but3.setOnAction(event -> {
                fx_1.setText("Вулиця");
                fx_2.setText("Номер будинку");
                fx_3.setText("Квартира");
                fx_4.setText("Номер");
                fx_5.setText(" ");
                fx_6.setText(" ");
                // Получаем данные о фильмах из базы данных
                List<Adress> adresses = fetchAdressData();
                // Создаем ObservableList для хранения данных
                ObservableList<BD> adressList = FXCollections.observableArrayList(adresses);
                // Устанавливаем данные в TableView
                fx_table.setItems(adressList);
                // Настраиваем соответствие между столбцами таблицы и свойствами модели данных
                fx_1.setCellValueFactory(new PropertyValueFactory<>("street"));
                fx_2.setCellValueFactory(new PropertyValueFactory<>("house"));
                fx_3.setCellValueFactory(new PropertyValueFactory<>("apartment"));
                fx_4.setCellValueFactory(new PropertyValueFactory<>("user_id"));
                handleFxOutAdressAction(null);
            });
    }
    private List<Phonebook> fetchPhonebookData() {
        // Соединение с базой данных
        Connection connection = null;
        // Список для хранения данных о фильмах
        List<Phonebook> phonebooks = new ArrayList<>();
        try {
            // Устанавливаем соединение с базой данных
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook", "root", "1234");
            // Выполняем SQL-запрос для извлечения данных из таблицы movies
            String sql = "SELECT * FROM phonebook";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            // Извлекаем данные из результата запроса и добавляем их в список movies
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String phone_number = resultSet.getString("phone_number");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String job = resultSet.getString("job");
                Phonebook phonebook = new Phonebook(user_id, phone_number, first_name, last_name, age, job);
                phonebooks.add(phonebook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрываем соединение с базой данных
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Верните список объектов Movie, содержащих данные о фильмах
        return phonebooks;
    }


    @FXML
    private void handleFxOutPhonebookAction(ActionEvent event) {
        // Получаем данные фильмов из базы данных
        List<Phonebook> phonebooks = fetchPhonebookData();
        // Очищаем текущую модель данных
        fx_table.getItems().clear();
        // Добавляем новые данные фильмов в модель данных
        fx_table.getItems().addAll(phonebooks);
        // Обновляем TableView
        fx_table.refresh();

    }
    private List<Second_phone> fetchSecond_phoneData() {
        // Соединение с базой данных
        Connection connection = null;
        // Список для хранения данных о фильмах
        List<Second_phone> second_phones = new ArrayList<>();
        try {
            // Устанавливаем соединение с базой данных
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook", "root", "1234");
            // Выполняем SQL-запрос для извлечения данных из таблицы movies
            String sql = "SELECT * FROM second_phone";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            // Извлекаем данные из результата запроса и добавляем их в список movies
            while (resultSet.next()) {
                String phone = resultSet.getString("phone");
                int user_id = resultSet.getInt("user_id");
                Second_phone second_phone = new Second_phone(phone, user_id);
                second_phones.add(second_phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрываем соединение с базой данных
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Верните список объектов Movie, содержащих данные о фильмах
        return second_phones;
    }


    @FXML
    private void handleFxOutSecond_phoneAction(ActionEvent event) {
        // Получаем данные фильмов из базы данных
        List<Second_phone> second_phones = fetchSecond_phoneData();
        // Очищаем текущую модель данных
        fx_table.getItems().clear();
        // Добавляем новые данные фильмов в модель данных
        fx_table.getItems().addAll(second_phones);
        // Обновляем TableView
        fx_table.refresh();

    }

    private List<Adress> fetchAdressData() {
        // Соединение с базой данных
        Connection connection = null;
        // Список для хранения данных о фильмах
        List<Adress> adresses = new ArrayList<>();
        try {
            // Устанавливаем соединение с базой данных
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook", "root", "1234");
            // Выполняем SQL-запрос для извлечения данных из таблицы movies
            String sql = "SELECT * FROM adress";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            // Извлекаем данные из результата запроса и добавляем их в список movies
            while (resultSet.next()) {
                String street = resultSet.getString("street");
                int house = resultSet.getInt("house");
                int apartment = resultSet.getInt("apartment");
                int user_id = resultSet.getInt("user_id");
                Adress adress = new Adress(street, house, apartment, user_id);
                adresses.add(adress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрываем соединение с базой данных
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Верните список объектов Movie, содержащих данные о фильмах
        return adresses;
    }


    @FXML
    private void handleFxOutAdressAction(ActionEvent event) {
        // Получаем данные фильмов из базы данных
        List<Adress> adresses = fetchAdressData();
        // Очищаем текущую модель данных
        fx_table.getItems().clear();
        // Добавляем новые данные фильмов в модель данных
        fx_table.getItems().addAll(adresses);
        // Обновляем TableView
        fx_table.refresh();

    }

}