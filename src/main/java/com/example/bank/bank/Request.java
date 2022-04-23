package com.example.bank.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Request {

    ArrayList<String> account = new ArrayList<>();
    ArrayList<Integer> balance = new ArrayList<>();
    ArrayList<Integer> amount = new ArrayList<>();

    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private Label lb01;
    @FXML
    private Label lb02;
    @FXML
    private Label lb03;
    @FXML
    private Label lb04;
    @FXML
    private Button bt;


    @FXML
    public void initialize(){

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM user;";

        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                if (rs.getInt(5)==1){
                    account.add(rs.getString(1));
                    balance.add(rs.getInt(4));
                    amount.add(rs.getInt(6));
                }
            }
            if (account.isEmpty()){
                lb02.setText("No Request(s)");
                myChoiceBox.isDisable();
                bt.isDisable();
            }

            myChoiceBox.getItems().addAll(account);
            myChoiceBox.setOnAction(this::getFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getFood(ActionEvent actionEvent) {
        String acc = myChoiceBox.getValue();
        for (int i = 0; i<account.size(); i++){
            if (account.get(i)==acc){
                lb01.setText(String.valueOf(amount.get(i)));
                lb03.setText(String.valueOf(balance.get(i)));
            }
        }
    }

    @FXML
    protected void Deposit(ActionEvent actionEvent) throws IOException, SQLException {
        if (account.isEmpty())
            return;
        String acc = myChoiceBox.getValue();
        int bl = Integer.parseInt(lb03.getText());
        int am = Integer.parseInt(lb01.getText());
        int var = bl+am;

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "UPDATE `bank`.`user` SET `balance` = '"+var+"', `req` = '"+0+"', `amount` = '"+0+"' WHERE (`account` = '"+acc+"');";

        statement.executeUpdate(q);

        lb04.setText("Deposited");

        myChoiceBox.getItems().clear();
        account.clear();
        amount.clear();
        balance.clear();
        lb01.setText("");
        lb03.setText("");
        initialize();

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
