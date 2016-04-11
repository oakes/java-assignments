## ForumWeb

Let's make a web version of the Forum project we did before. Create a new project called `ForumWeb`. In `Project Structure -> Libraries`, add two libraries:

* `com.sparkjava:spark-core:2.3`
* `com.sparkjava:spark-template-mustache:2.3`

In `Project Structure -> Modules`, create a folder called "resources" and mark it as such. Inside of that, create a folder called "templates". This will hold our HTML templates.

We'll start with our data. The first thing we should do is make a class to represent a message:

```java
public class Message {
    int id;
    int replyId;
    String author;
    String text;

    public Message(int id, int replyId, String author, String text) {
        this.id = id;
        this.replyId = replyId;
        this.author = author;
        this.text = text;
    }
}
```

Then we should make a class for our user. This time we'll store a password:

```java
public class User {
    String name;
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
```

Start off the main class by creating our `HashMap`. Instead of storing our `ArrayList` inside the `User` class, like we did before, we'll create a separate `ArrayList<Message>` that stores everyone's messages. This is more appropriate for a forum, since messages from various users are often displayed on the same page:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
    }
}
```

Now let's write a few methods to insert test data into our data structures, so we can continuously test the app without manually inserting information. Notice how the ID system works: the messages each get a unique ID and a reply ID for whatever message it is responding to. For top-level messages, the reply ID is -1:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        addTestUsers();
        addTestMessages();
    }
    
    static void addTestUsers() {
        users.put("Alice", new User("Alice", ""));
        users.put("Bob", new User("Bob", ""));
        users.put("Charlie", new User("Charlie", ""));
    }

    static void addTestMessages() {
        messages.add(new Message(0, -1, "Alice", "Hello world!"));
        messages.add(new Message(1, -1, "Bob", "This is another thread!"));
        messages.add(new Message(2, 0, "Charlie", "Cool thread, Alice."));
        messages.add(new Message(3, 2, "Alice", "Thanks"));
    }
}
```

Let's start working on `resources/templates/home.html` which lists the author and text of each message:

```html
<html>
<body>
{{#messages}}
{{author}}: {{text}}<br>
{{/messages}}
</body>
</html>
```

Now create the `/` route to send down the HTML:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        addTestUsers();
        addTestMessages();
        
        Spark.init();
        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    ArrayList<Message> threads = new ArrayList<>();
                    for (Message message : messages) {
                        if (message.replyId == -1) {
                            threads.add(message);
                        }
                    }
                    m.put("messages", threads);
                    return new ModelAndView(m, "home.html");
                }),
                new MustacheTemplateEngine()
        );
    }
    
    ...
}
```

This should be enough to see the top-level messages (i.e., the ones with a `replyId` of -1).
