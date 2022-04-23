package com.example.bank.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.bank.bank.Login.*;

public class Dashboard {

    @FXML
    public Label lb01;
    @FXML
    public Label lb02;
    @FXML
    public Label lb03;
    @FXML
    public Label lb04;


    @FXML
    public void initialize(){
        lb01.setText(accountId);
        lb02.setText(name);
        lb03.setText(String.valueOf(balance));
        if (req)
            lb04.setText("Deposit Pending");
        else lb04.setText("No Pending(s)");
    }

    @FXML
    protected void Transfer(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("transfer-user.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Deposit(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("deposit.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
