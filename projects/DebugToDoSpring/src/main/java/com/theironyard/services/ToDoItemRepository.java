package com.theironyard.services;

import com.theironyard.entities.ToDoItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zach on 11/17/15.
 */
public interface ToDoItemRepository extends CrudRepository<ToDoItem, Integer> {
}
