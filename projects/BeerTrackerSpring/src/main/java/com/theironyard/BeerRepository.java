package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by zach on 11/10/15.
 */
public interface BeerRepository extends CrudRepository<Beer, Integer> {
}
