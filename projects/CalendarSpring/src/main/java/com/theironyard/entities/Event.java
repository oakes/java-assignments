package com.theironyard.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by zach on 11/15/15.
 */
@Entity
public class Event {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    int id;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public LocalDateTime date;

    @ManyToOne
    public User user;

    public Event() {
    }

    public Event(User user, LocalDateTime date, String description) {
        this.user = user;
        this.date = date;
        this.description = description;
    }
}
