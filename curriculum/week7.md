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
  * Make custom methods return `Page<Purchase>` and use the return value to selectively show the "Next" link
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
* Fork the [DebugCalendarSpring](../projects/DebugCalendarSpring) project
  * Fix all the bugs until you can create and display events
  * Write tests for the routes
    * Create `src/test/resources` with its own `application.properties`
    * Create an `@Before` method that clears the test database
    * Create an autowired `WebApplicationContext` and a `MockMvc`
    * Add `testLogin` to the test file (use `MockMvcRequestBuilders.post`)
    * Add `testAddEvent` to the test file (use `MockMvcRequestBuilders.post`)
    * Set mock username with `sessionAttr`
  * Add updating and deleting along with tests
  * Add paging

### Day 2

* Review assignment (spring - paging and tdd)
* Topics
  * JSON API + AJAX
  * Uploading files
* Install [JSONView](https://chrome.google.com/webstore/detail/jsonview/chklaanhfefbnpoihckbnefhakgolnmc?hl=en) chrome extension
* Create IronGram
  * Create project from template with the following options
    * Web
    * DevTools
    * JPA
    * H2
    * PostgreSQL
  * Create initial server-side code
    * Modify `application.properties`
      * `spring.datasource.url=jdbc:h2:./main`
      * `spring.jpa.generate-ddl=true`
      * `spring.jpa.hibernate.ddl-auto=none`
    * Create `IronGramController` with `@RestController`
    * Create `User` with `username` and `password`
    * Create `Photo` with `sender`, `recipient`, and `filename`
    * Create `UserRepository` and `PhotoRepository`
    * Copy `PasswordHash.java` into project
    * In the controller, add the repositories and create the `/login`, `/logout`, and `/user` routes
  * Create public folder
    * Create `WebConfig` which extends `WebMvcConfigurerAdapter`
      * Override `addResourceHandlers` to add `"public/**"`
    * Download [jQuery](http://jquery.com/download/) and move it into `public`
    * Create `public/index.html` with jquery included
    * Create login, logout, and upload forms
    * Create a `<script>` tag that uses `$.get("/user", getUser)` and conditionally shows the forms based on the return data
  * Create upload route
    * In the controller, create the `/upload` route with a `MultipartFile` as the file argument
      * Read username from session and throw exception if null
      * Use `File.createTempFile` and `FileOutputStream` to write to disk
      * Create `Photo` object
      * Throw exception if recipient is null
  * Show photos
    * Create `/photos` route
    * Add `$.get("/photos", getPhotos)` to the `<script>` tag
  * Write tests for the routes
    * Create `src/test/resources` with its own `application.properties`
    * Create an `@Before` method that clears the test database
    * Create an autowired `WebApplicationContext` and a `MockMvc`
    * Add `testLogin` to the test file (use `MockMvcRequestBuilders.post`)
    * Add `testUpload` to the test file (use `MockMvcRequestBuilders.post`)

### Day 3

* Review assignment
* Reading documentation
* Enable CORS
  * https://spring.io/guides/gs/rest-service-cors/
* Using REST APIs
* Deploy to a server
* Git
  * Merge conflicts
  * Branches
  * Command line interface
  * .gitignore
