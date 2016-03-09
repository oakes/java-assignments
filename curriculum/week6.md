## Week 6 - Spring

### Day 1

* Create [HelloSpring](../projects/HelloSpring)
  * Create project from template
    * Go to [Spring Initializr](https://start.spring.io/)
    * Choose "Gradle Project"
    * Group is `com.theironyard` and artifact is `HelloSpring`
    * Click "Switch to the full version"
    * Check the following options:
      * Web
      * Mustache
      * DevTools
    * Download and unzip the project
    * Import into IntelliJ
    * Choose "Import project from external model" and select Gradle
    * Click Next and Finish
  * MVC
    * Model (SQL)
    * View (HTML)
    * Controller (Java)
  * Create `/person` route that returns HTML
    * `src/main/resources/templates/person.html`
    * `src/main/java/com/theironyard/HelloSpringController.java`
    * Add arguments for name, city and age, and use `@RequestParam` to set default values
  * Create `/person.json` route that returns JSON
    * `src/main/java/com/theironyard/Person.java`
    * `src/main/java/com/theironyard/HelloSpringJsonController.java`
    * Add arguments for name, city, and age, and use `@RequestParam` to set default values
  * Create `/` route that returns HTML
    * `src/main/resources/templates/home.html`
    * Add `home` method to `HelloSpringController`
    * Add `login` method to `HelloSpringController`
* Build JAR file
  * Click the Gradle tab on the right edge of the window
  * HelloSpring -> Tasks -> build -> build
  * It will be in `build/libs`

### Day 2

* Exercise (reverse number)
* Java build systems
  * Ant (2000)
    * First major Java build tool
    * Uses `build.xml`
  * Maven (2004)
    * Introduced downloadable dependencies
    * Uses `pom.xml`
  * Gradle (2012)
    * Customizable, uses a real programming language
    * Can use Maven libraries
    * Uses `build.gradle`
  * IDE-specific projects (IntelliJ and Eclipse)
* PostgreSQL
  * Download and run [Postgres.app](http://postgresapp.com/)
  * Open `psql`
  * `\l`
  * `\du`
  * `CREATE DATABASE hellodb;`
  * `\c hellodb`
  * `CREATE TABLE test (id SERIAL, stuff VARCHAR);`
  * `\dt`
  * `INSERT INTO test VALUES (DEFAULT, 'hello world');`
  * `SELECT * FROM test;`
  * `DROP TABLE test;`
* HelloDatabase
  * Download and add library: [PostgreSQL Driver](https://jdbc.postgresql.org/download/postgresql-9.4-1205.jdbc42.jar)
  * Change connection URL to `"jdbc:postgresql://localhost:5432/hellodb"`
  * Use `SERIAL` instead of `IDENTITY`
  * Use `DEFAULT` instead of `NULL`
  * Use `DECIMAL` instead of `DOUBLE`
* Writing direct SQL queries
  * Problems
    * Dialects differ between databases
    * Easy to make typos
    * Going between the database and Java objects is busy work
  * Solutions
    * SQL wrapper libraries: JOOQ
    * Object-Relational Mapping libraries: Hibernate
* Create [GameTrackerSpring](../projects/GameTrackerSpring)
  * Create project from template with the following options
    * Web
    * DevTools
    * JPA
    * Mustache
    * PostgreSQL
  * Create `gametracker` database in psql
  * In `src/main/resources/application.properties` add:
    * `spring.datasource.url=jdbc:postgresql://localhost:5432/gametracker`
    * `spring.jpa.generate-ddl=true`
  * Create `Game` class that uses `@Entity`, `@Id`, and `@GeneratedValue`
  * Create `src/main/resources/templates/home.html`
  * Create `GameTrackerController` with a `/` and `/add-game` route
  * Create `GameRepository` interface that extends `CrudRepository`
  * Use `@Autowired` to bring the repo into the controller
  * In `/add-game`, create a `Game` object and save it to the repo
  * In `/`, add the games to the `Model`

### Day 3

* GameTrackerSpring
  * Add genre filter
    * In `home.html`, add links for each game genre
    * Add `findByGenre` to `GameRepository`
    * Modify the `/` route to use it if the `genre` parameter isn't null
  * Add genre and release year filter
    * Add `findByGenreAndReleaseYear` to `GameRepository`
    * Modify the `/` route to use it if the `genre` and `releaseYear` parameters aren't null
    * Add `findByGenreAndReleaseYearIsGreaterThanEqual` to `GameRepository`
  * More query methods
    * `findFirstByGenre`
    * `countByGenre`
    * `findByGenreOrderByNameAsc`
    * [Documentation](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)
  * Add search form
    * In `home.html`, add search form
    * Add `searchByName` to `GameRepository` with `@Query`
  * Add a user class and do joins
    * Create `User` with `@Table(name = "users")`
    * Create `src/main/resources/templates/login.html`
    * Create `/login` route and return the template in the `/` route
    * Create `/logout` route and add link in `home.html`
    * Create `UserRepository` interface with `findOneByName`
    * Add `UserRepository` to the controller and use it in the `/login` route
    * Add `User` to `Game` with `@ManyToOne`
    * Edit `home.html` to show the username next to each item
    * Add `List<Game>` to `User` with `@OneToMany(mappedBy = "user")`
    * Add a `showMine` parameter to the `/login` route and a link to `home.html`
    * Insert a default user at startup by creating an init method with `@PostConstruct`

### Day 4

* Major security topics
  * SQL injection prevention
  * Cross-site scripting prevention
  * SSL encryption
  * Secure password storage
* GameTrackerSpring
  * Add a secure login system
    * Add password field to `login.html` and the `User` class
    * Make the `/login` route take a password and make both params required
    * Download [`PasswordHash.java`](https://crackstation.net/source/password-hashing/PasswordHash.java) and use it to store and validate the password
    * Drop and create database
