package acs.boundary;

import acs.utils.UserFullName;

public class UserBoundaryWithPassword extends UserBoundary {
    private String password;

    public UserBoundaryWithPassword() {}


    public UserBoundaryWithPassword(String email, UserFullName name, String password, String birthdate, String[] roles) {
        super(email, name, birthdate, roles);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
