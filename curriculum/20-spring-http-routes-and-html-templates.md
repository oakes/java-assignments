## HelloSpring

Spring is a commonly used framework for building web services. We'll be using Spring Boot in particular, which is a version of Spring that allows you to generate a preconfigured project to get started quickly.

* Go to [Spring Initializr](https://start.spring.io/)
* Choose "Gradle Project"
* Group is `com.theironyard` and artifact is `HelloSpring`
* Click "Switch to the full version"
* Check the following options:
  * Web
  * Mustache
  * DevTools
* Download and unzip the project

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/assets/spring-1.png)

Now that the project is on your computer, you can import it into IntelliJ:

* Import into IntelliJ
* Choose "Import project from external model" and select Gradle
* Click Next and Finish

It may take some time for IntelliJ to import the project at first, because it needs to download all of Spring's dependencies. Future Spring projects of the same version should import much more quickly. Once it's ready, we'll start by creating a simple GET route that displays information about a person. To begin, create a file at `src/main/resources/templates/person.html` with the following:

```html
<html>
<body>
{{#person}}
{{name}} from {{city}}, {{age}} years old
{{/person}}
</body>
</html>
```

Now create `src/main/java/com/theironyard/HelloSpringController.java`:

```java
@Controller
public class HelloSpringController {
    @RequestMapping(path = "/person", method = RequestMethod.GET)
    public String person(Model model, String name, String city, int age) {
        Person p = new Person(name, city, age);
        model.addAttribute("person", p);
        return "person";
    }
}
```

Now we can test it out. Try going a URL like http://localhost:8080/person?name=Alice&city=Charleston&age=30 and verify that the three values are displayed on the page. Now let's see how Spring makes it easy to create a JSON route as well. First, we'll need a class to represent this data, so create `src/main/java/com/theironyard/Person.java`:

```java
public class Person {
    String name;
    String city;
    int age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }
}
```

Generate getters and setters for the class as well. Next, create `src/main/java/com/theironyard/HelloSpringJsonController.java`:

```java
@RestController
public class HelloSpringJsonController {
    @RequestMapping(path = "/person.json", method = RequestMethod.GET)
    public Person jsonHome(String name, String city, int age) {
        return new Person(name, city, age);
    }
}
```

Note that we're using `@RestController` this time, and we're returning the object we want to serialize into JSON. Try it out by going to http://localhost:8080/person.json?name=Alice&city=Charleston&age=30. Now let's try making a simple login system to learn how POST routes work. First, create `src/main/resources/templates/home.html`:

```html
<html>
<body>
{{#name}}Hello, {{.}}!{{/name}}

{{^name}}
<form action="/login" method="post">
    <input type="text" placeholder="Enter your name" name="userName"/>
    <button type="submit">Login</button>
</form>
{{/name}}
</body>
</html>
```

Now add a `/` route to `HelloSpringController`:

```java
@Controller
public class HelloSpringController {
    ...

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute("userName"));
        return "home";
    }
}
```

Now you should see the login form at http://localhost:8080 because `name` is null. Let's create the POST route now:

```java
@Controller
public class HelloSpringController {
    ...

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
}
```

Note that we are returning a special string that causes the browser to redirect to `/`. This app should now allow you to login. Lastly, let's learn how to build a JAR. Since we are using Gradle, instead of IntelliJ's built-in build system as we've done in the past, the process is different:

* Click the Gradle tab on the right edge of the window
* HelloSpring -> Tasks -> build -> build
* It will be in `build/libs`
