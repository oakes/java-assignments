## Week 5 - Databases

### Day 1

* Review assignment (crud)
* Exercise
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
* Create HelloDatabase
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
      * The bad way: `String.format("INSERT INTO players VALUES ('%s', true, 10, 90.5)", scannerName)`
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
* Create ToDoDatabase (based on the original ToDo project)
  * Add the H2 JAR
  * Create `Connection` and `Statement`
  * `CREATE TABLE IF NOT EXISTS todos (text VARCHAR, is_done BOOLEAN)`
  * Create `insertTodo` with `PreparedStatement`
    * `INSERT INTO todos VALUES (?, false)`
  * Call `insertTodo` when `optionNum == 1`
  * Create `selectTodos` that returns `ArrayList<ToDoItem>`
    * `SELECT * FROM todos`
  * Remove the original `ArrayList<ToDoItem> todos` declaration
  * Call `selectTodos` at the top of the while loop
  * Create `toggleTodo`
    * `UPDATE todos SET is_done = NOT is_done WHERE ROWNUM = ?`
  * Call `toggleTodo` when `optionNum == 2`

### Day 2

* Review assignment
* The `id IDENTITY` column
* Joins

### Day 3

* H2 (continued)

### Day 4

* H2 (continued)
