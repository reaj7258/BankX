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
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginADView {

    public TextField userID;
    public TextField password;
    public Label label;


    @FXML
    protected void logIn(ActionEvent e) throws IOException {

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String id = userID.getText();
        String pass = password.getText();

        String q = "SELECT * FROM admin;";

        boolean logged = false;
        try {
            Statement s = conBD.createStatement();

            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                if (id.equals(rs.getString(1))&&pass.equals(rs.getString(2))) {

                    System.out.println(id+" logged in ");
                    logged = true;
                    label.setText("");
                    label.setText("");
                    Parent root = FXMLLoader.load(getClass().getResource("admin-dashboard.fxml"));
                    Scene scene = new Scene(root);
                    Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    break;
                }
            }
            if (!logged) {
                label.setText("Wrong ID or Password");
                password.setText("");
            }

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
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
