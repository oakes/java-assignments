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
