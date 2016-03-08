package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by zach on 3/8/16.
 */
@Entity
public class Game {
    @Id
    @GeneratedValue
    int id;

    String name;
    String platform;
    int releaseYear;

    public Game() {
    }

    public Game(String name, String platform, int releaseYear) {
        this.name = name;
        this.platform = platform;
        this.releaseYear = releaseYear;
    }
}
