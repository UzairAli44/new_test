package com.example.fidely.fidely.model;

import org.ektorp.support.CouchDbDocument;

public class User extends CouchDbDocument {
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String email;
    private String phone;
    private String password;
    private String role;

    public User() {}

    public User(String username, String password, String role, String firstName, String lastName, String dob, String address, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
