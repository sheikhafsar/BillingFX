package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.DBHandler;
import model.EmployeeModel;

import java.util.UUID;

public class EmployeeController implements Initializable {
    @FXML
    private TableView<EmployeeModel> table;
    @FXML
    private TableColumn<EmployeeModel, UUID> col_id;
    @FXML
    private TableColumn<EmployeeModel, String> col_username;
    @FXML
    private TableColumn<EmployeeModel, String> col_pwd;
    @FXML
    private TableColumn<EmployeeModel, String> col_firstName;
    @FXML
    private TableColumn<EmployeeModel, String> col_lastName;
    @FXML
    private TableColumn<EmployeeModel, String> col_email;
    @FXML
    private TableColumn<EmployeeModel, String> col_phone;
    @FXML
    private TableColumn<EmployeeModel, String> col_address;

    @FXML
    private TextField empIDText;
    @FXML
    private TextField empUsernameText;
    @FXML
    private TextField empPassText;
    @FXML
    private TextField empFNText;
    @FXML
    private TextField empLNText;
    @FXML
    private TextField empEmailText;
    @FXML
    private TextField empPhoneText;
    @FXML
    private TextField empAddressText;
    @FXML
    private Label ErrorOutput;

    @FXML
    Button AddEmpBtn;
    @FXML
    Button empDeleteBtn;
    @FXML
    Button empUpdateBtn;

    ObservableList<EmployeeModel> oblist = FXCollections.observableArrayList();
    boolean employeeExistInTableView = false;
    Connection con;
    ResultSet rs;
    PreparedStatement ps = null;
    EmployeeModel employee = new EmployeeModel();
    //int emp_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            con = DBHandler.getConnection();
            rs = con.createStatement().executeQuery("select * from employee");

            while (rs.next()) {
                oblist.add(new EmployeeModel(UUID.fromString(rs.getString("id")), rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("phone"), rs.getString("address")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_pwd.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.setItems(oblist);

        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                employee = table.getSelectionModel().getSelectedItem();
                empIDText.setText(employee.getId().toString());
                empUsernameText.setText(employee.getUsername());
                empPassText.setText(employee.getPassword());
                empFNText.setText(employee.getFirstname());
                empLNText.setText(employee.getLastname());
                empPhoneText.setText(employee.getPhone());
                empEmailText.setText(employee.getEmail());
                empAddressText.setText(employee.getAddress());
            }
        });

        BooleanBinding booleanBind = empUsernameText.textProperty().isEmpty()
                .or(empPassText.textProperty().isEmpty()).or(empFNText.textProperty().isEmpty())
                .or(empLNText.textProperty().isEmpty()).or(empPhoneText.textProperty().isEmpty())
                .or(empEmailText.textProperty().isEmpty()).or(empAddressText.textProperty().isEmpty());

        AddEmpBtn.disableProperty().bind(booleanBind);
        empDeleteBtn.disableProperty().bind(Bindings.isEmpty(table.getSelectionModel().getSelectedItems()));
        empUpdateBtn.disableProperty().bind(Bindings.isEmpty(table.getSelectionModel().getSelectedItems()));
    }

    public void RemoveEmployee() throws SQLException, ClassNotFoundException {
        con = DBHandler.getConnection();
        int selectedIndex = table.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            String query1 = "DELETE FROM employee WHERE id = ?";
            ps = con.prepareStatement(query1);
            String temp = col_id.getCellData(selectedIndex).toString();
            ps.setString(1, temp);
            System.out.println("Col_id :" +col_id.getCellData(selectedIndex));
            ps.executeUpdate();
            clearColumn();
            ClearTextFields();
        }
    }

    public void clearColumn() throws SQLException, ClassNotFoundException {
        table.getItems().removeAll(
                table.getSelectionModel().getSelectedItems()
        );
    }

    public void AddEmployeeHandler(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        boolean duplicate = false;

        ErrorOutput.setText("");

        for(EmployeeModel employee:oblist){

                if (employee.getUsername().equals(empUsernameText.getText()) || employee.getPassword().equals(empPassText.getText()) || employee.getPhone().equals(empPhoneText.getText()) || employee.getEmail().equals(empEmailText.getText())) {
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

            EmployeeModel employee = new EmployeeModel(temp_id, empUsernameText.getText(), empPassText.getText(), empFNText.getText(), empLNText.getText(), empEmailText.getText(), empPhoneText.getText(), empAddressText.getText());
            //table.getItems().add(employee);
            oblist.add(employee);
            table.setItems(oblist);


            try {
                String query = "insert into employee(id,userName,password,firstname, lastname,phone,email,address) values (?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(query);

                ps.setString(1, employee.getId().toString());
                System.out.println(employee.getId().toString());
                ps.setString(2, employee.getUsername());
                System.out.println(employee.getUsername());
                ps.setString(3, employee.getPassword());
                System.out.println(employee.getPassword());
                ps.setString(4, employee.getFirstname());
                System.out.println(employee.getFirstname());
                ps.setString(5, employee.getLastname());
                System.out.println(employee.getLastname());
                ps.setString(6, employee.getPhone());
                System.out.println(employee.getPhone());
                ps.setString(7, employee.getEmail());
                System.out.println(employee.getEmail());
                ps.setString(8, employee.getAddress());
                System.out.println(employee.getAddress());

                ps.executeUpdate();

                ClearTextFields();

            } catch (BatchUpdateException b) {
                System.out.println(b);
            }
        }
    }

    public void UpdateEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {

        boolean duplicate = false;

        ErrorOutput.setText("");

        for(EmployeeModel employee:oblist){
            if(!employee.getId().equals(UUID.fromString(empIDText.getText()))) {
                if (employee.getUsername().equals(empUsernameText.getText()) || employee.getPassword().equals(empPassText.getText()) || employee.getPhone().equals(empPhoneText.getText()) || employee.getEmail().equals(empEmailText.getText())) {
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
            for(EmployeeModel employee: oblist){
                if(employee.getId().equals(UUID.fromString(empIDText.getText()))){
                    //employeeExistInTableView = true;
                    employee.setUsername(empUsernameText.getText());
                    employee.setPassword(empPassText.getText());
                    employee.setFirstname(empFNText.getText());
                    employee.setLastname(empLNText.getText());
                    employee.setPhone(empPhoneText.getText());
                    employee.setEmail(empEmailText.getText());
                    employee.setAddress(empAddressText.getText());
                    oblist.set(rowIndex,employee);

                    try {
                        String query = "update employee set userName=?, password=?, firstname=?, lastname=?, phone=?, email=?, address=? where id=?";
                        ps = con.prepareStatement(query);

                        ps.setString(1, empUsernameText.getText());
                        System.out.println(empUsernameText.getText());
                        ps.setString(2, empPassText.getText());
                        System.out.println(empUsernameText.getText());
                        ps.setString(3, empFNText.getText());
                        System.out.println(empFNText.getText());
                        ps.setString(4, empLNText.getText());
                        System.out.println(empLNText.getText());
                        ps.setString(5, empPhoneText.getText());
                        System.out.println(empPhoneText.getText());
                        ps.setString(6, empEmailText.getText());
                        System.out.println(empEmailText.getText());
                        ps.setString(7, empAddressText.getText());
                        System.out.println(empAddressText.getText());
                        ps.setString(8, empIDText.getText());
                        System.out.println(empIDText.getText());
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

    public void ClearTextFields(){
        empUsernameText.clear();
        empPassText.clear();
        empFNText.clear();
        empLNText.clear();
        empEmailText.clear();
        empPhoneText.clear();
        empAddressText.clear();
    }
}





