package com.dzhao.springmvc.model;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;
import java.util.Date;

/**
 * Created by dzhao on 2/09/2015.
 */
@Entity
@Table(name = "my_user_profile")
public class UserProfile extends AbstractBaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Column(name = "living_address")
    private String address;
    @Column(name = "date_of_birth")
    //@JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @Column
    private boolean gender;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
