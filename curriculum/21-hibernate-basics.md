## Overview of Build Systems

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

## PostGreSQL

Thus far, we've used the H2 embedded database for our projects. To get experience with an external database, we'll start using PostGreSQL. The difference is that we'll need to explicitly start it up and create a database for our project before running the project. Let's begin by installing it and using the `psql` tool:

* Download and run [Postgres.app](http://postgresapp.com/)
* Open `psql` via the menubar
* `\l` lists existing databases
* `\du` lists existing users
* `CREATE DATABASE hellodb;` creates a new database
* `\c hellodb` connects to the database
* `\dt` lists the tables in the connected database
* Try some commands
  * `CREATE TABLE test (id SERIAL, stuff VARCHAR);`
  * `INSERT INTO test VALUES (DEFAULT, 'hello world');`
  * `SELECT * FROM test;`
  * `DROP TABLE test;`

Notice how PostGreSQL's SQL isn't quite the same as H2's. The auto-incrementing column type is `SERIAL` instead of `IDENTITY`, and when inserting we specify `DEFAULT` as its value instead of `NULL`. This is one of the problems with writing raw SQL queries. As we've learned, it is also easy to make typos in SQL strings and it involves a lot of busy work. To solve this problem, some people opt to use an ORM (Object Relational Mapping) library such as Hibernate.

## GameTrackerSpring

Let's create the same game tracker web app we made previously, but this time using Spring and Hibernate. First, create the project via Spring Initializr called `GameTrackerSpring` with the following libraries:

* Web
* DevTools
* Mustache
* JPA
* H2 (useful for testing)
* PostgreSQL

Import it into IntelliJ. Now open `psql` and create a database for this project via `CREATE DATABASE gametracker;`. Then open `src/main/resources/application.properties` and add the following:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/gametracker
spring.jpa.generate-ddl=true
```

Let's start by creating an entity, which is just a normal class that Hibernate will automatically create a table for. Create `src/main/java/com/theironyard/Game.java`:

```java
@Entity
public class Game {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String platform;

    @Column(nullable = false)
    String genre;

    @Column(nullable = false)
    int releaseYear;

    public Game() {
    }

    public Game(String name, String platform, String genre, int releaseYear, User user) {
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.user = user;
    }
}
```
