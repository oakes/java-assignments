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
