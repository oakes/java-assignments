## Why Use Databases

In the past, our primary way of saving data to the disk was to serialize it into a large JSON-encoded string and write it out to a file. This works well for small amounts of data, such as config or game save files, because it is easy to implement, and the resulting files are relatively easy for a human to read and modify. There is, however, a downside. JSON files can't be read or updated incrementally; you must read and parse the entire file in memory, and overwrite the entire file when you want to update it.

For larger data sets, it becomes important to incrementally read and update it. This is what databases provide us with. We'll be focusing in particular on relational databases, which organize data into tables with rows and columns (conceptually similar to Excel spreadsheets). To interact with it, we'll be using a language called SQL (Structured Query Language), developed by IBM in the 70s.

## Basic SQL Commands and Data Types

There are only five basic commands you need to know to be productive with SQL. You need to be able to create a table, and then you need to know how to create, read, update, and delete records in that table. The commands look like this:

* `CREATE TABLE`
* `INSERT`
* `SELECT`
* `UPDATE`
* `DELETE`

Let's start with creating a table. Doing so requires us to specify all the columns we want in the table. Every column needs a name and a type. This is similar to defining fields in a Java class. For example, let's say we want to make a table that stores the players in a game. We want to keep track of the the player's name, whether or not they are alive, their score, and their health. In Java, we would use `String`, `boolean`, `int`, and `double` for these respectively. In SQL, we'll use these types:

* `VARCHAR`
* `BOOLEAN`
* `INT`
* `DOUBLE`

With this in mind, we can write all five commands to create the table and create/read/update/delete a player in the table. Keep in mind that capitalization isn't important, but generally built-in keywords are written in all caps and table/column names are in snake case (lower case with underscores).

* `CREATE TABLE players (name VARCHAR, is_alive BOOLEAN, score INT, health DOUBLE)`
* `INSERT INTO players VALUES ('Bob', true, 0, 100.0)`
* `SELECT * FROM players WHERE name = 'Bob'`
* `UPDATE players SET is_alive = FALSE WHERE name = 'Bob'`
* `DELETE FROM players WHERE name = 'Bob'`

## HelloDatabase

Create a new project called `HelloDatabase`. In `File -> Project Structure -> Libraries`, add the following library from Maven:

* `com.h2database:h2:1.4.191`

H2 is an embedded SQL database, which means it runs inside your project and stores its data in a single file on your disk. Later on we will try using an external database like PostGreSQL, which will run outside of our project, but for now we can stick to H2 so we don't need to worry about starting and stopping our database. Another nice thing about H2 is that it has a built-in web interface for interacting with the database. In your main class, write just a single line to start it:

```java
public class Main {
    public static void main(String[] args) throws SQLException {
        Server.createWebServer().start();
    }
}
```

After running it, open your web browser and go to http://localhost:8082 where you will see a login page. Change the JDBC URL to `jdbc:h2:./main`, which will save the database in a file called `main.mv.db` in your project's directory. Make sure the username and password are blank and click Connect.

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/assets/sql-basics-1.png)
