package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DBHandler;
import model.Global;
import model.UserSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private Label label;

    DBHandler db;
    Connection con=null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @FXML
    private Tab tabAdmin;

    @FXML
    private TabPane tabPaneLogin;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblCreateAccount;

    @FXML
    private Label lblAdmin;

    @FXML
    private Label lblUser;

    @FXML
    private Pane slidingPane;


    @FXML
    private Tab tabUser;


    public void openUserTab(MouseEvent mouseEvent) {

        TranslateTransition toRightAnimation =new TranslateTransition(new Duration(500),lblStatus);

        toRightAnimation.setToX(slidingPane.getTranslateX()+(slidingPane.getPrefWidth()-lblStatus.getPrefWidth()));
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event) -> {
            lblStatus.setText("USER");
        });
        tabPaneLogin.getSelectionModel().select(tabUser);
    }

    public void openAdminTab(MouseEvent mouseEvent) {

        TranslateTransition toLeftAnimation =new TranslateTransition(new Duration(500),lblStatus);
        toLeftAnimation.setToX(slidingPane.getTranslateX());
        toLeftAnimation.play();
        toLeftAnimation.setOnFinished((ActionEvent event) -> {
            lblStatus.setText("ADMINISTRATOR");
        });

        tabPaneLogin.getSelectionModel().select(tabAdmin);

    }

    @FXML
    private Button adminLoginBtn;

    @FXML
    private TextField adminUsername;

    @FXML
    private TextField adminPassword;

    @FXML
    private Button userLoginBtn;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label loginOutput;


    public void adminLoginHandler(ActionEvent actionEvent) throws SQLException {

        db = DBHandler.getInstance();



        try{
            con=db.getConnection();
            System.out.println("con:"+con);
            ps=con.prepareStatement("select * from admin where username=? and password=? ");
            ps.setString(1, adminUsername.getText());
            ps.setString(2, adminPassword.getText());
            rs=ps.executeQuery();

            if (rs.next()){
                System.out.println("Name: "+rs.getString("username"));
                UserSession.loggedInUser= UserSession.getInstance(rs.getString("username"),rs.getInt("id"));
                System.out.println("LoggedIn Admin:"+UserSession.loggedInUser.getUserName());
                //loginOutput.setText(rs.getString("firstname")+" "+rs.getString("lastname"));

                Stage stage = (Stage) adminLoginBtn.getScene().getWindow();
                //FXMLLoader loader = new FXMLLoader();
                //loader.getNamespace().put("username",UserSession.loggedInUser.getUserName());
                Parent root = FXMLLoader.load(getClass().getResource("../view/adminDashboard.fxml"));
                Scene scene =  new Scene(root, Global.WIDTH, Global.HEIGHT);
                stage.setScene(scene);
                stage.show();

            }
            else{
                //System.out.println("Loggin Failed");
                loginOutput.setText("Loggin Failed");
            }
        }catch (Exception e){ e.printStackTrace(); } finally{
            if (ps!=null){
                ps.close();
            }if(con!=null){
                con.close();
            }
        }

    }

    public void userLoginHandler(ActionEvent actionEvent) throws SQLException {
        db = DBHandler.getInstance();

        try{
            con=db.getConnection();
            System.out.println("con:"+con);
            ps=con.prepareStatement("select * from employee where username=? and password=? ");
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            rs=ps.executeQuery();

            if (rs.next()){
                System.out.println("Name: "+rs.getString("firstname")+" "+rs.getString("lastname"));
                UserSession.loggedInUser= UserSession.getInstance(rs.getString("firstname"),rs.getInt("id"));
                System.out.println("LoggedIn user:"+UserSession.loggedInUser.getUserName());
                //loginOutput.setText(rs.getString("firstname")+" "+rs.getString("lastname"));

                Stage stage = (Stage) userLoginBtn.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                //loader.getNamespace().put("username",UserSession.loggedInUser.getUserName());
                Parent root = FXMLLoader.load(getClass().getResource("../view/billTemplate.fxml"));
                Scene scene =  new Scene(root, Global.WIDTH ,Global.HEIGHT);
                stage.setScene(scene);
                stage.show();

            }
            else{
                //System.out.println("Loggin Failed");
                loginOutput.setText("Loggin Failed");
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
