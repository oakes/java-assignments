## Week 5 - Databases

### Day 1

* Saving data to the disk
  * Serialize to JSON vs using a database
  * JSON files can't be updated incrementally
    * You must continuously serialize the entire `HashMap` and overwrite the file
  * Databases let you read/write small portions of the entire data at a time
* Relational databases
  * Organize information into tables with rows and columns
  * Use a language called SQL (Structured Query Language)
    * Developed by IBM in the 70s
  * To-do example
    * Users table: id, name, password
    * Todos table: id, user_id, text, is_done
* Embedded vs external databases (SQLite/H2 vs MySQL/PostgreSQL)
* HelloDatabase
  * Download and add [H2](http://www.h2database.com/html/main.html) JAR file to project
  * Work with the database directly (by running the JAR file from the Terminal tab)
    * Use `jdbc:h2:./main` as the JDBC URL
  * Work with the database from Java (by writing code in `Main.java`)
    * Create a `Connection`
      * `Connection conn = DriverManager.getConnection("jdbc:h2:./main")`
    * Create a `Statement`
      * Create a table called `users`

### Day 2

* H2 (continued)

### Day 3

* H2 (continued)

### Day 4

* H2 (continued)
