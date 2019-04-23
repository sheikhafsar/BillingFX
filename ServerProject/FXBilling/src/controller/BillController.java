package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BillModel;
import model.DBHandler;
import model.ProductModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

public class BillController implements Initializable {
    @FXML
    private TableView<BillModel> billTable;
    @FXML
    private TableColumn<BillModel, Integer> col_billId;
    @FXML
    private TableColumn<BillModel, String> col_date;
    @FXML
    private TableColumn<BillModel, String> col_customerName;
    @FXML
    private TableColumn<BillModel, Double> col_amount;
    @FXML
    private TableColumn<BillModel, String> col_employee;

    ObservableList<BillModel> oblist = FXCollections.observableArrayList();
    Connection con;
    ResultSet rs;
    PreparedStatement ps = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            con = DBHandler.getConnection();
            rs = con.createStatement().executeQuery("SELECT bill_id,customer_name,date,amount,username FROM bill,employee WHERE bill.emp_id=employee.id");

            while (rs.next()) {
                /*
                System.out.println(rs.getInt("bill_id"));
                System.out.println(rs.getString("date"));
                System.out.println(rs.getString("customer_name"));
                System.out.println(rs.getDouble("amount"));
                System.out.println(rs.getString("username"));
                */

                oblist.add(new BillModel(rs.getInt("bill_id"),rs.getString("date"), rs.getString("customer_name"),rs.getDouble("amount"),rs.getString("username")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        col_billId.setCellValueFactory(new PropertyValueFactory<>("bill_id"));
       col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
       col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_employee.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

        billTable.setItems(oblist);

    }
}
