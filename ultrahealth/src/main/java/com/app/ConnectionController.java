package com.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.app.database.*;
import com.app.entity.*;

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
    void connectionClicked() throws SQLException, IOException, URISyntaxException, ClassNotFoundException {
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
        }
        this.user.toString();

        if(this.user.getRole().equals("ADMIN")){
            String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            SecureRandom RANDOM = new SecureRandom();
            String token = generate(41, ALPHABET, RANDOM);
            System.out.println("token :" + token);
            sqlQuery = "UPDATE users SET token = '"+token+"' WHERE id = '"+this.user.getId()+"' ";
           int reslt = this.db.insertIntoBase(sqlQuery);
           if(reslt == 1 ){
            System.out.println("succes ! ");
           }else{
            System.out.println("faild ! ");
           }
            App.setRoot("adminDashboard");
        }
    }

    @FXML
    void signUpClicked(ActionEvent event) throws IOException {
        App.setRoot("signUp");
    }

    public String cookie(){
        System.out.println(this.lastNameField.getText());
        return this.lastNameField.getText();
    }

   
    public static String generate(int length, String ALPHABET, SecureRandom RANDOM) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
    
}