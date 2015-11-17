package com.theironyard.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zach on 11/11/15.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    Integer id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String password;

    @OneToMany(mappedBy = "user") // the name of the field in the Beer class
    public List<Beer> beers;
}
