/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//dao : data access object (queries and all stuff) Java Package

package dao;
import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import common.Encryption;
/**
 *
 * @author kingrishabdugar
 */
public class UserDao {
    
    /*Value should be of User Type as in User.java in models*/
    public static void save(User user) {
        String salt = Encryption.genRandomSalt();
        String encPassword = Encryption.encryptPassword(user.getPassword(),salt);
        String encSecurityAnswer = Encryption.encryptPassword(user.getAnswer(),salt);
      //String sqlInsert = "INSERT INTO staff (name,salt,password,age,salary) VALUES ( '" + name + "', '" + salt + "', '" + encPass + "', " + age + ", " + salary + " )";
        String query = "insert into user(name, email, mobileNumber, address, password, securityQuestion, answer, salt , status) values('" + user.getName() + "','" + user.getEmail() + "','" + user.getMobileNumber() + "','" + user.getAddress() + "','" + encPassword + "','" + user.getSecurityQuestion() + "','" + encSecurityAnswer + "','" + salt + "','false')";
        DbOperations.setDataorDelete(query, "Registered Successfully! Wait for Admin Approval!!");
    }

    public static User login(String email, String password) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("select * from user where email ='" + email + "'");
            while (rs.next()) {
            String dbSalt = rs.getString("salt");
            String dbPass = rs.getString("password");
            String pass = Encryption.encryptPassword(password,dbSalt);
                if(dbPass.equals(pass))
                {
                user = new User();
                user.setStatus(rs.getString("status"));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return user;
    }

    public static User getSecurityQuestion(String email) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("select * from user where email = '" + email + "'");
            while (rs.next()) {
                user = new User();
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static void update(String email, String newPassword) {
        String query = "update user set password = '" + newPassword + "' where email = '" + email + "'";
        DbOperations.setDataorDelete(query, " Password Changed Successfully ");
    }
    
    public static ArrayList<User> getAllRecords(String email){
        ArrayList<User> arrayList = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from user where email like '%"+email+"%'");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                user.setAddress(rs.getString("address"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
        
    }
    
    public static void changeStatus(String email,String status){
        String query = "update user set status ='"+status+"' where email ='"+email+"'";
        DbOperations.setDataorDelete(query," Status Changed Successfully");
        
    }
    
    public static void changePassword(String email,String oldPassword,String newPassword){
        try{
            ResultSet rs = DbOperations.getData("select * from user where email ='"+email+"' and password ='"+oldPassword+"'");
            if(rs.next()){
                update(email, newPassword);
                
            }
            else{
                
                JOptionPane.showMessageDialog(null,"Old Password is Worng");
            }
        }
    
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void changeSecurityQuestion(String email,String password,String securityQuestion,String answer){
        try{
            ResultSet rs = DbOperations.getData("select * from user where email='"+email+"' and password='"+password+"'");
            if(rs.next()){
                update(email, securityQuestion, answer);
                
            }
            else{
                    JOptionPane.showMessageDialog(null,"Password is Wrong");
                    }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void update(String email,String securityQuestion,String answer){
        String query = "update user set securityQuestion='"+securityQuestion+"',answer='"+answer+"' where email='"+email+"'";
        DbOperations.setDataorDelete(query, "Security Question Changed Successfully");
    }
     public static void delete(String id){
        String query = "delete from user where id ='"+id+"' "; // delete from user
        DbOperations.setDataorDelete(query, "User deleted successfully!");
        
    }

}




/* String sqlSelect = "SELECT salt,password from staff WHERE name = '" + name+ "'";
 ResultSet rSet = stmt.executeQuery(sqlSelect);
 rSet.next();
 String dbSalt = rSet.getString("salt");
 String dbPass = rSet.getString("password");
 String pass = Utils.encryptPassword(password,dbSalt);
 if(dbPass.equals(pass))
 status = true;
 }
 catch(SQLException ex){
 ex.printStackTrace();
 }
 return status;
} 

           String salt = Utils.genRandomSalt();
            String encPass = Utils.encryptPassword(password,salt);
            String sqlInsert = "INSERT INTO staff (name,salt,password,age,salary) VALUES ( '" + name + "', '" + salt + "', '" + encPass + "', " + age + ", " +
            salary + " )";

 public static String encryptPassword(String password,String salt)
    {
        String encryptedPassword = null;
        try
        {
            MessageDigest md=MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes= md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb=new StringBuilder();
            for(byte aByte : bytes)
            {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword=sb.toString();
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println(e);
        }
        return encryptedPassword;
    }
    public static String genRandomSalt()
    {
        SecureRandom random = new SecureRandom();
        String gensalt = random.ints(48,122+1)
                .filter(i -> (i<=57 || i>=65) && (i<=90 || i>=97))
                .limit(10)
                .collect(StringBuilder ::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .substring(0);
        
        return gensalt;
        
    }
*/