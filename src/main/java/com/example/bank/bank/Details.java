package com.example.bank.bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Details implements Initializable {


    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User,String> accountTableColumn;
    @FXML
    private TableColumn<User,String> nameTableColumn;
    @FXML
    private TableColumn<User,Integer> balanceTableColumn;
    @FXML
    private TableColumn<User,Boolean> reqTableColumn;
    @FXML
    private TableColumn<User,Integer> amountTableColumn;

    ObservableList<User> userObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM user;";

        try {
            Statement statement = conBD.createStatement();
            ResultSet rs = statement.executeQuery(q);

            while (rs.next()){
                String qAccountId = rs.getString("account");
                String qName = rs.getString("name");
                int qBalance = rs.getInt("balance");
                boolean qReq = rs.getBoolean("req");
                int qAmount = rs.getInt("amount");

                userObservableList.add(new User(qAccountId,qName,qBalance,qReq,qAmount));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        accountTableColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        balanceTableColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        reqTableColumn.setCellValueFactory(new PropertyValueFactory<>("req"));
        amountTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        userTableView.setItems(userObservableList);
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
