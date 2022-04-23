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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDashboard {

    public Label lb01;
    public Label lb02;
    public Label lb03;

    public static int totalAccounts=0;
    public static int totalDeposit=0;
    public static int totalReq=0;

    @FXML
    public void initialize(){

        DatabaseConnection con = new DatabaseConnection();

        Connection conBD = con.getConnection();

        String q = "SELECT * FROM user;";

        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                totalAccounts++;
                totalDeposit+=rs.getInt(4);
                if (rs.getInt(5)==1)
                    totalReq+=rs.getInt(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        lb01.setText(String.valueOf(totalAccounts));
        lb02.setText(String.valueOf(totalDeposit));
        lb03.setText(String.valueOf(totalReq));
    }

    @FXML
    protected void Details(ActionEvent actionEvent) throws IOException {
        totalAccounts=0;
        totalDeposit=0;
        totalReq=0;
        Parent root = FXMLLoader.load(getClass().getResource("details.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Create(ActionEvent actionEvent) throws IOException {
        totalAccounts=0;
        totalDeposit=0;
        totalReq=0;
        Parent root = FXMLLoader.load(getClass().getResource("create-account.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Request(ActionEvent actionEvent) throws IOException {
        totalAccounts=0;
        totalDeposit=0;
        totalReq=0;
        Parent root = FXMLLoader.load(getClass().getResource("request.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Transfer(ActionEvent actionEvent) throws IOException {
        totalAccounts=0;
        totalDeposit=0;
        totalReq=0;
        Parent root = FXMLLoader.load(getClass().getResource("transfer.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginAD-view.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
