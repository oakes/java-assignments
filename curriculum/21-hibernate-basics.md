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

## PostgreSQL

Thus far, we've used the H2 embedded database for our projects. To get experience with an external database, we'll start using PostgreSQL. The difference is that we'll need to explicitly start it up and create a database for our project before running the project. Let's begin by installing it and using the `psql` tool:

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

Notice how PostgreSQL's SQL isn't quite the same as H2's. The auto-incrementing column type is `SERIAL` instead of `IDENTITY`, and when inserting we specify `DEFAULT` as its value instead of `NULL`. This is one of the problems with writing raw SQL queries. As we've learned, it is also easy to make typos in SQL strings and it involves a lot of busy work. To solve this problem, some people opt to use an ORM (Object Relational Mapping) library such as Hibernate.

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

Let's start by creating an entity, which is just a normal class that Hibernate will automatically create a table for. Create `src/main/java/com/theironyard/Game.java`. The `id` column is always given special annotations marking it as such and making it auto-increment. Every other column should be marked with `@Column(nullable = false)` to make sure null values aren't accidentally inserted into the database (unless you intend for that to be possible).

```java
@Entity
@Table(name = "games")
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

    public Game(String name, String platform, String genre, int releaseYear) {
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}
```

After restarting your project, go back to `psql`. Make sure you are connected to the `gametracker` database and run the `\dt` command. Notice that Hibernate has automatically created a table called "games"!

Next, create `src/main/resources/templates/home.html`:

```html
<html>
<body>
<form action="/add-game" method="post">
    <input type="text" placeholder="Name" name="gameName"/>
    <select name="gameGenre">
        <option selected disabled>Genre</option>
        <option value="adventure">Adventure</option>
        <option value="rpg">RPG</option>
        <option value="strategy">Strategy</option>
        <option value="shooter">Shooter</option>
    </select>
    <select name="gamePlatform">
        <option selected disabled>Platform</option>
        <option value="pc">PC</option>
        <option value="ps4">PS4</option>
        <option value="xbone">XBox One</option>
        <option value="wiiu">Wii U</option>
    </select>
    <input type="number" placeholder="Release Year" name="gameYear"/>
    <button type="submit">Add Game</button>
</form>

<ol>
{{#games}}
    <li>{{name}} {{platform}} {{releaseYear}}</li>
{{/games}}
</ol>
</body>
</html>
```

Now create `GameTrackerSpringController`:

```java
@Controller
public class GameTrackerController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
```

We now need to learn how to learn how to save a game into the database. To do so, we need to create an interface called `GameRepository`:

```java
public interface GameRepository extends CrudRepository<Game, Integer> {
}
```

Now back in our controller, we will bring in this repository via a special annotation:

```java
@Controller
public class GameTrackerController {
    @Autowired
    GameRepository games;
    
    ...
}
```

We can then create our `/add-game` route and use this repository to save a game to the database:

```java
@Controller
public class GameTrackerController {
    ...
    
    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(String gameName, String gamePlatform, String gameGenre, int gameYear) {
        Game game = new Game(gameName, gamePlatform, gameGenre, gameYear);
        games.save(game);
        return "redirect:/";
    }
}
```

That's all we need to do to create an entry into the games table! Now we need to learn how to do the equivalent of `SELECT * FROM games`, so we can display the games in the page.

```java
@Controller
public class GameTrackerController {
    ...
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        List<Game> gameList = games.findAll();
        model.addAttribute("games", gameList);
        return "home";
    }
    
    ...
}
```
