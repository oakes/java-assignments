package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by zach on 11/10/15.
 */
@Entity
public class Beer {
    @Id
    @GeneratedValue
    Integer id;

    String name;
    String type;
    Integer calories;
    @ManyToOne
    User user;
}
