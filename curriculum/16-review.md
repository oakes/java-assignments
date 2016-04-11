## Layers of the Internet

It is important to understand all the technologies we are relying on when making web apps. Let's spend some time talking about the primary layers of the internet and what each provides:

* Physical layer (hardware)
  * Wireless
  * Electrical (ethernet, coaxial)
  * Fiber optics
* Internet layer (packets)
  * IPv4
  * IPv6
* Transport layer (reliability)
  * UDP (checksum)
  * TCP (checksum and retransmission)
* Application layer (end users)
  * ASCII
  * UTF-8
  * HTTP
  * HTTP/2

## ForumWeb

We will now return to ForumWeb to add the ability to make a post. First we'll add the requisite form to the HTML:

```html
<html>
<body>
...

{{#userName}}
Welcome, {{.}}!
<form action="/logout" method="post">
    <button type="submit">Logout</button>
</form>
<br>
<form action="/create-message" method="post">
    <input type="text" placeholder="Enter your message" name="messageText" width="200"/>
    <button type="submit">Post Message</button>
</form>
{{/userName}}

...
</body>
</html>
```

Then add the necessary post route:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/create-message",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");
                    if (userName == null) {
                        throw new Exception("Not logged in.");
                    }

                    String text = request.queryParams("messageText");
                    if (text == null) {
                        throw new Exception("Didn't get necessary query parameters.");
                    }

                    Message m = new Message(messages.size(), -1, userName, text);
                    messages.add(m);

                    response.redirect("/");
                    return "";
                })
        );
    }
    
    ...
}
```

This works, but it only allows us to post top-level messages. It would be better if the post we wrote was marked as a reply to whichever post we are currently looking at. We can do this by first sending down the current page's `replyId` to the template:

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
                    ...
                    
                    m.put("messages", threads);
                    m.put("userName", userName);
                    m.put("replyId", replyIdNum);
                    return new ModelAndView(m, "home.html");
                }),
                new MustacheTemplateEngine()
        );
        
        ...
    }
    
    ...
}
```

Now we can modify the HTML to send back the correct `replyId` via a hidden input field:

```html
<html>
<body>
...

{{#userName}}
Welcome, {{.}}!
<form action="/logout" method="post">
    <button type="submit">Logout</button>
</form>
<br>
<form action="/create-message" method="post">
    <input type="text" placeholder="Enter your message" name="messageText" width="200"/>
    <input type="hidden" value="{{replyId}}" name="replyId"/>
    <button type="submit">Post Message</button>
</form>
{{/userName}}

...
</body>
</html>
```

Finally, we can modify out POST route to use this instead of hard-coding -1 as the `replyId`:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/create-message",
                ((request, response) -> {
                    ...

                    String text = request.queryParams("messageText");
                    String replyId = request.queryParams("replyId");
                    if (text == null || replyId == null) {
                        throw new Exception("Didn't get necessary query parameters.");
                    }
                    int replyIdNum = Integer.valueOf(replyId);

                    Message m = new Message(messages.size(), replyIdNum, userName, text);
                    messages.add(m);

                    response.redirect("/");
                    return "";
                })
        );
    }
    
    ...
}
```

Currently it always redirects you back to the top-level of the forum site. To make it say on whatever page you are currently on, you can tell it to redirect you to the referrer URL:

```java
public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        ...
        
        Spark.post(
                "/create-message",
                ((request, response) -> {
                    ...

                    response.redirect(request.headers("Referer"));
                    return "";
                })
        );
    }
    
    ...
}
```

Let's pretend we are deploying this to a real server by creating a standalone JAR file (often called a "fat" JAR because it contains everything we need to run our code):

* File -> Project Structure...
* Click "Artifacts" and then the plus button
* JAR -> From modules with dependencies...
* Choose the main class and click OK
* Build -> Build Artifacts...

You should now see a JAR file in `out/artifacts/ForumWeb_jar`. You can simply repeat the last step to build a new JAR file. You can try running it in IntelliJ's terminal by running `java -jar out/artifacts/ForumWeb_jar/ForumWeb.jar`.
