package com.example.fidely.fidely.model;

import org.ektorp.support.CouchDbDocument;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends CouchDbDocument {
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String email;
    private String phone;
    private String password;
    private String role;
}