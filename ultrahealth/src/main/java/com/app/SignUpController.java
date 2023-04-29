package com.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import com.app.entity.Users;
import com.app.database.Base;

public class SignUpController {

    private Users user;
    private boolean roleClient = false;
    private boolean roleDoctor = false;
    private boolean rolePatient = false;
    private boolean roleNutritionist = false;
    private Base db;
    

    @FXML
    private DatePicker birthDayField;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField cinField;


    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNamefield;


    @FXML
    private PasswordField passwordField;

    @FXML
    private Label roleLable;


    @FXML
    private TextField zoneField;

    @FXML
    private Button ClientBtn;

    @FXML
    private Button doctorBtn;

    @FXML
    private Button nutBTN;

    @FXML
    private Button patientBtn;

    @FXML
    void btnSignUpClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        this.user = new Users();
        try {
            this.db = new Base("jdbc:mysql://localhost:3306/ultrahealth", "yako", "admin");
           db.connection();
       } catch (SQLException e) {
           System.err.println("Error connecting to MySQL database: " + e.getMessage());
       } catch (ClassNotFoundException e) {
           System.err.println("MySQL JDBC driver not found: " + e.getMessage());
       }
       
        
        Date date = this.user.converter(this.birthDayField.getValue());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Random rand = new Random();
        int randomId = rand.nextInt(9999);

        System.out.println("date : " + date.getClass());
        
        this.user.setId(randomId);
        this.user.setCin(this.cinField.getText());
        this.user.setFirstName(this.firstNameField.getText());
        this.user.setLastName(this.lastNamefield.getText());
        this.user.setZone(this.zoneField.getText());
        this.user.setBirthDay(sqlDate);
        this.user.setEmail(this.emailField.getText());
        this.user.setPassword(this.passwordField.getText());

        if(roleClient == true){
            this.user.setRole("CLIENT");
        }else if(roleDoctor == true){
            this.user.setRole("DOCTOR");
        }else if(roleNutritionist == true){
            this.user.setRole("NUTRITIONIST");
        }else{
            this.user.setRole("PATIENT");
        }


        String sqlQuery = "INSERT users (`id`, `cin`, `firstname`, `lastname`, `zone`, `birthday`, `role`, `email`, `password`) VALUES('"+this.user.getId()+"','"+this.user.getCin()+"','"+this.user.getFirstName()+"','"+this.user.getLastName()+"','"+this.user.getZone()+"','"+this.user.getBirthDay()+"','"+this.user.getRole()+"','"+this.user.getEmail()+"','"+this.user.getPassword()+"')";

            int res = this.db.insertIntoBase(sqlQuery);
            if(res==1){
                System.out.println("Console add /> add with success");
            }else{
                System.out.println("Console add /> Error Fail");
            }
    }

    @FXML
    void ClientBtnClicked(ActionEvent event) {
        this.roleLable.setText(this.ClientBtn.getText());
        this.ClientBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
        public void handle(ActionEvent arg0) {
            roleClient = true;
        }

        });
         
    }

    @FXML
    void doctorBtnClicked(ActionEvent event) {
        this.roleLable.setText(this.doctorBtn.getText());
        this.doctorBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
        public void handle(ActionEvent arg0) {
            roleDoctor = true;
        }

        });

    }

    @FXML
    void nutBTNClicked(ActionEvent event) {
        this.roleLable.setText(this.nutBTN.getText());
        ClientBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
        public void handle(ActionEvent arg0) {
            roleNutritionist = true;
        }

        });

    }

    @FXML
    void patientBtnClicked(ActionEvent event) {
        this.roleLable.setText(this.patientBtn.getText());
        ClientBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
        public void handle(ActionEvent arg0) {
            rolePatient = true;
        }

        });

    }

    public boolean roleClient() {
        return roleClient;
    }

    public boolean roleDoctor() {
        return roleDoctor;
    }

    public boolean roleNutritionist() {
        return roleNutritionist;
    }

    public boolean rolePatientt() {
        return rolePatient;
    }
}