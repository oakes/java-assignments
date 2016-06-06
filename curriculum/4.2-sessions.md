## Mustache

The mustache template language may seem deceptively simple, but it's quite powerful. The basic idea is that we store values in a `HashMap`, which is passed to mustache to be injected into the page. Let's review how mustache works with different data types:

* `String name;`
  * Welcome, {{name}}!
* `ArrayList<String> names;`
  * {{#names}} {{.}} {{/names}}
* `Message msg;`
  * {{#message}} {{text}} by {{username}} {{/message}}
* `ArrayList<Message> messages;`
  * {{#messages}} {{text}} by {{username}} {{/messages}}
* Conditional (truthy)
  * {{#name}}Welcome, {{name}}!{{/name}}
* Conditional (falsey)
  * {{^name}}Please login.{{/name}}

## HelloSpark

Let's add multi-user support to HelloSpark. First, change the static `user` variable to be a `HashMap<String, User>`:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();
    
    ...
}
```

Update the POST route to save the user object into the `HashMap` *and* save the username into the session, so subsequent connections will know which user is currently logged in:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/login",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    User user = users.get(name);
                    if (user == null) {
                        user = new User(name);
                        users.put(name, user);
                    }

                    Session session = request.session();
                    session.attribute("userName", name);

                    response.redirect("/");
                    return "";
                })
        );
    }
}
```

Then update the GET route to get the correct `User` object from the `HashMap`:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();

                    Session session = request.session();
                    String userName = session.attribute("userName");
                    User user = users.get(userName);

                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        m.put("name", user.name);
                        return new ModelAndView(m, "home.html");
                    }
                }),
                new MustacheTemplateEngine()
        );
        
        ...
    }
}
```

Now let's add a logout button. Add a form to home.html:

```html
<html>
<body>
<form action="/logout" method="post">
    <button type="submit">Logout</button>
</form>
Hello, {{name}}!
</body>
</html>
```

Then add the appropriate route:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/logout",
                ((request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );
    }
}
```

## GameTracker

Let's make a web app to track an inventory of video games. Create a new project called `GameTracker`. In `Project Structure -> Libraries`, add two libraries:

* `com.sparkjava:spark-core:2.3`
* `com.sparkjava:spark-template-mustache:2.3`

In `Project Structure -> Modules`, create a folder called "resources" and mark it as such. Inside of that, create a folder called "templates". This will hold our HTML templates.

Start by creating `resources/templates/login.html`:

```html
<html>
<body>
<form action="/login" method="post">
    <input type="text" placeholder="Enter your name" name="loginName"/>
    <button type="submit">Login</button>
</form>
</body>
</html>
```

We'll create a temporary `resources/templates/home.html` as well:

```html
<html>
<body>
Hello, world!
</body>
</html>
```

Make a class called `Game` to represent the thing we are tracking:

```java
public class Game {
    String name;
    String genre;
    String platform;
    int releaseYear;

    public Game(String name, String genre, String platform, int releaseYear) {
        this.name = name;
        this.genre = genre;
        this.platform = platform;
        this.releaseYear = releaseYear;
    }
}
```

Then make a `User` class which contains an `ArrayList<Game>` for all the games added by that user:

```java
public class User {
    String name;
    ArrayList<Game> games = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }
}
```

In the main class, we can start by returning the correct template:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();
        Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);

                    HashMap m = new HashMap<>();
                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    }
                    else {
                        return new ModelAndView(user, "home.html");
                    }
                }),
                new MustacheTemplateEngine()
        );
    }
}
```

Now add the requisite `/login` POST route:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/login",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    User user = users.get(name);
                    if (user == null) {
                        user = new User(name);
                        users.put(name, user);
                    }

                    Session session = request.session();
                    session.attribute("userName", name);

                    response.redirect("/");
                    return "";
                })
        );
    }
}
```

We can now fill in home.html with a form::

```html
<html>
<body>
<form action="/logout" method="post">
    <button type="submit">Logout</button>
</form>

<form action="/create-game" method="post">
    <input type="text" placeholder="Game name" name="gameName" width="200"/>
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
    <button type="submit">Submit</button>
</form>

<ol>
    {{#games}}
    <li>{{name}} - {{genre}} - {{platform}} - {{releaseYear}}</li>
    {{/games}}
</ol>
</body>
</html>
```

We then need a POST route that this form will connect with:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/create-game",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    if (user == null) {
                        throw new Exception("User is not logged in");
                    }

                    String gameName = request.queryParams("gameName");
                    String gameGenre = request.queryParams("gameGenre");
                    String gamePlatform = request.queryParams("gamePlatform");
                    int gameYear = Integer.valueOf(request.queryParams("gameYear"));
                    Game game = new Game(gameName, gameGenre, gamePlatform, gameYear);

                    user.games.add(game);

                    response.redirect("/");
                    return "";
                })
        );
    }
}
```

Finally, add the logout route:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/logout",
                ((request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );
    }
}
```
