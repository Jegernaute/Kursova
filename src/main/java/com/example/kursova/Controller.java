package com.example.kursova;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private Button fx_del;

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
            fx_2.setText("Телефон");
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
            fx_3.setCellValueFactory(new PropertyValueFactory<>(null));
            fx_4.setCellValueFactory(new PropertyValueFactory<>(null));
            fx_5.setCellValueFactory(new PropertyValueFactory<>(null));
            fx_6.setCellValueFactory(new PropertyValueFactory<>(null));
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
                fx_5.setCellValueFactory(new PropertyValueFactory<>(null));
                fx_6.setCellValueFactory(new PropertyValueFactory<>(null));
                handleFxOutAdressAction(null);
            });

        //Ввод данных
        fx_ad1.setOnAction(event -> {
            // Создаем диалоговое окно для ввода данных фильма
            Dialog<Phonebook> dialog = new Dialog<>();
            dialog.setTitle("Додати телефон");

            // Устанавливаем кнопку "Добавить" и кнопку "Отмена"
            ButtonType addButton = new ButtonType("Додати", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

            // Создаем форму для ввода данных
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            // Создаем текстовые поля для ввода данных
            TextField user_idTextField = new TextField();
            TextField phone_numberTextField = new TextField();
            TextField first_nameTextField = new TextField();
            TextField last_nameTextField = new TextField();
            TextField ageTextField = new TextField();
            TextField jobTextField = new TextField();

            // Добавляем текстовые поля на форму
            grid.add(new Label("phone_number:"), 0, 0);
            grid.add( phone_numberTextField, 1, 0);
            grid.add(new Label("First_name:"), 0, 1);
            grid.add(first_nameTextField, 1, 1);
            grid.add(new Label("Last_name:"), 0, 2);
            grid.add(last_nameTextField, 1, 2);
            grid.add(new Label("Age:"), 0, 3);
            grid.add(ageTextField, 1, 3);
            grid.add(new Label("Job:"), 0, 4);
            grid.add(jobTextField, 1, 4);

            // Устанавливаем форму на диалоговое окно
            dialog.getDialogPane().setContent(grid);

            // Устанавливаем фокус на поле Title при открытии окна
            Platform.runLater(() -> phone_numberTextField.requestFocus());

            // Обрабатываем результат нажатия кнопки "Добавить"
            dialog.setResultConverter(buttonType -> {
                if (buttonType == addButton) {
                    // Извлекаем данные из текстовых полей
                    String phone_number = phone_numberTextField.getText();
                    String first_name = first_nameTextField.getText();
                    String last_name = last_nameTextField.getText();
                    int age = Integer.parseInt(ageTextField.getText());
                    String job = jobTextField.getText();
                    // Создаем объект Movie с введенными данными
                    Phonebook phonebook = new Phonebook(0, phone_number, first_name, last_name, age, job);

                    // Добавляем фильм в базу данных
                    addPhonebookToDatabase(phonebook);

                    // Возвращаем объект Movie в качестве результата
                    return phonebook;
                }
                return null;
            });

            // Открываем диалоговое окно и ждем результата
            Optional<Phonebook> result = dialog.showAndWait();

            // Обновляем TableView, если был добавлен новый фильм
            result.ifPresent(phonebook -> {
                fx_table.getItems().add(phonebook);
                fx_table.refresh();
            });
        });

        fx_ad2.setOnAction(event -> {
            // Создаем диалоговое окно для ввода данных фильма
            Dialog<Second_phone> dialog = new Dialog<>();
            dialog.setTitle("Додати другий телефон");

            // Устанавливаем кнопку "Добавить" и кнопку "Отмена"
            ButtonType addButton = new ButtonType("Додати", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

            // Создаем форму для ввода данных
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            // Создаем текстовые поля для ввода данных
            TextField phoneTextField = new TextField();
            TextField user_idTextField = new TextField();


            // Добавляем текстовые поля на форму
            grid.add(new Label("Phone:"), 0, 0);
            grid.add( phoneTextField, 1, 0);
            grid.add(new Label("User_id:"), 0, 1);
            grid.add(user_idTextField, 1, 1);

            // Устанавливаем форму на диалоговое окно
            dialog.getDialogPane().setContent(grid);

            // Устанавливаем фокус на поле Title при открытии окна
            Platform.runLater(() -> phoneTextField.requestFocus());

            // Обрабатываем результат нажатия кнопки "Добавить"
            dialog.setResultConverter(buttonType -> {
                if (buttonType == addButton) {
                    // Извлекаем данные из текстовых полей
                    String phone = phoneTextField.getText();
                    int user_id = Integer.parseInt(user_idTextField.getText());

                    // Создаем объект Movie с введенными данными
                    Second_phone second_phone = new Second_phone(phone, user_id);

                    // Добавляем фильм в базу данных
                    addSecond_phoneToDatabase(second_phone);

                    // Возвращаем объект Movie в качестве результата
                    return second_phone;
                }
                return null;
            });

            // Открываем диалоговое окно и ждем результата
            Optional<Second_phone> result = dialog.showAndWait();

            // Обновляем TableView, если был добавлен новый фильм
            result.ifPresent(second_phone -> {
                fx_table.getItems().add(second_phone);
                fx_table.refresh();
            });
        });

        fx_ad3.setOnAction(event -> {
            // Создаем диалоговое окно для ввода данных фильма
            Dialog<Adress> dialog = new Dialog<>();
            dialog.setTitle("Додати адрес");

            // Устанавливаем кнопку "Добавить" и кнопку "Отмена"
            ButtonType addButton = new ButtonType("Додати", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

            // Создаем форму для ввода данных
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            // Создаем текстовые поля для ввода данных
            TextField streetTextField = new TextField();
            TextField houseTextField = new TextField();
            TextField apartmentTextField = new TextField();
            TextField user_idTextField = new TextField();

            // Добавляем текстовые поля на форму
            grid.add(new Label("Street:"), 0, 0);
            grid.add( streetTextField, 1, 0);
            grid.add(new Label("House:"), 0, 1);
            grid.add(houseTextField, 1, 1);
            grid.add(new Label("Apartment:"), 0, 2);
            grid.add(apartmentTextField, 1, 2);
            grid.add(new Label("User_id:"), 0, 3);
            grid.add(user_idTextField, 1, 3);

            // Устанавливаем форму на диалоговое окно
            dialog.getDialogPane().setContent(grid);

            // Устанавливаем фокус на поле Title при открытии окна
            Platform.runLater(() -> streetTextField.requestFocus());

            // Обрабатываем результат нажатия кнопки "Добавить"
            dialog.setResultConverter(buttonType -> {
                if (buttonType == addButton) {
                    // Извлекаем данные из текстовых полей
                    String street = streetTextField.getText();
                    int house = Integer.parseInt(houseTextField.getText());
                    int apartment = Integer.parseInt(apartmentTextField.getText());
                    int user_id = Integer.parseInt(user_idTextField.getText());

                    // Создаем объект Movie с введенными данными
                    Adress adress = new Adress(street, house, apartment, user_id);

                    // Добавляем фильм в базу данных
                    addAdressToDatabase(adress);

                    // Возвращаем объект Movie в качестве результата
                    return adress;
                }
                return null;
            });

            // Открываем диалоговое окно и ждем результата
            Optional<Adress> result = dialog.showAndWait();

            // Обновляем TableView, если был добавлен новый фильм
            result.ifPresent(adress -> {
                fx_table.getItems().add(adress);
                fx_table.refresh();
            });
        });

        fx_del.setOnAction(event -> {
            // Створюємо діалогове вікно для видалення елемента з таблиці
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Видалити елемент з таблиці");

            // Встановлюємо кнопку "Видалити" та кнопку "Скасувати"
            ButtonType deleteButton = new ButtonType("Видалити", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(deleteButton, ButtonType.CANCEL);

            // Створюємо форму для вибору таблиці та введення імені колонки та значення ID
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            // Створюємо ChoiceBox для вибору таблиці
            ChoiceBox<String> tableChoiceBox = new ChoiceBox<>();
            tableChoiceBox.getItems().addAll("phonebook", "second_phone", "adress");
            tableChoiceBox.setValue("phonebook");

            // Створюємо текстове поле для введення імені колонки
            TextField columnNameTextField = new TextField();
            columnNameTextField.setPromptText("Введіть ім'я стовпчика");

            // Створюємо текстове поле для введення значення ID
            TextField idTextField = new TextField();
            idTextField.setPromptText("Введіть значення ID");

            // Додаємо ChoiceBox та текстові поля на форму
            grid.add(new Label("Таблиця:"), 0, 0);
            grid.add(tableChoiceBox, 1, 0);
            grid.add(new Label("Ім'я стовпчику:"), 0, 1);
            grid.add(columnNameTextField, 1, 1);
            grid.add(new Label("Значення ID:"), 0, 2);
            grid.add(idTextField, 1, 2);

            // Встановлюємо форму на діалогове вікно
            dialog.getDialogPane().setContent(grid);

            // Встановлюємо фокус на полі введення імені колонки під час відкриття вікна
            Platform.runLater(() -> columnNameTextField.requestFocus());

            // Обробляємо результат натискання кнопки "Видалити"
            dialog.setResultConverter(buttonType -> {
                if (buttonType == deleteButton) {
                    // Виймаємо вибрану таблицю, ім'я колонки та значення ID з полів уведення
                    String table = tableChoiceBox.getValue();
                    String columnName = columnNameTextField.getText();
                    String id = idTextField.getText();

                    // Викликаємо метод deleteElementFromDatabase() з передачею імені колонки, значення ID та імені таблиці
                    deleteElementFromDatabase(table, columnName, id);



                    // Повертаємо результат діалогу
                    return null;
                }
                return null;
            });

            // Відображаємо діалогове вікно та чекаємо на його закриття
            dialog.showAndWait();
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
    private void addPhonebookToDatabase(Phonebook phonebook) {
        // Подключение к базе данных (предполагается, что у вас уже есть соединение с базой данных)
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ; // Получение соединения с базой данных

        try {
            // Создаем PreparedStatement для выполнения SQL-запроса
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO phonebook (user_id, phone_number, first_name, last_name, age, job) VALUES (?, ?, ?, ?, ?, ?)");

            // Устанавливаем параметры запроса на основе данных фильма
            preparedStatement.setInt(1, phonebook.getUser_id());
            preparedStatement.setString(2, phonebook.getPhone_number());
            preparedStatement.setString(3, phonebook.getFirst_name());
            preparedStatement.setString(4, phonebook.getLast_name());
            preparedStatement.setInt(5, phonebook.getAge());
            preparedStatement.setString(6, phonebook.getJob());
            // Выполняем SQL-запрос
            preparedStatement.executeUpdate();

            // Закрываем PreparedStatement
            preparedStatement.close();

            // Закрываем соединение с базой данных
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addSecond_phoneToDatabase(Second_phone second_phone) {
        // Подключение к базе данных (предполагается, что у вас уже есть соединение с базой данных)
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ; // Получение соединения с базой данных

        try {
            // Создаем PreparedStatement для выполнения SQL-запроса
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO second_phone (phone, user_id) VALUES (?, ?)");

            // Устанавливаем параметры запроса на основе данных фильма

            preparedStatement.setString(1, second_phone.getPhone());
            preparedStatement.setInt(2, second_phone.getUser_id());

            // Выполняем SQL-запрос
            preparedStatement.executeUpdate();

            // Закрываем PreparedStatement
            preparedStatement.close();

            // Закрываем соединение с базой данных
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addAdressToDatabase(Adress adress) {
        // Подключение к базе данных (предполагается, что у вас уже есть соединение с базой данных)
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ; // Получение соединения с базой данных

        try {
            // Создаем PreparedStatement для выполнения SQL-запроса
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO adress (street, house, apartment,user_id) VALUES (?, ?, ?, ?)");

            // Устанавливаем параметры запроса на основе данных фильма

            preparedStatement.setString(1, adress.getStreet());
            preparedStatement.setInt(2, adress.getHouse());
            preparedStatement.setInt(3, adress.getApartment());
            preparedStatement.setInt(4, adress.getUser_id());
            // Выполняем SQL-запрос
            preparedStatement.executeUpdate();

            // Закрываем PreparedStatement
            preparedStatement.close();

            // Закрываем соединение с базой данных
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteElementFromDatabase(String table, String columnName, String id) {
        try {
            //Створюємо підключення до бази даних
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook", "root", "1234");
            // Створюємо SQL-запит для видалення елемента з таблиці
            String sql = "DELETE FROM " + table + " WHERE " + columnName + " = ?";
            // Готуємо SQL-запит
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Встановлюємо значення ID у параметр SQL-запиту
            preparedStatement.setString(1, id);
            // Виконуємо SQL-запит
            preparedStatement.executeUpdate();
            // Закриваємо з'єднання з базою даних та звільняємо ресурси
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}