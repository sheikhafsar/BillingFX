package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DBHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class trialLoginController {
    Parent root;
    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label loginOutput;

    @FXML
    private void handleLoginAction(ActionEvent event) throws SQLException, IOException {


        System.out.println("Logging Process");
        System.out.println("Name:"+username.getText());
        System.out.println("Password:"+password.getText());

        DBHandler db = DBHandler.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int recordCounter=0;

        try{
            con=db.getConnection();
            ps=con.prepareStatement("select * from user where user_id=? and password=? ");
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            rs=ps.executeQuery();

            if (rs.next()){
                System.out.println("Name: "+rs.getString("firstname")+" "+rs.getString("lastname"));
                //loginOutput.setText(rs.getString("firstname")+" "+rs.getString("lastname"));
              /*  root = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root));
                stage.show();
                */
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
                //Scene scene =  (Scene) loginButton.getScene();
                Scene scene =  new Scene(root, 800 ,600);
                scene.setRoot(root);
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
