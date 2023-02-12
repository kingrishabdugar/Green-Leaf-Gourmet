/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author kingrishabdugar
 */
public class ConnectionProvider {
    
    static String username;
    static String password;
    static String server;
    static int id = 1;
    public static void getid(int a)
    {
        id=a;
    }
    public static void setusername(String id) {
        ConnectionProvider.username = id;
    }
    public static void setpassword(String password) {
        ConnectionProvider.password = password;
    }
    public static void setserver(String server) {
        ConnectionProvider.server = server;
    }
    public static Connection getCon()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //root and "Password" for MySQL login in MySQL login locally in Client
            //Local Server
            if(id == 0)
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+server+"/GreenLeafGourmet2?useSSL=false",username,password);
                return con;
            }
            else
            {
            //Online SQL Server
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/greenleafgourmet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","kingrishabdugar","i3Enq7@gM!maYmn");
            // Win + R  -> services.msc -> MySQL (manual) -> Start -> MySql client -> Login with password
            return con;
            }
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException ex) {   
            Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}