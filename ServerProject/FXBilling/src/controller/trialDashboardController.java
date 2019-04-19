package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class trialDashboardController implements Initializable {
    @FXML
    private Label username;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button bills;

    @FXML
    private Button logoutBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Dash LoggedIn user:"+UserSession.loggedInUser.getUserName());
        String usernamestr = UserSession.loggedInUser.getUserName();
        System.out.println("username:"+usernamestr);
        username.setText(usernamestr);
    }

    @FXML
    private void logoutHandler(){
        UserSession.loggedInUser = null;

       // System.out.println("Dash LoggedIn user after logout :"+UserSession.loggedInUser.getUserName());
    }

    @FXML
    private void displayBills() {
        System.out.println("Dash LoggedIn user:"+UserSession.loggedInUser.getUserName());
    }

    @FXML
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
