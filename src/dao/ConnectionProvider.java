/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;

/**
 *
 * @author kingrishabdugar
 */
public class ConnectionProvider {
    public static Connection getCon()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //root and "Password" for MySQL login in MySQL login locally in Client
            //Local Server
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms?useSSL=false","Username","Password");
            //Online SQL Server
            //Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/greenleafgourmet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","UserName","Password");
            // Win + R  -> services.msc -> MySQL (manual) -> Start -> MySql client -> Login with password
            return con;
        }
        catch(Exception e){
            return null;
        }   
    }
}
