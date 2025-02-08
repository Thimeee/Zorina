package com.zorina.lk.zorina.model;

public class Admin {

    private String Name;
    private String Mobile;
    private String Email;
    private String UserName;
    private String Password;

    public Admin() {
    }

    public Admin(String name, String mobile, String email, String userName, String password) {
        Name = name;
        Mobile = mobile;
        Email = email;
        UserName = userName;
        Password = password;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
}
