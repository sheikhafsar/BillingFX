package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;

public class BillTemplateController implements Initializable {
    @FXML
    private Label username;

    @FXML
    private TextField searchTextArea;

    @FXML
    private TextField qtyTextField;

    @FXML
    private TextField customerNameField;

    @FXML
    private Label dateLabel;

    @FXML
    private Label errorOutput;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableView<ProductModel> prodTable;

    @FXML
    private TableColumn<ProductModel, Integer> col_srNo;
    @FXML
    private TableColumn<ProductModel ,String> col_name;
    @FXML
    private TableColumn<ProductModel ,Integer> col_quantity;
 //   @FXML
 //   private TableColumn<ProductModel ,String> col_barcode;
    @FXML
    private TableColumn<ProductModel ,Double> col_price;

    @FXML
    private TableColumn<ProductModel ,Double> col_total;

    @FXML
    private Label grandTotal;

    @FXML
    private Button billGenerateBtn;


    @FXML
    private Button addProdButton;

    @FXML
    private Button deleteProdBtn;

    ObservableList<ProductModel> oblist = FXCollections.observableArrayList();

    //TableView.TableViewSelectionModel selectionModel = prodTable.getSelectionModel();

    DBHandler db;
    Connection con=null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    double sum=0.0;
    Boolean prodExistInBill=false;
    int rowIndex=0;
    int billId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Dash LoggedIn user:"+ UserSession.loggedInUser.getUserName());
        String usernamestr = UserSession.loggedInUser.getUserName();
        System.out.println("username:"+usernamestr);
        username.setText(usernamestr);

        prodTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                searchTextArea.setText(prodTable.getSelectionModel().getSelectedItem().getBarcode());
                qtyTextField.setText(String.valueOf(prodTable.getSelectionModel().getSelectedItem().getQuantity()));
            }
        });

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        System.out.println("date:"+dtf.format(now));
        dateLabel.setText(dtf.format(now));

        //add button enable
        BooleanBinding booleanBind = searchTextArea.textProperty().isEmpty()
                .or(qtyTextField.textProperty().isEmpty());
        System.out.println("booleanBind:"+booleanBind);
        addProdButton.disableProperty().bind(booleanBind);

        //delete button enable
      //  BooleanBinding booleanBind1 = new BooleanBinding(prodTable.getSelectionModel().isEmpty());

        deleteProdBtn.disableProperty().bind(Bindings.isEmpty(prodTable.getSelectionModel().getSelectedItems()));

        BooleanBinding booleanBind1 = customerNameField.textProperty().isEmpty().or(Bindings.isNotEmpty(prodTable.getItems()));
        billGenerateBtn.disableProperty().bind(booleanBind1);
    }

    public void logoutHandler(ActionEvent actionEvent) throws IOException {
        UserSession.setInstance(null);
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
                if(Integer.parseInt(qtyTextField.getText())<=rs.getInt("quantity")){

                    //check product existence in bill
                    rowIndex=0;
                    for(ProductModel prod: oblist){
                        if(prod.getBarcode().equals(rs.getString("barcode"))){
                            System.out.println("found");
                            prodExistInBill=true;
                            prod.setQuantity(Integer.parseInt(qtyTextField.getText()));
                            prod.setTotal(prod.getPrice()*prod.getQuantity());
                            oblist.set(rowIndex,prod);
                            break;
                        }
                        else {
                            prodExistInBill=false;
                        }
                        rowIndex++;
                    }

                    if(!prodExistInBill){
                        oblist.add(new ProductModel(UUID.fromString(rs.getString("id")),rs.getString("name"),rs.getString("barcode"),Integer.parseInt(qtyTextField.getText()),rs.getDouble("price"),rs.getDouble("price")*Double.parseDouble(qtyTextField.getText()),rs.getInt("quantity")));
                    }


                    //col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    col_srNo.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(prodTable.getItems().indexOf(column.getValue())+1));
                    col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    //col_barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
                    col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
                    col_total.setCellValueFactory(new PropertyValueFactory<>("total"));

                    prodTable.setItems(oblist);

                    //clear textfields
                    searchTextArea.clear();
                    qtyTextField.clear();


                    //Compute grand Total Amount to be paid.
                    sum=0.0;
                    for(ProductModel prod: oblist){
                        System.out.println("prod.getTotal:"+prod.getTotal());
                        sum=sum+prod.getTotal();
                    }
                    grandTotal.setText(String.valueOf(sum));
                    errorOutput.setText("");
                }
                else{
                    if(rs.getInt("quantity")>0)
                        errorOutput.setText("Only "+rs.getInt("quantity")+" left");
                    else
                        errorOutput.setText("Out of Stock");
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

      /*  if (oblist.size()!=0){

        } */

    }



    public void billGenerateHandler(ActionEvent actionEvent) throws SQLException {
        System.out.println("date:"+dateLabel.getText());
        System.out.println("customer:"+customerNameField.getText());
        System.out.println("emp_id"+UserSession.loggedInUser.getUserId());
        System.out.println("amount"+grandTotal.getText());

        //insert in bill table
        db = DBHandler.getInstance();

        try{
            con=db.getConnection();
            System.out.println("con:"+con);
            ps=con.prepareStatement("insert into bill(date,customer_name,emp_id,amount) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dateLabel.getText());
            ps.setString(2, customerNameField.getText());
            ps.setString(3, UserSession.loggedInUser.getUserId().toString());
            ps.setString(4, grandTotal.getText());
            ps.executeUpdate();

            rs=ps.getGeneratedKeys();

            if (rs.next()){
                billId=rs.getInt(1);
            }

        }catch (Exception e){ e.printStackTrace(); } finally{
            if (ps!=null){
                ps.close();
            }if(con!=null){
                con.close();
            }
        }

        //insert into bill_prod table
        for(ProductModel prod: oblist){
            System.out.println("billId: "+billId+" prodId:"+prod.getId()+" prodQty:"+prod.getQuantity());

            try{
                con=db.getConnection();
                ps=con.prepareStatement("insert into bill_prod(bill_id,prod_id,quantity) values(?,?,?)");
                ps.setInt(1, billId);
                ps.setString(2, prod.getId().toString());
                ps.setInt(3, prod.getQuantity());
                ps.executeUpdate();

                /*rs=ps.getGeneratedKeys();
                if (rs.next()){
                    billId=rs.getInt(1);
                }*/

            }catch (Exception e){
                e.printStackTrace();
            }
            finally{
                if (ps!=null){
                    ps.close();
                }if(con!=null){
                    con.close();
                }
            }

            //update product quantity in database
            try{
                con=db.getConnection();
                ps=con.prepareStatement("update product set quantity = ? where barcode = ?");
                ps.setInt(1, prod.getCount() - prod.getQuantity());
                ps.setString(2, prod.getBarcode());
                ps.executeUpdate();

                /*rs=ps.getGeneratedKeys();
                if (rs.next()){
                    billId=rs.getInt(1);
                }*/

            }catch (Exception e){
                e.printStackTrace();
            }
            finally{
                if (ps!=null){
                    ps.close();
                }if(con!=null){
                    con.close();
                }
            }
        }
        customerNameField.clear();
        oblist.clear();
        grandTotal.setText("");
        searchTextArea.clear();
        qtyTextField.clear();
    }

    public void deleteProdHandler(ActionEvent actionEvent) {
        System.out.println("sum(Delete)"+sum);
        System.out.println("prodTotal(Delete)"+prodTable.getSelectionModel().getSelectedItem().getTotal());
        grandTotal.setText(String.valueOf(Double.parseDouble(grandTotal.getText())-prodTable.getSelectionModel().getSelectedItem().getTotal()));
        System.out.println("selected :"+prodTable.getSelectionModel().isEmpty());
        prodTable.getItems().removeAll(prodTable.getSelectionModel().getSelectedItem());

    }
}
