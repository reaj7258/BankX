package com.example.bank.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.bank.bank.Login.*;

public class Deposit {

    @FXML
    public Label lb01;
    @FXML
    public Label lb02;
    @FXML
    public TextField tx;

    @FXML
    public void initialize(){
        if (req)
            lb01.setText("Deposit Pending: "+amount);
    }

    @FXML
    protected void Deposit(ActionEvent actionEvent) throws IOException, SQLException {
        int a = 0;
        boolean acb01 = false;
        if (tx.getText().trim().isEmpty()){
            lb02.setText("Blank");
            acb01 = false;
        }
        else if (!isNumeric(tx.getText())){
            lb02.setText("Not Number");
            acb01 = false;
        }
        else{
            a = Integer.parseInt(tx.getText());
            lb02.setText("");
            acb01 = true;
            a = a+amount;
            amount = a;
        }
        if (acb01){

            DatabaseConnection con = new DatabaseConnection();
            Connection conBD = con.getConnection();
            Statement statement = conBD.createStatement();

            String q = "UPDATE `bank`.`user` SET `req` = '1', `amount` = '"+a+"' WHERE (`account` = '"+accountId+"');";

            statement.executeUpdate(q);
            lb02.setText("Request");
            lb01.setText("Deposit Pending: "+amount);
            tx.setText("");
        }

    }


    @FXML
    protected void Back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
