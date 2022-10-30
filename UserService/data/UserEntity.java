package acs.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import acs.annotations.Email;
import acs.annotations.NotEmptyFields;
import org.hibernate.annotations.Formula;

import acs.annotations.Password;

@Entity
@Table(name="USERS")
public class UserEntity {

    @Id
    @Email
    private String email;        // EMAIL PK VARCHAR(255)

    @NotEmpty(message="First name can not be empty")
    private String firstName;    // FIRST VARCHAR(255)

    @NotEmpty(message="Last name can not be empty")
    private String lastName;    // LAST VARCHAR(255)

    @Password
    private String password;    // PASSWORD VARCHAR(255)

    @NotNull
    private Date birthdate;    // CREATED_TIME_STAMP TIMESTAMP

    @Lob
    @NotEmptyFields
    @Convert(converter = acs.logic.utils.SetToJsonConverter.class)
    public Set<String> roles;

    @Formula("DATE_PART('YEAR', AGE(birthdate))::int")
    private int age;    // calculated field by birth date

    public UserEntity() {
        this.roles = new HashSet<>();
    }

    public UserEntity(String email, String firstName, String lastName, String password, Date birthdate, Set<String> roles) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthdate = birthdate;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() { return birthdate; }

    public void setBirthdate(Date date) {
        if(date.after(new Date())){
            throw new RuntimeException("Date is illegal");
        }
        this.birthdate = date;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
