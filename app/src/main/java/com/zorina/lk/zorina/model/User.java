package com.zorina.lk.zorina.model;

public class User {


    private int id;

    private String FullName;

    private String Email;

    private String Mobile;

    private String UserName;


    private String Password;

    private int UserStatus;

    private City city;

    private String Adress;

    public User() {
    }

    public User(int id, String fullName, String email, String mobile, String userName, String password, int userStatus, City city, String adress) {
        this.id = id;
        FullName = fullName;
        Email = email;
        Mobile = mobile;
        UserName = userName;
        Password = password;
        UserStatus = userStatus;
        this.city = city;
        Adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getUserStatus() {
        return UserStatus;
    }

    public void setUserStatus(int userStatus) {
        UserStatus = userStatus;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }
}
