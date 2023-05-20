module com.example.lmv_lr5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kursova to javafx.fxml;
    exports com.example.kursova;
}