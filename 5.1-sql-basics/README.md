# SQL Basics

![screenshot](screenshot.jpg)

## Description

Fork the [BeerTrackerDatabase](../projects/BeerTrackerDatabase) project. It is a copy of the BeerTracker project we did in class. Modify it to store its information in an H2 database. Add an edit form which updates the relevant item in the database. The final app should look like the screenshot below.

## Requirements

* Create the `Connection` and execute a query to create a `beers` table that stores the beer name and type.
* Write a static method `insertBeer` and run it in the `/create-beer` route. It should insert a new row with the user-supplied information.
* Write a static method `deleteBeer` and run it in the `/delete-beer` route. It should remove the correct row using `id`.
* Write a static method `selectBeers` that returns an `ArrayList<Beer>` containing all the beers in the database.
* Remove the global `ArrayList<Beer>` and instead just call `selectBeers` inside the "/" route.
* Add a form to edit the beer name and type, and create an `/edit-beer` route. Write a static method `updateBeer` and use it in that route. Then redirect to "/".
* Optional: Add a search form which filters the beer list to only those beers whose name contains the (case-insensitive) search string.

![screenshot](screenshot.png)
