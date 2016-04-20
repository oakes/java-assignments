## GameTrackerSpring

Sometimes you'll want to query just a subset of your data. Let's add a way to view all the games marked with a specific genre. We'll need to add links in `home.html` that pass the genre as a query parameter:

```html
<html>
<body>
<a href="/">All</a>
<a href="/?genre=adventure">Adventure</a>
<a href="/?genre=rpg">RPG</a>
<a href="/?genre=strategy">Strategy</a>
<a href="/?genre=shooter">Shooter</a>

<br><br>

...
```

Then we need to create a special method that querys by genre. To do so, we must go to `GameRepository` and add the following method signature:

```java
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByGenre(String genre);
}
```

Note that the name of this method is sematically meaningful. Any method called `findBySomething(String something)` will produce a SQL query that filters the records by a column named `something`.

Now we must change the `/` route to accept a `genre` query parameter and use the custom query method if it isn't null:

```java
@Controller
public class GameTrackerController {
    ...
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String genre) {
        List<Game> gameList;
        if (genre != null) {
            gameList = games.findByGenre(genre);
        } else {
            gameList = games.findAll();
        }
        model.addAttribute("games", gameList);
        return "home";
    }
    
    ...
}
```

The links we added to the main page should now work. Just for fun, let's add a filter for release year. Add `findByReleaseYear` to your `GameRepository`:

```java
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByGenre(String genre);
    List<Game> findByReleaseYear(int year);
}
```

Then add the necessary parameter to the `/` route. We need to use a boxed integer (`Integer`) rather than the primitive version (`int`). This is because the boxed version can be set to null, whereas the primitive version must have a value set. We want this parameter to be optional, and the only way to make it so is to allow it to be set to null:

```java
@Controller
public class GameTrackerController {
    ...
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String genre, Integer releaseYear) {
        List<Game> gameList;
        if (genre != null) {
            gameList = games.findByGenre(genre);
        } else if (releaseYear != null) {
            gameList = games.findByReleaseYear(releaseYear);
        } else {
            gameList = games.findAll();
        }
        model.addAttribute("games", gameList);
        return "home";
    }
    
    ...
}
```

You can test that out by adding a game with a given release year, like `1995`, and then going to `http://localhost:8080/?releaseYear=1995`.

There are many endless ways to write query methods. Reference [the documentation](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation) to learn the rules. Here is an example of the possibilities. Notice that you can also use raw SQL with the `@Query` annotation if all else fails:

```java
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByUser(User user);
    List<Game> findByReleaseYear(int year);
    List<Game> findByGenreAndReleaseYear(String genre, int releaseYear);
    List<Game> findByReleaseYearIsGreaterThanEqual(int minReleaseYear);

    Game findFirstByGenre(String genre);
    int countByGenre(String genre);
    List<Game> findByGenreOrderByNameAsc(String genre);

    @Query("SELECT g FROM Game g WHERE g.platform LIKE ?1%")
    List<Game> findByPlatformStartsWith(String platform);
}
```
