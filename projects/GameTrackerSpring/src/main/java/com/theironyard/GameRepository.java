package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by zach on 3/8/16.
 */
public interface GameRepository extends CrudRepository<Game, Integer> {
}
