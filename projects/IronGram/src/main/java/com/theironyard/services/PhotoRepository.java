package com.theironyard.services;

import com.theironyard.entities.Photo;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zach on 11/17/15.
 */
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
    List<Photo> findByReceiver(User receiver);
}
