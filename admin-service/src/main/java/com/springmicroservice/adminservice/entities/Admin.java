package com.springmicroservice.adminservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "admin")
@Entity
@Getter
@Setter
public class Admin extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email_address", length = 200)
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "email_sent")
    private Character emailSent;

    @Column(name = "status")
    private Character status;

    @Column(name = "login_attempt")
    private Integer loginAttempt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Integer getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(Integer loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

//    public List<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<String> roles) {
//        this.roles = roles;
//    }

    public Character getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(Character emailSent) {
        this.emailSent = emailSent;
    }

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();

    // constructor
    public Admin(String username, String password, String emailAddress, Character status, Integer loginAttempt) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.status = status;
        this.loginAttempt = loginAttempt;
        //this.roles = roles;
    }

    public Admin() {
    }

}
