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
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kingrishabdugar
 */
public class UserDao {

    /*Value should be of User Type as in User.java in models*/
    public static void save(User user) {
        String salt = Encryption.genRandomSalt();
        String encPassword = Encryption.encryptPassword(user.getPassword(), salt);
        String encSecurityAnswer = Encryption.encryptPassword(user.getAnswer(), salt);
        //String sqlInsert = "INSERT INTO staff (name,salt,password,age,salary) VALUES ( '" + name + "', '" + salt + "', '" + encPass + "', " + age + ", " + salary + " )";
        String query = "insert into user(name, email, mobileNumber, address, password, securityQuestion, answer, salt , status) values('" + user.getName() + "','" + user.getEmail() + "','" + user.getMobileNumber() + "','" + user.getAddress() + "','" + encPassword + "','" + user.getSecurityQuestion() + "','" + encSecurityAnswer + "','" + salt + "','false')";
        DbOperations.setDataorDelete(query,"<html><b style=\"color:Green\">Registered Successfully ✅ Wait for Admin Approval ❗</b></html>");
    }

    public static User login(String email, String password) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("select * from user where email ='" + email + "'");
            while (rs.next()) {
                String dbSalt = rs.getString("salt");
                String dbPass = rs.getString("password");
                String pass = Encryption.encryptPassword(password, dbSalt);
                if (dbPass.equals(pass)) {
                    user = new User();
                    user.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "<html><b style=\"color:Red\">Some Error ❌ Occured ❗ </b></html>", JOptionPane.ERROR_MESSAGE);

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
             JOptionPane.showMessageDialog(null, e, "<html><b style=\"color:Red\">Some Error ❌ Occured ❗ </b></html>",JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }

    public static void update(String email, String newPassword) {
//        User user = null;
        try {
            ResultSet rs = DbOperations.getData("select * from user where email ='" + email + "'");
            if (rs.next()) {
                //user = new User();
                String dbSalt = rs.getString("salt");
                String pass = Encryption.encryptPassword(newPassword, dbSalt);
                String query = "update user set password = '" + pass + "' where email = '" + email + "'";
                DbOperations.setDataorDelete(query, "<html><b style=\"color:Green\">Password 🔐 Changed Successfully ❗</b></html>");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//models.User gets filled here 

    public static ArrayList<User> getAllRecords(String email) {
        ArrayList<User> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from user where email like '%" + email + "%'");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                user.setAddress(rs.getString("address"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                user.setSalt(rs.getString("salt"));
                arrayList.add(user);

            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e, "Some Error ❌ Occured ❗",JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;

    }

    public static void changeStatus(String email, String status) {
        String query = "update user set status ='" + status + "' where email ='" + email + "'";
        DbOperations.setDataorDelete(query, "<html><b style=\"color:Green\">Status ✅ Changed Successfully ❗</b></html>");

    }

    public static void changePassword(String email, String oldPassword, String newPassword) {
        try {
            ResultSet rs = DbOperations.getData("select * from user where email = '" + email + "'");
            while (rs.next()) {
                String dbSalt = rs.getString("salt");
                String dbPass = rs.getString("password");

                String pass = Encryption.encryptPassword(oldPassword, dbSalt);
                if (pass.equals(dbPass)) {
                    update(email, newPassword);
                    JOptionPane.showMessageDialog(null, "<html><b style=\"color:Green\">Password 🔐 Updated Successfully ❗</b></html>");
                } else {
                    JOptionPane.showMessageDialog(null, "<html><b style=\"color:Red\">Old Password 🔐 is Incorrect ❌</b></html>");
                }
            }
        } catch (HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null, e, "Some Error ❌ Occured ❗",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void changeSecurityQuestion(String email, String password, String securityQuestion, String answer) {

        String dbSalt = "", dbPass = "", pass = "";
        try {
            ResultSet rs = DbOperations.getData("select * from user where email = '" + email + "'");

            while (rs.next()) {
                dbSalt = rs.getString("salt");
                dbPass = rs.getString("password");

                answer = Encryption.encryptPassword(answer, dbSalt);
                pass = Encryption.encryptPassword(password, dbSalt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (pass.equals(dbPass)) {
            String query = "update user set securityQuestion='" + securityQuestion + "',answer='" + answer + "' where email='" + email + "'";
            DbOperations.setDataorDelete(query, "<html><b style=\"color:Green\">Security Question 🔒 Changed Successfully ❗</b></html>");
        } else {
            JOptionPane.showMessageDialog(null, "<html><b style=\"color:Red\">Incorrect Password ❌ ❗</b></html>");
        }
    }
//function overloading
//
//    public static void update(String email, String securityQuestion, String answer) {
//        String query = "update user set securityQuestion='" + securityQuestion + "',answer='" + answer + "' where email='" + email + "'";
//        DbOperations.setDataorDelete(query, "Security Question Changed Successfully");
//    }

    public static void delete(String id) {
        String query = "delete from user where id ='" + id + "' "; // delete from user
        DbOperations.setDataorDelete(query, "<html><b style=\"color:Red\">User 👤 Deleted Successfully ❗</b></html>");

    }
}

