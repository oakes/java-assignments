package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by zach on 3/14/16.
 */
@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

    @ManyToOne
    Event event;

    public Favorite() {
    }

    public Favorite(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
