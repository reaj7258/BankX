module com.example.bank.bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bank.bank to javafx.fxml;
    exports com.example.bank.bank;
}