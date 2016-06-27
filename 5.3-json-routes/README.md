# JSON Routes

[![screenshot](screenshot.jpg)](http://xeubie.tripod.com/ff11jpn/)

## Description

Create a project called `RegistrationForm`. Download [angular.zip](https://github.com/oakes/java-assignments/raw/master/curriculum/assets/angular.zip) and extract it, then rename the folder to `public` and move it into the root of your project. This is a fully complete client-side web app, and you will need to write the backend for it using Spark and H2.

## Requirements

* Create the project and make sure `public` is in the right spot. Add the following libraries:
  * `com.h2database:h2:1.4.192`
  * `com.sparkjava:spark-core:2.3`
  * `org.jodd:jodd-json:3.7.1`
  * `junit:junit:4.12`
* Connect to the database and create a table with four columns: `id`, `username`, `address`, and `email`.
* Create a `User` class with the same four fields. Make sure the `id` field is `Integer` instead of `int`, so it can be set to null if necessary when parsing the JSON. Also remember to create getter methods.
* Write the four SQL methods:
  * `selectUsers`
  * `insertUser`
  * `updateUser`
  * `deleteUser`
* Write tests for the four methods.
* Create a GET route called `/user` that  calls `selectUsers` and returns the data as JSON.
* Create a POST route called `/user` that parses `request.body()` into a `User` object and calls `insertUser` to put it in the database.
* Create a PUT route called `/user` that parses `request.body()` into a `User` object and calls `updateUser` to update it in the database.
* Create a DELETE route called `/user/:id` that gets the `id` via `request.params(":id")` and gives it to `deleteUser` to delete it in the database.
* Make sure all four functions work in the app. When you hit "Edit", it should populate the form with that user's data and then allow you to update it by clicking "Add" again.
