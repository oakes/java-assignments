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
  * Make custom methods return `Page` and use the return value to selectively show the "Next" link
* BeerTrackerSpring
  * Use subpackages for better organization
    * Controllers go in `controllers`
    * Entities go in `entities`
    * Repositories go in `services`
    * Make fields public when necessary
  * Prevent null values
    * The "Billion Dollar Mistake"
    * Prevent null in the routes: Use primitive types for everything but `String`
    * Prevent null in the database: Non-nullable columns
* Fork the CalendarSpring project
  * Fix all the bugs until you can create and display events
  * Write tests for the routes
  * Add updating and deleting along with tests

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
* Reading documentation
* Using REST APIs
* Deploy to a server
