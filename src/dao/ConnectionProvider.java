<<<<<<< HEAD
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
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+server+"/cms?useSSL=false",username,password);
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
        catch(Exception e){
            return null;
        }   
    }
}
=======
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
>>>>>>> fe35325a78edd5e7ac8be8dbe354e80dedc53409
