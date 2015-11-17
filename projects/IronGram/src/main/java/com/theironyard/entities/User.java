package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by zach on 11/17/15.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    public int id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;
}
