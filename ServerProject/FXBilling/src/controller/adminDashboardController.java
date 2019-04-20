package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminDashboardController implements Initializable {

    @FXML
    private Label username;

    @FXML
    private BorderPane mainBorderPane;


    @FXML
    private Button billDetailBtn;

    @FXML
    private Button empDetailBtn;

    @FXML
    private Button prodDetailBtn;


    @FXML
    private Button logoutBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Dash LoggedIn user:"+ UserSession.loggedInUser.getUserName());
        String usernamestr = UserSession.loggedInUser.getUserName();
        System.out.println("username:"+usernamestr);
        username.setText(usernamestr);
    }


    public void BillDetailHandler(ActionEvent actionEvent) {
        handleShowView(actionEvent);
    }

    public void EmployeeHandler(ActionEvent actionEvent) {
        handleShowView(actionEvent);
    }

    public void ProductHandler(ActionEvent actionEvent) {
        handleShowView(actionEvent);
    }

    public void logoutHandler(ActionEvent actionEvent) throws IOException {
        UserSession.loggedInUser = null;
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Scene scene =  new Scene(root, 1280 ,800);
        stage.setScene(scene);
        stage.show();
    }


    private void handleShowView(ActionEvent e) {
        String view = (String) ((Node)e.getSource()).getUserData();
        loadFXML(getClass().getResource(view));
    }

    private void loadFXML(URL url) {
        try {
            username.setText(UserSession.loggedInUser.getUserName());
            System.out.println("Dash LoggedIn user:"+UserSession.loggedInUser.getUserName());
            FXMLLoader loader = new FXMLLoader(url);
            mainBorderPane.setCenter(loader.load());

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
