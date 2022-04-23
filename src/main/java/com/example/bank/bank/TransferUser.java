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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.bank.bank.Login.*;

public class TransferUser {

    ArrayList<String> account = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> balanceA = new ArrayList<>();

    String nameS;
    String balanceS;
    String accountS;

    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private Label lb01;
    @FXML
    private Label lb02;
    @FXML
    private Label lb03;
    @FXML
    private TextField tx;

    @FXML
    public void initialize(){

        lb02.setText("Available Balance: "+balance);

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM user;";

        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                if (!Objects.equals(rs.getString(1), accountId)){
                    account.add(rs.getString(1));
                    name.add(rs.getString(2));
                    balanceA.add(rs.getInt(4));
                }
            }

            myChoiceBox.getItems().addAll(account);
            System.out.println(account);
            myChoiceBox.setOnAction(this::getFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void Deposit(ActionEvent actionEvent) throws IOException, SQLException {
        if (account.isEmpty())
            return;

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        int a = 0;
        int sent = 0;
        int got = 0;
        boolean acb01 = false;
        if (tx.getText().trim().isEmpty()){
            lb03.setText("Blank");
            acb01 = false;
        }
        else if (!isNumeric(tx.getText())){
            lb03.setText("Not Number");
            acb01 = false;
        }
        else if (Integer.parseInt(tx.getText())>balance){
            lb03.setText("No enough Balance");
            acb01 = false;
        }
        else{
            a = Integer.parseInt(tx.getText());
            lb03.setText("");
            acb01 = true;
        }
        if (acb01){
            sent = Integer.parseInt(balanceS)+a;
            got = balance-a;

            String q1 = "UPDATE `bank`.`user` SET `balance` = '"+sent+"' WHERE (`account` = '"+accountS+"');";
            String q2 = "UPDATE `bank`.`user` SET `balance` = '"+got+"' WHERE (`account` = '"+accountId+"');";
            String q3 = "INSERT INTO `bank`.`transfer` (`sender`, `receiver`, `amount`) VALUES ('"+accountId+"', '"+accountS+"', '"+a+"');";
            statement.executeUpdate(q1);
            statement.executeUpdate(q2);
            statement.executeUpdate(q3);

            lb03.setText("Money Send");
            balance = got;
            tx.setText("");
            account.clear();;
            balanceA.clear();
            name.clear();
            initialize();

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

    private void getFood(ActionEvent actionEvent) {
        String acc = myChoiceBox.getValue();
        for (int i = 0; i<account.size(); i++){
            if (Objects.equals(acc, account.get(i))){
                lb01.setText(name.get(i));
                accountS = acc;
                balanceS = String.valueOf(balanceA.get(i));
                nameS = name.get(i);
            }
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
}
