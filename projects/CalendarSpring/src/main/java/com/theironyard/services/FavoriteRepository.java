package com.theironyard.services;

import com.theironyard.entities.Event;
import com.theironyard.entities.Favorite;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zach on 3/14/16.
 */
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
    Favorite findByUserAndEvent(User user, Event event);
}
