package com.blog.itmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    private String username;
    private String password;// icon url
    private String memId;   // FK: Id of Member Class
    private int roleId;     // FK: Id of Role Class
}
