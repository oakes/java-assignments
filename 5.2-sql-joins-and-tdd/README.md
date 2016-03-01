# SQL Joins and Test-Driven Development

![screenshot](screenshot.jpg)

## Description

Add a database to the CRUD app you built last weekend.

## Requirements

* Create `insertUser` and `selectUser`
  * Create an `insertUser` method, which creates a new record in the `users` table.
  * Create a `selectUser` method, which returns a `User` object for the given username.
  * Create a test for `insertUser` and `selectUser`.
* Create `insertEntry`, `selectEntry`, and `selectEntries`
  * Create an `insertEntry` method, which creates a new record for the thing you're tracking.
  * Create a `selectEntry` method, which returns a new object for the given id.
    * This query should use an `INNER JOIN` between your users and entries table.
  * Create a test for `insertEntry` and `selectEntry`.
  * Create a `selectEntries` method, which returns an arraylist of all objects you are tracking.
    * This query should use an `INNER JOIN` between your users and entries table.
  * Create a test for `selectEntries`.
* Create `updateEntry` and `deleteEntry`
  * Create an `updateEntry` method, which updates all the values for a given id.
  * Create a `deleteEntry` method, which deletes an entry with the given id.
  * Create a test for `updateEntry` and `deleteEntry`.
* Remove your global arraylist/hashmap that stores users and entries. Use your five new methods in your project so it now is backed by a database.
