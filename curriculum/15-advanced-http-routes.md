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

Start off the main class by creating our `HashMap`:

```java

public class Main {
    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
    }
}
```
