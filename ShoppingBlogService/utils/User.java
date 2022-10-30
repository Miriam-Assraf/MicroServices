package acs.utils;

import acs.exceptions.BadRequestException;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;


public class User {
    private String email;

    User(){}

    User(String email){
        this.email=email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) { this.email = email;}

    public void validate(){
        if(email == null)
            throw new BadRequestException("email must be specified");

        if (!new EmailValidator().isValid(this.email, null) || email.isEmpty()) {
            throw new RuntimeException("The email is not in a valid format");
        }

    }
}
