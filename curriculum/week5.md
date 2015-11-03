## Week 5 - Databases

### Day 1

* Review assignment (crud)
* Exercise (read people.csv via `Scanner` into arraylist and run case-insensitive search)
* Saving data to the disk
  * Serialize to JSON vs using a database
  * JSON files can't be updated incrementally
    * You must continuously serialize the entire `HashMap` and overwrite the file
  * Databases let you read/write small portions of the entire data at a time
* Relational databases
  * Organize information into tables with rows and columns
  * Use a language called SQL (Structured Query Language)
    * Developed by IBM in the 70s
  * Most common commands
    * `CREATE TABLE`
    * `INSERT`
    * `SELECT`
    * `UPDATE`
    * `DELETE`
  * Most common data types
    * `VARCHAR`
    * `BOOLEAN`
    * `INT`
    * `DOUBLE`
  * Player example
    * `CREATE TABLE players (name VARCHAR, is_alive BOOLEAN, score INT, health DOUBLE)`
* Embedded vs external databases (SQLite/H2 vs MySQL/PostgreSQL)
* Create [HelloDatabase](../projects/HelloDatabase)
  * Download and add [H2](http://www.h2database.com/html/main.html) JAR file to project
  * Work with the database directly (by running the JAR file from the Terminal tab)
    * Use `jdbc:h2:./main` as the JDBC URL
    * Make the user name blank
  * Work with the database from Java (by writing code in `Main.java`)
    * Create a `Connection`
      * `Connection conn = DriverManager.getConnection("jdbc:h2:./main")`
    * Create a `Statement`
    * When creating a table, you should use `CREATE TABLE IF NOT EXISTS`
    * When selecting, use the `executeQuery` method, loop over the `ResultSet`, and print each column
    * Update and delete before the select statement
    * Run `DROP TABLE players` at the end
    * Demonstrate injecting values into query after the `DELETE` statement
      * The bad way: `String.format("INSERT INTO players VALUES ('%s', true, 10, 90.5)", inputName)`
        * SQL injection: `', true, 0, 0); DROP TABLE players; --`
      * The good way: `PreparedStatement`
  * For both methods, execute the following commands
    * `CREATE TABLE players (name VARCHAR, is_alive BOOLEAN, score INT, health DOUBLE)`
    * `INSERT INTO players VALUES ('Alice', true, 10, 90.5)`
    * `INSERT INTO players VALUES ('Bob', true, 9, 95)`
    * `SELECT * FROM players`
    * `SELECT * FROM players WHERE name = 'Bob'`
    * `SELECT * FROM players WHERE health > 0`
    * `UPDATE players SET score=20 WHERE name='Alice'`
    * `SELECT * FROM players`
    * `DELETE FROM players WHERE name='Alice'`
    * `SELECT * FROM players`
* Create [ToDoDatabase](../projects/ToDoDatabase) (based on the original ToDo project)
  * Add the H2 JAR
  * Create `Connection` and `Statement`
  * `CREATE TABLE IF NOT EXISTS todos (id IDENTITY, text VARCHAR, is_done BOOLEAN)`
  * Create `insertTodo` with `PreparedStatement`
    * `INSERT INTO todos VALUES (NULL, ?, false)`
  * Call `insertTodo` when `optionNum == 1`
  * Create `selectTodos` that returns `ArrayList<ToDoItem>`
    * `SELECT * FROM todos`
  * Remove the original `ArrayList<ToDoItem> todos` declaration
  * Call `selectTodos` at the top of the while loop
  * Create `toggleTodo`
    * `UPDATE todos SET is_done = NOT is_done WHERE id = ?`
  * Call `toggleTodo` when `optionNum == 2`

### Day 2

* Review assignment (sql basics - beer tracker database)
* Joins
  * Most common type is `INNER JOIN`
  * There is also `LEFT JOIN`, `RIGHT JOIN`, and `FULL JOIN`
  * [Visual Representation of SQL Joins](http://www.codeproject.com/Articles/33052/Visual-Representation-of-SQL-Joins)
* Test-Driven Development (TDD)
* Create ForumWebDatabase
  * Create `Connection`
  * Create `createTables` tables which defines the `users` and `messages` tables
  * Add `id` to `User` class
  * Create `static void insertUser(Connection conn, String username, String password)`
  * Create `static User selectUser(Connection conn, String username)`
  * Create a test for `insertUser` and `selectUser` called `testUser`
    * Add junit from Maven
    * Create `src/test` and mark it as a test dir
    * Create `startConnection` and `endConnection`
  * Create `static void insertMessage(Connection conn, int replyId, int userId, String text)`
  * Create `static Message selectMessage(Connection conn, int id)`
  * Create a test for `insertMessage` and `selectMessage` called `testMessage`
  * Create `static ArrayList<Message> selectMessages(Connection conn, int replyId)`
  * Create a test for `selectMessages` called `testMessages`
  * Delete the global `users` and `messages` collections and the test methods
  * Use new methods
    * Use `insertUser` and `selectUser` in `/login`
    * Use `insertMessage` in `/create-message`
    * Use `selectMessage` in `/replies`
    * Use `selectMessages` in `/` and `/replies`

### Day 3

* Review assignment
* [JOOQ](http://www.jooq.org/)
  * In HelloDatabase, duplicate the functionality using JOOQ
* Review serializing to JSON
* Paging with `OFFSET` and `LIMIT`

### Day 4

* H2 + JOOQ (continued)
