package controller;

import com.sun.org.omg.CORBA.Initializer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.DBHandler;
import model.ProductModel;

import java.net.URL;
import java.sql.*;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.UUID;

public class ProductController implements Initializable {
    @FXML
    private TableView<ProductModel> prodTable;
    @FXML
    private TableColumn<ProductModel, UUID> col_prodId;
    @FXML
    private TableColumn<ProductModel, String> col_prodName;
    @FXML
    private TableColumn<ProductModel, String> col_prodBarcode;
    @FXML
    private TableColumn<ProductModel, Integer> col_prodCount;
    @FXML
    private TableColumn<ProductModel, Double> col_prodPrice;

    @FXML
    private TextField prodIDText;
    @FXML
    private TextField prodNameText;
    @FXML
    private TextField prodBarCodeText;
    @FXML
    private TextField prodQtyText;
    @FXML
    private TextField prodPriceText;
    @FXML
    private Label ErrorOutput;
    @FXML
    private Button prodAddBtn;
    @FXML
    private Button prodUpdateBtn;
    @FXML
    private Button prodDeleteBtn;

    ObservableList<ProductModel> oblist = FXCollections.observableArrayList();
    Connection con;
    ResultSet rs;
    PreparedStatement ps = null;

    ProductModel product = new ProductModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            con = DBHandler.getConnection();
            rs = con.createStatement().executeQuery("select * from product");

            while (rs.next()) {
                //System.out.println("id: " + rs.getString("id"));
                oblist.add(new ProductModel(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("barcode"),rs.getDouble("price"),rs.getInt("quantity")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_prodId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_prodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_prodBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        col_prodCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        col_prodPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        prodTable.setItems(oblist);

        prodTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                product = prodTable.getSelectionModel().getSelectedItem();
                prodIDText.setText(product.getId().toString());
                prodNameText.setText(product.getName());
                prodBarCodeText.setText(product.getBarcode());
                prodQtyText.setText(String.valueOf(product.getCount()));
                prodPriceText.setText(String.valueOf(product.getPrice()));
            }
        });

        BooleanBinding booleanBind = prodNameText.textProperty().isEmpty()
                .or(prodBarCodeText.textProperty().isEmpty())
                .or(prodQtyText.textProperty().isEmpty())
                .or(prodPriceText.textProperty().isEmpty());

        prodAddBtn.disableProperty().bind(booleanBind);
        prodUpdateBtn.disableProperty().bind(Bindings.isEmpty(prodTable.getSelectionModel().getSelectedItems()));
        prodDeleteBtn.disableProperty().bind(Bindings.isEmpty(prodTable.getSelectionModel().getSelectedItems()));
    }

    public void prodAddHandler(ActionEvent actionEvent) throws SQLException {

        boolean duplicate = false;

        ErrorOutput.setText("");

        for(ProductModel product:oblist){

            if (product.getBarcode().equals(prodBarCodeText.getText()) ) {
                ErrorOutput.setText("Duplicate Value!");
                duplicate = true;
                break;
            } else {
                duplicate = false;
            }
        }

        if (!duplicate) {
            //insert new employee
            System.out.println("No Duplicate");

            UUID temp_id = UUID.randomUUID();

            ProductModel product = new ProductModel(temp_id,prodNameText.getText(),prodBarCodeText.getText(),Double.parseDouble(prodPriceText.getText()),Integer.parseInt(prodQtyText.getText()));

            oblist.add(product);
            prodTable.setItems(oblist);

            try {
                String query = "insert into product(id,name,barcode,quantity,price) values (?,?,?,?,?)";
                ps = con.prepareStatement(query);

                ps.setString(1, product.getId().toString());
                //System.out.println(prodIDText.getText());
                ps.setString(2, product.getName());
                //System.out.println(prodNameText.getText());
                ps.setString(3, product.getBarcode());
               // System.out.println(prodBarCodeText.getText());
                ps.setInt(4, product.getCount());
                //System.out.println(prodQtyText.getText());
                ps.setDouble(5,product.getPrice());
                //System.out.println(prodPriceText.getText());
                ps.executeUpdate();

                ClearTextFields();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void prodUpdateHandler(ActionEvent actionEvent) throws SQLException {

        boolean duplicate = false;

        ErrorOutput.setText("");

        for(ProductModel product:oblist){

            if(!product.getId().equals(UUID.fromString(prodIDText.getText()))){
                if (product.getBarcode().equals(prodBarCodeText.getText()) ) {
                    ErrorOutput.setText("Duplicate Value!");
                    duplicate = true;
                    break;
                } else {
                    duplicate = false;
                }
            }

        }

        if (!duplicate) {
            System.out.println("No Duplicate");
            int rowIndex = 0;
            for(ProductModel product: oblist){
                if(product.getId().equals(UUID.fromString(prodIDText.getText()))){
                    //employeeExistInTableView = true;
                    product.setName(prodNameText.getText());
                    product.setBarcode(prodBarCodeText.getText());
                    product.setCount(Integer.parseInt(prodQtyText.getText()));
                    product.setPrice(Double.parseDouble(prodPriceText.getText()));
                    oblist.set(rowIndex,product);

                    try {
                        String query = "update product set name=?, barcode=?, quantity=?, price=? where id=?";
                        ps = con.prepareStatement(query);

                        ps.setString(1, prodNameText.getText());
                        ps.setString(2, prodBarCodeText.getText());
                        ps.setInt(3, Integer.parseInt(prodQtyText.getText()));
                        ps.setDouble(4, Double.parseDouble(prodPriceText.getText()));
                        ps.setString(5, prodIDText.getText());
                        ps.executeUpdate();

                        ClearTextFields();
                    } catch (BatchUpdateException b) {

                    }
                    break;
                }
                rowIndex++;
            }
        }
    }

    public void prodDeleteHandler(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        con = DBHandler.getConnection();
        int selectedIndex = prodTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            String query1 = "DELETE FROM product WHERE id = ?";
            ps = con.prepareStatement(query1);
            String temp = col_prodId.getCellData(selectedIndex).toString();
            ps.setString(1, temp);
            System.out.println("Col_id :" +col_prodId.getCellData(selectedIndex));
            ps.executeUpdate();
            clearColumn();
            ClearTextFields();
        }
    }

    public void clearColumn(){
        prodTable.getItems().removeAll(
                prodTable.getSelectionModel().getSelectedItems()
        );
    }


    private void ClearTextFields() {
        prodIDText.clear();
        prodNameText.clear();
        prodBarCodeText.clear();
        prodQtyText.clear();
        prodPriceText.clear();
    }
}

