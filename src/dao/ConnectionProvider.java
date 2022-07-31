/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;

/**
 *
 * @author kingr
 */
public class ConnectionProvider {
    public static Connection getCon()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //root and "Password" for MySQL login in MySQL login locally in Client
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms?useSSL=false","root","Your MySql Password");
            // Win + R  -> services.msc -> MySQL (manual) -> Start -> MySql client -> Login with password
            return con;
        }
        catch(Exception e){
            return null;
        }   
    }
}
