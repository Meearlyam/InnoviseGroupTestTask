package main.java.by.meearlyam.userseditor.model;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private List<RoleEnum> roles;
    private List<String> mobilePhones;

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String surname, String email, List<RoleEnum> roles, List<String> mobilePhones) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.mobilePhones = mobilePhones;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    public List<String> getMobilePhones() {
        return mobilePhones;
    }

    public void setMobilePhones(List<String> mobilePhones) {
        this.mobilePhones = mobilePhones;
    }

    public String toString() {
        return "\n ID: " + id +
                "\n Name: " + name +
                "\n Surname: " + surname +
                "\n Email: " + email +
                "\n Roles: " + roles +
                "\n Mobile phone numbers: " + mobilePhones;
    }
}
