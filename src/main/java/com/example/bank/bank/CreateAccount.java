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

public class CreateAccount {

    public TextField accountId;
    public TextField name;
    public TextField password;
    public TextField balance;

    public Label lb01;
    public Label lb02;
    public Label lb03;
    public Label lb04;
    public Label lb05;


    @FXML
    protected void Create(ActionEvent actionEvent) throws IOException, SQLException {
        String acc01="", acc02="", acc03="", acc04="";
        boolean acb01 = false,  acb02 = false, acb03 = false, acb04 = false;

        if (accountId.getText().trim().isEmpty()){
            lb01.setText("Blank");
            acb01 = false;
        }
        else if (!isNumeric(accountId.getText())){
            lb01.setText("Not Number");
            acb01 = false;
        }
        else if (accountId.getText().length()<6){
            lb01.setText("Too short");
            acb01 = false;
        }
        else{
            acc01 = accountId.getText();
            lb01.setText("");
            acb01 = true;
        }

        if (name.getText().trim().isEmpty()){
            lb02.setText("Blank");
            acb02 = false;
        }
        else{
            acc02 = name.getText();
            lb02.setText("");
            acb02 = true;
        }

        if (password.getText().trim().isEmpty()){
            lb03.setText("Blank");
            acb03 = false;
        }
        else if (password.getText().length()<6){
            lb03.setText("must be 6 digit");
            acb03 = false;
        }
        else{
            acc03 = password.getText();
            lb03.setText("");
            acb03 = true;
        }

        if (balance.getText().trim().isEmpty()){
            lb04.setText("Blank");
            acb04 = false;
        }
        else if (!isNumeric(balance.getText())){
            lb04.setText("Not Number");
            acb04 = false;
        }
        else{
            acc04 = balance.getText();
            lb04.setText("");
            acb04 = true;
        }
        if (acb01 && acb02 && acb03 && acb04) {
            System.out.println(acc01 + acc02 + acc03 + acc04);

            DatabaseConnection con = new DatabaseConnection();
            Connection conBD = con.getConnection();
            Statement statement = conBD.createStatement();

            String q = "INSERT INTO `bank`.`user` (`account`, `name`, `password`, `balance`, `req`, `amount`) VALUES ('" + acc01 + "', '" + acc02 + "', '" + acc03 + "', '" + acc04 + "', '0', '0');";
            statement.executeUpdate(q);
            lb05.setText("User Created");
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @FXML
    protected void Back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin-dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
