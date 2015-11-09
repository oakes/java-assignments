## Week 6 - Spring

### Day 1

* Create HelloSpring
  * Create project from template
    * Go to [Spring Initializr](https://start.spring.io/)
    * Choose "Gradle Project"
    * Group is `com.theironyard` and artifact is `HelloSpring`
    * Click "Switch to the full version"
    * Check the following options:
      * Web
      * Mustache
      * PostgreSQL
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
* Java build tools
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
  * IntelliJ's internal build system
    * Good for simple projects
    * Can use Maven libraries
    * Uses `.idea`
* Build JAR file
  * Click the Gradle tab on the right edge of the window
  * HelloSpring -> Tasks -> build -> build
  * It will be in `build/libs`

### Day 2

* PostgreSQL
  * Download and run [Postgres.app](http://postgresapp.com/)
  * Open `psql`
  * `\l`
  * `\du`
  * `CREATE DATABASE hellodb;`
  * `\c hellodb`
  * `CREATE TABLE test (id SERIAL, stuff VARCHAR);`
  * `INSERT INTO test VALUES (DEFAULT, 'hello world');`
  * `SELECT * FROM test;`
  * `DROP TABLE test;`
* HelloDatabase
  * Download and add library: [PostgreSQL Driver](https://jdbc.postgresql.org/download/postgresql-9.4-1205.jdbc42.jar)
  * Change connection URL to `"jdbc:postgresql://localhost:5432/hellodb"`
  * Use `DECIMAL` instead of `DOUBLE`
