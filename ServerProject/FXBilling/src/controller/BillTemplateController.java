package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BillTemplateController implements Initializable {
    @FXML
    private Label username;

    @FXML
    private TextField searchTextArea;

    @FXML
    private TextField qtyTextField;

    @FXML
    private Label errorOutput;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableView<ProductModel> prodTable;

    @FXML
    private TableColumn<ProductModel,String> col_id;
    @FXML
    private TableColumn<ProductModel ,String> col_name;
    @FXML
    private TableColumn<ProductModel ,String> col_quantity;
 //   @FXML
 //   private TableColumn<ProductModel ,String> col_barcode;
    @FXML
    private TableColumn<ProductModel ,String> col_price;

    ObservableList<ProductModel> oblist = FXCollections.observableArrayList();



    DBHandler db;
    Connection con=null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Dash LoggedIn user:"+ UserSession.loggedInUser.getUserName());
        String usernamestr = UserSession.loggedInUser.getUserName();
        System.out.println("username:"+usernamestr);
        username.setText(usernamestr);
    }

    public void logoutHandler(ActionEvent actionEvent) throws IOException {
        UserSession.loggedInUser = null;
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Scene scene =  new Scene(root, 1280 ,800);
        stage.setScene(scene);
        stage.show();
    }

    public void addProdHandler(ActionEvent actionEvent) throws SQLException {
        db = DBHandler.getInstance();

        try{
            con=db.getConnection();
            System.out.println("con:"+con);
            ps=con.prepareStatement("select * from product where barcode =?");
            ps.setString(1, searchTextArea.getText());
            //ps.setString(2, qtyTextField.getText());
            rs=ps.executeQuery();

            if (rs.next()){
                //found
                System.out.println("prod code: "+rs.getString("barcode"));

                //check availability
                if(Integer.parseInt(qtyTextField.getText())<=Integer.parseInt(rs.getString("quantity"))){
                    errorOutput.setText(rs.getString("barcode")+"available");

                    oblist.add(new ProductModel(rs.getString("id"),rs.getString("name"),rs.getString("barcode"),rs.getString("quantity"),rs.getString("price")));

                    col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    //col_barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
                    col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

                    prodTable.setItems(oblist);
                }
                else{
                    errorOutput.setText(rs.getString("barcode")+" found but qty is"+rs.getString("quantity"));
                }
            }
            else{
                //System.out.println("not found");
                errorOutput.setText(searchTextArea.getText()+"not found");
            }
        }catch (Exception e){ e.printStackTrace(); } finally{
            if (ps!=null){
                ps.close();
            }if(con!=null){
                con.close();
            }
        }

    }
}
