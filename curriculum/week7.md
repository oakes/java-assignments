## Week 7 - Spring

### Day 1

* Review assignment (spring - crud)
* Add paging support to the "Purchases" assignment
  * Change `CrudRepository` to `PagingAndSortingRepository`, and add `Pageable pageable` to the custom method
  * Make the `/` route take an `int page` that defaults to `"0"`
  * Create a `PageRequest` object and pass it into the query methods
  * Add `nextPage` to the model and add the "Next" link to `home.html`
  * Add `page` to the model and add the `page` param to the filter links
  * Add `category` to the model and add the `category` param to the "Next" link
* BeerTrackerSpring
  * Prevent null values
    * The "Billion Dollar Mistake"
    * Prevent null in the routes: Use primitive types for everything but `String`
    * Prevent null in the database: Non-nullable columns

### Day 2

* Review assignment
* Create a JSON API via `@RestController`
* AJAX
* Enable CORS
  * https://spring.io/guides/gs/rest-service-cors/
* Git
  * Merge conflicts
  * Branches
  * Command line interface
  * .gitignore

### Day 3

* Review assignment
* Deploy to a server
