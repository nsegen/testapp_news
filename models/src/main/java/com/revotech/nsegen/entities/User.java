package com.revotech.nsegen.entities;

/**
 * Created by Revotech on 12.07.16.
 */
public class User {
    Integer id;
    String firstName;
    String lastName;
    String password;
    String nickname;

    public User(){
        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.password = null;
        this.nickname = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
