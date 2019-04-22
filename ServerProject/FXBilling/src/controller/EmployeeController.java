package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DBHandler;
import model.EmployeeModel;


public class EmployeeController implements Initializable {
    @FXML
    private TableView<EmployeeModel> table;
    @FXML
    private TableColumn<EmployeeModel,String> col_id;
    @FXML
    private TableColumn<EmployeeModel,String> col_name;
    @FXML
    private TableColumn<EmployeeModel,String> col_email;
    @FXML
    private TableColumn<EmployeeModel,String> col_phone;
    @FXML
    private TableColumn<EmployeeModel,String> col_address;


    ObservableList<EmployeeModel> oblist = FXCollections.observableArrayList();

    //ObservableList<EmployeeModel>

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection con = DBHandler.getConnection();
            ResultSet rs=con.createStatement().executeQuery("select * from employee");

            while (rs.next()){
                System.out.println("id: "+rs.getString("id"));
                oblist.add(new EmployeeModel(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getString("phone"),rs.getString("address")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.setItems(oblist);
    }

    public void RemoveEmployee() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        Connection con = DBHandler.getConnection();
        int selectedIndex = table.getSelectionModel().getSelectedIndex();

        if(selectedIndex >= 0){
            String query1 = "DELETE FROM employee WHERE id = ?";
            System.out.println(query1);
            ps = con.prepareStatement(query1);
            ps.setString(1, col_id.getCellData(selectedIndex));
            ps.executeUpdate();
            clearColumn();
        }
    }

    public void clearColumn() throws SQLException, ClassNotFoundException {
        table.getItems().removeAll(
                table.getSelectionModel().getSelectedItems()
        );
    }
}
