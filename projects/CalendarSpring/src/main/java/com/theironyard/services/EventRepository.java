package com.theironyard.services;

import com.theironyard.entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zach on 3/14/16.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findAllByOrderByDateTimeDesc();
    List<Event> findAllByOrderByDescriptionAsc();
}
