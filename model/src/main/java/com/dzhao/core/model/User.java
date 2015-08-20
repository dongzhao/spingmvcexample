package com.dzhao.core.model;
import com.dzhao.core.model.generic.AbstractDomain;
import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "my_user")
public class User extends AbstractDomain{

    @Column(name="USER_NAME", nullable = false)
    private String username;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="EMAIL")
    private String email;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
