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
