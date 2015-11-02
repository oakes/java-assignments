# SQL Basics

![screenshot](screenshot.jpg)

## Description

Fork the [BeerTrackerDatabase](../projects/BeerTrackerDatabase) project. It is a copy of the BeerTracker project we did in class. Modify it to store its information in an H2 database. Add an edit form which updates the relevant item in the database. The final app should look like the screenshot below.

## Requirements

* Create the `Connection` and execute a query to create a `beers` table that stores the beer name and type.
* Write a static method `insertBeer` and run it in the `/create-beer` route. It should insert a new row with the user-supplied information.
* Write a static method `deleteBeer` and run it in the `/delete-beer` route. It should remove the correct row using `ROWNUM`.
* Write a static method `selectBeers` that returns an `ArrayList<Beer>` containing all the beers in the database.
  * Hint: Since we aren't storing `id` in a column, you'll need to get it another way. Just create `int id = 1;` before you loop over the `ResultSet`, and then just continuously run `id++;` at the end of the loop.
* Remove the global `ArrayList<Beer>` and instead just call `selectBeers` inside the "/" route.
* Add a form to edit the beer name and type, and create an `/edit-beer` route that executes the necessary `UPDATE` query to edit it in the database. Then redirect to "/".
* Optional: Add a search form which filters the beer list to only those beers whose name contains the (case-insensitive) search string.

![screenshot](screenshot.png)
