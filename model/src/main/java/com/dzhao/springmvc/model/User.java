package com.dzhao.springmvc.model;
import com.dzhao.springmvc.model.generic.AbstractDomain;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "my_user")
public class User extends AbstractBaseEntity{

    @Column(name="USER_NAME", nullable = false)
    private String userName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="EMAIL")
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
