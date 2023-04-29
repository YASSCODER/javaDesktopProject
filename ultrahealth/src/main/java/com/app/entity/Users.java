package com.app.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Users {
    int id;
    String cin;
    String firstName;
    String lastName;
    String zone;
    Date birthDay;
    String role;
    String email;
    String password;
    String token;

    public Users(){}
    public Users(int id, String cin, String firstName, String lastName, String zone, Date birthDay, String role, String email, String password, String token){
        this.id = id;
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zone = zone;
        this.birthDay = birthDay;
        this.role = role;
        this.email = email;
        this.password = password;
        this.token = token;
    }
    public Users(int id, String cin, String firstName, String lastName, String zone, String email){
        this.id = id;
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zone = zone;
        this.email = email;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    //CIN :
    public void setCin(String cin){
        this.cin = cin;
    }
    public String getCin(){
        return this.cin;
    }
    //FN :
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return this.firstName ;
    }

    //LN : 
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return this.lastName;
    }
 
    //ZONE :
    public void setZone(String zone){
        this.zone = zone;
    }
    public String getZone(){
        return this.zone;
    }

    //BIRTHDAY :
    public void setBirthDay(Date birthDay){
        this.birthDay = birthDay;
    }
    public Date getBirthDay(){
        return this.birthDay;
    }

    //ROLE:
    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }

    //EMAIL : 
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }

    //PASSWORD :
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    //TOKEN :
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }

    public String convertDate(Date date){
        String strDate;
        DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        strDate = dateformat.format(date);
        return strDate;
    }

    public Date converter(LocalDate localDate){
        LocalDateTime localDateTime = localDate.atStartOfDay();
       Date  date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    @Override
    public String toString(){
        String str;
        
        str = "User -> " + this.getRole() + " {\n" + this.getCin() + "\n" + this.getFirstName() + "\n" + this.getLastName() + "\n" + this.getZone() + "\n" + this.convertDate(this.birthDay) + "\n" + this.getEmail() + "\n" + this.getToken() + "\n" + "}";
        return str;
    }
}
