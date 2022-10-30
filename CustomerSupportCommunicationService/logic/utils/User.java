package acs.logic.utils;

import acs.utils.UserFullName;

public class User {

    private String email;
    private UserFullName name;
    private String birthdate;
    private String[] roles;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String email, UserFullName name, String birthdate, String[] roles) {
        super();
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.roles = roles;
    }

    public UserFullName getName() {
        return name;
    }

    public void setName(UserFullName name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
