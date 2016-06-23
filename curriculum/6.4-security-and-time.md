## Major Security Topics

* SQL injection prevention
* Cross-site scripting prevention
* SSL encryption
* Secure password storage

## Secure Password Storage

Thus far, we've been saving passwords in plaintext, which is a big mistake. If your server ever gets compromized, the intruder now has everyone's password. The solution to this is to hash the passwords before inserting them into the database, and when the user attempts to login you can simply hash the password they send and compare it to the hash in the database.

There are a few things to keep in mind about hashing passwords. If an attacker gets a database full of hashes, they can attempt to find the plaintext passwords through a brute force attack. There are two primary ways we can thwart this:

1. Use a hashing algorithm that takes a relatively long time. This will make it take longer for an attacker to brute force the hashes.
2. Use a salt -- i.e., a random number that you add to the password before hashing and store alongside the hash. This prevents an attacker from pre-generating hashes based on common passwords (known as rainbow tables).

A common hashing algorithm is SHA1. One problem, though, is that it is designed to be fast. To securely store it, we should run SHA1 at least a thousand times by taking the output of the previous hashing and running it through SHA1 again. A standardized way of doing this is called [PBKDF2](https://en.wikipedia.org/wiki/PBKDF2).

## GameTrackerSpring

Let's make a few changes to the project to make it better organized and more secure. We'll start with organization. So far, we've made all our files under the `com.theironyard` package. Most Spring projects will organize different kinds of classes into subpackages. In your IDE, refactor your classes so they fall under the following subpackages:

* `controllers`
  * `src/main/java/com/theironyard/controllers/GameTrackerController.java`
* `entities`
  * `src/main/java/com/theironyard/entities/Game.java`
  * `src/main/java/com/theironyard/entities/User.java`
* `services`
  * `src/main/java/com/theironyard/services/GameRepository.java`
  * `src/main/java/com/theironyard/services/UserRepository.java`

Now let's work on secure password storage. Instead of trying to implement the aforementioned features ourselves, we'll use a ready-made class that implements PBKDF2 as well as salting. Download [PasswordStorage.java](https://raw.githubusercontent.com/defuse/password-hashing/master/PasswordStorage.java) and move it into the following path in your project: `src/main/java/com/theironyard/utilities/PasswordStorage.java`

You may need to add the correct package name, `com.theironyard.utilities`, at the top of the file if your IDE doesn't do it for you. Then modify the `/login` route to use this class:

```java
@Controller
public class GameTrackerController {
    ...
    
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
    
    ...
}
```

Also modify your `init` method to use it as well:

```java
@Controller
public class GameTrackerController {
    ...
    
    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            User user = new User();
            user.name = "Zach";
            user.password = PasswordStorage.createHash("hunter2");
            users.save(user);
        }
    }
    
    ...
}
```

Before trying it out, go to `psql` and run `DROP DATABASE gametracker;` and then `CREATE DATABASE gametracker;` again. Since the old `users` table wasn't using hashes, it will no longer work, so we need to start fresh to try it out.

## Time

In the old days, the main way of tracking time in Java was `java.util.Date`. The fact that most of its methods are now deprecated is a stark indicator of its problems. While still in common use, the `Date` class has a number of puzzling design choices:

1. The year starts at 1900, so you'd give it the year 2000 with `d.setYear(100)`.
2. The month is a zero-indexed integer, so you'd give it the month of January with `d.setMonth(0)`.
3. The day, however, is not zero-indexed, so you'd give it the first day of the month with `d.setDate(1)`.
4. It's a mutable object (as indicated by the previous examples), so a date object can be unexpectedly changed by any method it is passed to.
5. It has no concept of timezones.

The `java.util.Calendar` class was later introduced to fix a few of the problems, but most of them remained. For a long time, the best advice was to ignore both classes and use the [Joda Time](http://www.joda.org/joda-time/) library instead. In Java 8, it was added to the standard library under the `java.time` package.

## CalendarSpring

Create a project via Spring Initializr called `CalendarSpring` with the following libraries:

* Web
* DevTools
* Mustache
* JPA
* H2
* PostgreSQL

Import it into IntelliJ. Now open `psql` and create a database for this project via `CREATE DATABASE calendar;`. Then open `src/main/resources/application.properties` and add the following:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/calendar
spring.jpa.generate-ddl=true
```

Start by creating your entities in `src/main/java/com/theironyard/entities/`:

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
```

```java
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    LocalDateTime dateTime;

    @ManyToOne
    User user;

    public Event() {
    }

    public Event(String description, LocalDateTime dateTime, User user) {
        this.description = description;
        this.dateTime = dateTime;
        this.user = user;
    }
}
```

Then create `src/main/resources/templates/home.html`:

```html
<html>
<body>
{{^user}}
<form action="/login" method="post">
    <input type="text" placeholder="Enter your name" name="name"/>
    <button type="submit">Login</button>
</form>
{{/user}}

{{#user}}
Welcome, {{name}}!<br>
<form action="/logout" method="post">
    <button type="submit">Logout</button>
</form>

<form action="/create-event" method="post">
    <input type="text" placeholder="Description" name="description" size="50"/>
    <input type="datetime-local" name="dateTime" value="{{now}}"/>
    <button type="submit">Add Event</button>
</form>
{{/user}}

{{#events}}
{{description}} {{dateTime}}<br>
{{/events}}
</body>
</html>
```

Then create your repositories in `src/main/java/com/theironyard/services/`:

```java
public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByName(String name);
}
```

```java
public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findAllByOrderByDateTimeDesc();
}
```

Finally, create `src/main/java/com/theironyard/controllers/CalendarSpringController.java`:

```java
@Controller
public class CalendarSpringController {
    @Autowired
    EventRepository events;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        List<Event> eventEntities = events.findAllByOrderByDateTimeDesc();
        if (userName != null) {
            User user = users.findFirstByName(userName);
            model.addAttribute("user", user);
            model.addAttribute("now", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
        model.addAttribute("events", eventEntities);
        return "home";
    }

    @RequestMapping(path = "/create-event", method = RequestMethod.POST)
    public String createEvent(HttpSession session, String description, String dateTime) {
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            Event event = new Event(description, LocalDateTime.parse(dateTime), users.findFirstByName(userName));
            events.save(event);
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String name) {
        User user = users.findFirstByName(name);
        if (user == null) {
            user = new User(name);
            users.save(user);
        }
        session.setAttribute("userName", name);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
```

Now let's write a few tests. Spring Boot has already created a `src/test` directory, but we need to create a special resources directory so we can make it use different database settings than our actual application. To do so, go to `File -> Project Structure` and, in the `Modules` section, create a directory at `src/test/resources` and mark it as "Test Resources". Then create a file at `src/test/resources/application.properties` with the following:

```
spring.datasource.url=jdbc:h2:mem:test
spring.jpa.generate-ddl=true
```

This will cause our tests to use an in-memory database. In the existing test file called `CalendarSpringApplicationTests.java`, create autowire a few objects that we will use to send fake HTTP requests to our routes:

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalendarSpringApplication.class)
@WebAppConfiguration
public class CalendarSpringApplicationTests {

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@Before
	public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}
}
```

Let's start by testing our `/login` route. We'll use `mockMvc` to send the POST request to it, and then we bring in the `UserRepository` to make sure a user was created after the request is run:

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalendarSpringApplication.class)
@WebAppConfiguration
public class CalendarSpringApplicationTests {

    @Autowired
	UserRepository users;

	...
	
	@Test
    public void testLogin() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                .param("name", "TestUser")
        );

        Assert.assertTrue(users.count() == 1);
    }
}
```

Finally, let's test our `/create-event` route. Notice that we are using the `sessionAttr` method to manually set the session attribute; otherwise, our server will not realize we are logged in. We then have to bring in `EventRepository` to check that it worked.

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalendarSpringApplication.class)
@WebAppConfiguration
public class CalendarSpringApplicationTests {

    @Autowired
	EventRepository events;

	...
	
	@Test
    public void testAddEvent() throws Exception {
        testLogin();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-event")
                .param("description", "Test event")
                .param("dateTime", LocalDateTime.now().toString())
                .sessionAttr("userName", "TestUser")
        );

        Assert.assertTrue(events.count() == 1);
    }
}
```
