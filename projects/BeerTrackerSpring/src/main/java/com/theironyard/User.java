package com.theironyard;

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
    Integer id;

    String name;
    String password;
    @OneToMany(mappedBy = "user") // the name of the field in the Beer class
    List<Beer> beers;
}
