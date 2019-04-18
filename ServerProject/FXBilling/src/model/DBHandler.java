package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* Singleton Design pattern implementation of this class
 */
public class DBHandler {

    private static DBHandler db;
    private DBHandler() {
    }

    public  static  DBHandler getInstance(){
        if (db==null)
        {
            db=new  DBHandler();
        }
        return  db;
    }

    // to get the connection from methods like insert, view etc.
    public static Connection getConnection()throws ClassNotFoundException, SQLException
    {

        Connection con=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:8889/billing", "root", "root");
        return con;

    }


}
