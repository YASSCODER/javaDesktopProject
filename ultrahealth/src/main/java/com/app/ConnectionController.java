package com.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.app.database.*;
import com.app.entity.Users;

public class ConnectionController {

    private Base db;
    private Users user;

    @FXML
    private Button connectionButton;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    public ConnectionController(){

    }

    @FXML
    public Users connectionClicked() throws SQLException, IOException, URISyntaxException, ClassNotFoundException {
        ResultSet res;
        this.user = new Users();
        try {
             this.db = new Base("jdbc:mysql://localhost:3306/ultrahealth", "yako", "admin");
            db.connection();
        } catch (SQLException e) {
            System.err.println("Error connecting to MySQL database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found: " + e.getMessage());
        }
        String sqlQuery = "SELECT * FROM users where lastname = '"+this.lastNameField.getText()+"' AND password = '"+this.passwordField.getText()+"'";
        res = this.db.useStatment(sqlQuery);
        if(res.next()){
            this.user.setId(res.getInt(1));
            this.user.setCin(res.getString(2));
            this.user.setFirstName(res.getString(3));
            this.user.setLastName(res.getString(4));
            this.user.setZone(res.getString(5));
            this.user.setBirthDay(res.getDate(6));
            this.user.setRole(res.getString(7));
            this.user.setEmail(res.getString(8));
            this.user.setPassword(res.getString(9));
            this.user.setToken(res.getString(10));
            if(this.user.getRole().equals("ADMIN")){
            
                String token = this.user.session();
                System.out.println("token :" + token);
                sqlQuery = "UPDATE users SET token = '"+token+"' WHERE id = '"+this.user.getId()+"' ";
               int reslt = this.db.insertIntoBase(sqlQuery);
               if(reslt == 1 ){
                System.out.println("succes ! ");
                this.user = new Users(this.user.getId(),this.user.getCin(),this.user.getFirstName(),this.user.getLastName(),this.user.getZone(),this.user.getBirthDay(),this.user.getRole(),this.user.getEmail(),this.user.getPassword(),this.user.getToken());
                System.out.println(this.user.toString());
                App.setRoot("adminDashboard");
                return this.user;
               }else{
                System.out.println("faild ! ");
               }
               
                
            }else{
                String token = this.user.session();
                System.out.println("token :" + token);
                sqlQuery = "UPDATE users SET token = '"+token+"' WHERE id = '"+this.user.getId()+"' ";
               int reslt = this.db.insertIntoBase(sqlQuery);
               if(reslt == 1 ){
                System.out.println("succes ! ");
                this.user = new Users(this.user.getId(),this.user.getCin(),this.user.getFirstName(),this.user.getLastName(),this.user.getZone(),this.user.getBirthDay(),this.user.getRole(),this.user.getEmail(),this.user.getPassword(),this.user.getToken());
                System.out.println(this.user.toString());
                App.setRoot("userDashBoard");
                return this.user;
               }else{
                System.out.println("faild ! ");
               }
            }
            return user;
        }else{
            System.out.println("account do not exist ! ");
        }
        this.user.toString();

        return user;
    }

    
    public void signUpClicked() throws IOException {
        App.setRoot("signUp");
    }
}