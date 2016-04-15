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
