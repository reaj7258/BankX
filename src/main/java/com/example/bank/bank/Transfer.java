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

public class Transfer implements Initializable {
    @FXML
    private TableView<TransferLog> transferTableView;
    @FXML
    private TableColumn<TransferLog,String> senderTableColumn;
    @FXML
    private TableColumn<TransferLog,String> receiverTableColumn;
    @FXML
    private TableColumn<TransferLog,Integer> amountTableColumn;

    ObservableList<TransferLog> transferObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM transfer;";

        try {
            Statement statement = conBD.createStatement();
            ResultSet rs = statement.executeQuery(q);

            while (rs.next()){
                String sender = rs.getString("sender");
                String receiver = rs.getString("receiver");
                int amount = rs.getInt("amount");

                transferObservableList.add(new TransferLog(sender,receiver,amount));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        senderTableColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        receiverTableColumn.setCellValueFactory(new PropertyValueFactory<>("receiver"));
        amountTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        transferTableView.setItems(transferObservableList);
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
