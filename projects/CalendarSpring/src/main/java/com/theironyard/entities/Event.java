package com.theironyard.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by zach on 3/14/16.
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    LocalDateTime dateTime;

    @ManyToOne
    User user;

    @Transient // don't store in the database
    boolean showFavButton = false;

    public Event() {
    }

    public Event(String description, LocalDateTime dateTime, User user) {
        this.description = description;
        this.dateTime = dateTime;
        this.user = user;
    }

    public void setShowFavButton(boolean showFavButton) {
        this.showFavButton = showFavButton;
    }
}
