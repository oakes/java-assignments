## HTML vs JSON routes

Thus far, we've been creating old-fashioned websites with HTML that we generate server-side using a templating engine (Mustache). We need to learn about a second, more modern approach to web apps that enforces more of a separation between the front-end and back-end. Instead of returning HTML, we can make our GET routes just return data encoded as JSON. Then, the front-end can request the data via JavaScript and generate the appropriate HTML itself.

To reiterate, the old way is:

1. Initiate request from HTML (link or form)
2. Server returns HTML
3. Browser refreshes page

The new way is:

1. Initiate request from JavaScript (AJAX)
2. Server returns JSON
3. JavaScript parses data and injects it into the page

## AjaxChat

Create a new project called `AjaxChat`. Add the following libraries via Maven:

* `com.h2database:h2:1.4.190`
* `com.sparkjava:spark-core:2.3`
* `org.jodd:jodd-json:3.6.6`
* `junit:junit:4.12`

Start with your data. We'll keep it simple by just using a single table to store our messages. Let's create a `Message` class first.

```java
public class Message {
    int id;
    String author;
    String text;

    public Message(int id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }
}
```

Make sure to add getters and setters as well, because our JSON library won't work otherwise. Then create our database:

```java
public class Main {
    public static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS messages (id IDENTITY, author VARCHAR, text VARCHAR)");
    }
    
    public static void main(String[] args) throws SQLException {
        Server.createWebServer().start();
	      Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        createTables(conn);
    }
}
```

Next we need to tell Spark to serve a folder that will contain our client-side files:

```java
public class Main {
    ...
    
    public static void main(String[] args) throws SQLException {
        ...
        
        Spark.externalStaticFileLocation("public");
        Spark.init();
    }
}
```

Now create the `public` folder in the root of your project. First, create `public/chat.js` that just contains the following:

```js
alert("Hello, world!");
```

Then download the latest [jQuery](https://jquery.com/download/) and save it to the public folder as well.

Finally, create an HTML file at `public/index.html` that brings in both JS files:

```html
<html>
<body>

<script src="jquery-2.2.3.js"></script>
<script src="chat.js"></script>

</body>
</html>
```

Make sure you can see the alert message appear at `http://localhost:4567`.

Let's return our attention to the server side. Create the necessary methods to insert and select messages:

```java
public class Main {
    ...
    
    public static void insertMessage(Connection conn, String author, String text) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO messages VALUES (NULL, ?, ?)");
        stmt.setString(1, author);
        stmt.setString(2, text);
        stmt.execute();
    }

    public static ArrayList<Message> selectMessages(Connection conn) throws SQLException {
        ArrayList<Message> messages = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM messages");
        ResultSet results = stmt.executeQuery();
        while (results.next()) {
            int id = results.getInt("id");
            String author = results.getString("author");
            String text = results.getString("text");
            messages.add(new Message(id, author, text));
        }
        return messages;
    }
    
    ...
}
```

In the main method, we can then make routes to add and retrieve messages:

```java
public class Main {
    ...
    
    public static void main(String[] args) throws SQLException {
        ...
        
        Spark.get(
                "/get-messages",
                ((request, response) -> {
                    ArrayList<Message> messages = selectMessages(conn);
                    JsonSerializer serializer = new JsonSerializer();
                    return serializer.serialize(messages);
                })
        );
        Spark.post(
                "/add-message",
                ((request, response) -> {
                    String author = request.queryParams("author");
                    String text = request.queryParams("text");
                    insertMessage(conn, author, text);
                    return "";
                })
        );
    }
}
```

Now return to `index.html` and write the code necessary form. Note that we don't actually need to create a real HTML form, since we aren't using the traditional mechanism to hit the POST route. Instead, we just need a few input elements and tell the submit button to run a JavaScript function we will write soon:

```html
<html>
<body>

<input type="text" placeholder="Author" id="author" name="author"/>
<input type="text" placeholder="Text" id="text" name="text"/>
<button onclick="addMessage()">Submit</button>

<script src="jquery-2.2.1.js"></script>
<script src="chat.js"></script>

</body>
</html>
```

In `chat.js`, write the `addMessage` function we just referenced. It should use jQuery to hit the POST route:

```js
function addMessage() {
    $.post(
        "/add-message",
        {
            author: $("#author").val(),
            text: $("#text").val()
        },
        function(data) {
            $("#text").val("");
        }
    );
}
```

You should have enough now to run the project and try submitting a message. If you go to `http://localhost:4567/get-messages` you should see JSON representing the messages currently in the database. It's a good idea to install the [JSONView](https://chrome.google.com/webstore/detail/jsonview/chklaanhfefbnpoihckbnefhakgolnmc?hl=en) chrome extension so the JSON is formatted nicely.

Now let's make it display the messages from the server. In `index.html`, create an empty element to store the messages:

```html
<html>
<body>

<input type="text" placeholder="Author" id="author" name="author"/>
<input type="text" placeholder="Text" id="text" name="text"/>
<button onclick="addMessage()">Submit</button>

<div id="messages"></div>

<script src="jquery-2.2.1.js"></script>
<script src="chat.js"></script>

</body>
</html>
```

In `chat.js`, create a `getMessages` function and make it run every second:

```js
...

function getMessages() {
    $.get(
        "/get-messages",
        function(data) {
            var messages = JSON.parse(data);
            $("#messages").empty();
            for (var i in messages) {
                var elem = $("<div>").text(messages[i].author + ": " + messages[i].text);
                $("#messages").append(elem);
            }
        }
    );
}

setInterval(getMessages, 1000);
```

You can try the app out by opening it in two separate tabs. You should be able to write a message from one tab and see it appear in the other tab.
