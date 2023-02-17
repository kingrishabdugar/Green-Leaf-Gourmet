/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author kingr
 */
//creates queries to feed into SQL client via inputs from user
import javax.swing.*;

public class tables {

    public static void main(String[] args) {
        try {
            //String database = "create database green-leaf-gourmet"; 
            String userTable = "create table user(id int AUTO_INCREMENT primary key, name varchar(200),email varchar(200),mobileNumber varchar(10),address varchar(200),password varchar(200),securityQuestion varchar(200),answer varchar(200),status varchar(20),UNIQUE(email))";
            String adminDetails = "insert into user(name,email,mobileNumber,address,password,securityQuestion,answer,status) values('Admin','admin@gmail.com','1234567890','India','admin','Favorite Cartoon?','Doraemon','true')";
            String categoryTable = "create table category (id int AUTO_INCREMENT primary key,name varchar(200))";
            String productTable = "create table product(id int AUTO_INCREMENT primary key,name varchar(200),category varchar(200),price varchar(200))";
            String billTable = "create table bill(id int primary key,name varchar(200),mobileNumber varchar(200),email varchar(200),date varchar(50),total varchar(200),createdBy varchar(200))";
//            DbOperations.setDataorDelete(userTable, "User Database Created Successfully");
//            DbOperations.setDataorDelete(adminDetails, "Admin Details Added Successfully");
//            DbOperations.setDataorDelete(categoryTable, "Category Database Created Successfully");
//            DbOperations.setDataorDelete(productTable, "Product Database Created Successfully");
//            DbOperations.setDataorDelete(billTable, "Bill Table Created Successfully");
         

            String updateapplication = "create table updateapp(id int AUTO_INCREMENT primary key,current_version nvarchar(200),EXE_URL nvarchar(5000),latest_version nvarchar(200))";
            String exe = "insert into updateapp (current_version,EXE_URL,latest_version) values('0','null','null') " ;
            //String alter = "ALTER TABLE user ADD COLUMN salt nvarchar(200) AFTER email;";
            String alter="DROP table updateapp";
            String alter2="CREATE TABLE IF NOT EXISTS updateapp ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, current_version VARCHAR(255) NOT NULL, EXE_URL VARCHAR(255) NOT NULL, latest_version VARCHAR(255) NOT NULL ); ALTER TABLE updateapp ADD UNIQUE INDEX (id); INSERT INTO updateapp (id, current_version, EXE_URL, latest_version) VALUES (1, '0', 'null', '0') ON DUPLICATE KEY UPDATE current_version = updateapp.current_version, EXE_URL = updateapp.EXE_URL, latest_version = updateapp.latest_version;";
            
            
            /*In this example, the table updateapp is created with a unique index on the id column. The INSERT statement will either insert a new row with the values specified or, if a row with the id value of 1 already exists, it will do nothing and no values will be updated.
*/
           //alter="insert ignore into user(name,email,mobileNumber,address,password,securityQuestion,answer,status,salt) values('Admin','admin@gmail.com','1234567890','India','admin','Favorite Cartoon?','Doraemon','true','admin');";
            DbOperations.setDataorDelete("CREATE TABLE IF NOT EXISTS updateapp ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, current_version VARCHAR(255) NOT NULL, EXE_URL VARCHAR(255) NOT NULL, latest_version VARCHAR(255) NOT NULL );", "test pass");
            DbOperations.setDataorDelete("ALTER TABLE updateapp ADD UNIQUE INDEX (id);","test pass");
            DbOperations.setDataorDelete("INSERT INTO updateapp (id, current_version, EXE_URL, latest_version) VALUES (1, '2.5.1', 'null', '2.5.1') ON DUPLICATE KEY UPDATE current_version = updateapp.current_version, EXE_URL = updateapp.EXE_URL, latest_version = updateapp.latest_version;","test pass");
            //DbOperations.setDataorDelete("ALTER TABLE updateapp ADD UNIQUE INDEX (id);","test pass");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
}
