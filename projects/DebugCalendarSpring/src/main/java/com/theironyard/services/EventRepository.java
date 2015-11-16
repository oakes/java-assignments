package com.theironyard.services;

import com.theironyard.entities.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zach on 11/15/15.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
}
