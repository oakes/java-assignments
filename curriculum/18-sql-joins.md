## Joins

Imagine we want to create a database for our GameTracker project. The naive way to do that would be to create a single table called `games` like this:

| id IDENTITY | name VARCHAR | genre VARCHAR | release_year INT | username VARCHAR |
| ---- | ---- | ---- | ---- | ---- |
| 1 | Lunar 2: Eternal Blue | RPG | 1995 | Alice |

In this example, a user named Alice inserted a game. Consider, though, if we wanted to track other things. If we add a password to our app, where should we store it? Should we create another column called `password`? That would cause it to be stored in every game Alice decides to add to the database. What we really want is to store user and game information in separate tables called `users` and `games`, and store the user's ID with each game they create:

| id IDENTITY | name VARCHAR | password VARCHAR |
| ---- | ---- | ---- |
| 1 | Alice | hunter2 |
| 2 | Bob | password |

| id IDENTITY | name VARCHAR | genre VARCHAR | release_year INT | user_id INT |
| ---- | ---- | ---- | ---- | ---- |
| 1 | Lunar 2: Eternal Blue | RPG | 1995 | 1 |
| 2 | Goldeneye | FPS | 1995 | 1 |
| 3 | Age of Empires | RTS | 1997 | 2 |

If you wanted to write a query that retrieved only Alice's games, it would look like this:

```sql
SELECT * FROM games INNER JOIN users ON users.id = games.user_id WHERE users.id = 1
```

There are other kinds of joins, but we'll stick with inner joins for now. For a nice overview of the different kinds of joins, see [Visual Representation of SQL Joins](http://www.codeproject.com/Articles/33052/Visual-Representation-of-SQL-Joins).

## ForumWeb

Let's add a database to our ForumWeb project. To do so, we're going to practice test-driven development (TDD). We'll write all our SQL methods and verify they work by writing a test right after. Only when our tests are passing will we actually start using those methods. Start by writing a method to create our tables:

```java
public class Main {
    public static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, name VARCHAR, password VARCHAR)");
        stmt.execute("CREATE TABLE IF NOT EXISTS messages (id IDENTITY, user_id INT, reply_id INT, text VARCHAR)");
    }
    
    ...
}
```

Next, create methods to insert and select a single user:

```java
public class Main {
    ...

    public static void insertUser(Connection conn, String name, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, password);
        stmt.execute();
    }

    public static User selectUser(Connection conn, String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
        stmt.setString(1, name);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            int id = results.getInt("id");
            String password = results.getString("password");
            return new User(id, name, password);
        }
        return null;
    }
    
    ...
}
```

We now have enough to start writing tests. Add JUnit to the project by going to `File -> Project Structure -> Libraries` and adding `junit:junit:4.12` via Maven. Then go to the `Modules` section, create a folder at `src/test`, and mark it as a test folder (green color). Finally, make sure your main class is open and go to `Navigate -> Test` and choose `Create New Test...`. Make sure JUnit 4 is selected in the dialog and create the Test class.

In your new `MainTest` class, create a method for your tests to use to create a database connection. We'll make it use an in-memory database for tests:

```java
public class MainTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTables(conn);
        return conn;
    }
}
```

Now let's create a method to test inserting and selecting a `User`:

```java
public class MainTest {
    ...
    
    @Test
    public void testUser() throws SQLException {
        Connection conn = startConnection();
        Main.insertUser(conn, "Alice", "");
        User user = Main.selectUser(conn, "Alice");
        conn.close();
        assertTrue(user != null);
    }
}
```

Run the test and make sure it passes.
