## Anonymous Classes and Functions

Defining classes and methods is a fundamental activity, but sometimes the normal syntax for doing so is too verbose for what you're trying to achieve. There is a special way of defining both that is more terse and can be embedded inside a block of code. We call them "anonymous" because they don't even need a name like normal classes and methods do. Open the Zoo project so we can try them out.

Let's say we want to make an object representing an alligator. Using what we know so far, we would define a class called `Alligator` which extends `Reptile`. It would override the `makeSound` method to output something like "Croak". If we only intend to use this object once, however, defining a new class is a bit of overkill.

Make a new class called `AnonymousClassExample`. Here, we will make a small main method that defines an alligator object using an anonymous class. It essentially is defining a class and instantiating it at the same time:

```java
public class AnonymousClassExample {
    public static void main(String[] args) {
        Reptile alligator = new Reptile() {
            @Override
            public void makeSound() {
                System.out.println("Croak");
            }
        };
        alligator.name = "Alligator";

        alligator.makeSound();
    }
}
```

It turns out that there is an equivalent feature for methods, called anonymous functions (also known as lambdas). A lambda is sort of like defining a method inside some larger block of code rather than in its own separate code block. Like anonymous classes, it isn't given an explicit name.

Make a new class called `AnonymousFunctionExample`. First make a normal method called `sayHello` and run it. Then define a lambda with the type of `Runnable` and immediately run it. Notice that they both achieve the same thing, but the lambda has a few advantages: it allows simple methods to be defined inline rather than requiring you to look for it elsewhere, and it allows you to pass a method around as an object.

```java
public class AnonymousFunctionExample {
    public static void main(String[] args) {
        // Run code from a separate method
        sayHello();

        // Run code from a lambda
        Runnable r2 = () -> {
            System.out.println("Hello from a lambda!");
        };
        r2.run();
    }

    static void sayHello() {
        System.out.println("Hello from a method!");
    }
}
```

## HelloSpark

Create a new project called `HelloSpark`. In `Project Structure -> Libraries`, add two libraries:

* `com.sparkjava:spark-core:2.3`
* `com.sparkjava:spark-template-mustache:2.3`

In `Project Structure -> Modules`, create a folder called "resources" and mark it as such. Inside of that, create a folder called "templates". This will hold our HTML templates.

To kick things off, let's define write some HTML at `resources/templates/home.html`:

```html
<html>
<body>
Hello, {{name}}!
</body>
</html>
```

Then, in our main class, we can make a GET route to return that file:

```java
public class Main {
    public static void main(String[] args) {
        Spark.init();
        
        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    m.put("name", "Alice");
                    return new ModelAndView(m, "home.html");
                }),
                new MustacheTemplateEngine()
        );
    }
}
```

When you run the project, you should now see the page at http://localhost:4567. Now let's create a way to log in, and after doing so, make the aforementioned page display whatever name we login with. First, create a login page at `resources/templates/login.html`:

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

Then, make a simple `User` class:

```java
public class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}
```

Now we can define a static field to store our user, and modify the GET route to return the login page if the user is null:

```java
public class Main {
    static User user;
    
    public static void main(String[] args) {
        Spark.init();
        
        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        m.put("name", user.name);
                        return new ModelAndView(m, "home.html");
                    }
                }),
                new MustacheTemplateEngine()
        );
    }
}
```

Finally, create the POST route that the login page hits:

```java
public class Main {
    static User user;
    
    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/login",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    user = new User(name);
                    response.redirect("/");
                    return "";
                })
        );
    }
}
```
