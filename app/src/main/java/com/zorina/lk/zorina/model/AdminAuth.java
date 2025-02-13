package com.zorina.lk.zorina.model;

public class AdminAuth {

    private String Name;
    private String Mobile;
    private String Email;

    public AdminAuth(String name, String mobile, String email) {
        Name = name;
        Mobile = mobile;
        Email = email;
    }

    public AdminAuth(){

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


}
