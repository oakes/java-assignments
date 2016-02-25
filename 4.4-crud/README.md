# CRUD

[![screenshot](screenshot.png)](https://www.youtube.com/watch?v=6tgWH-qXpv8&list=PLoUHkRwnRH-KTCH3tJ9WvsWWPEgUu-y6d)

## Description

Design your very own CRUD app using Spark. It must support user authentication (login and logout) and creating/reading/updating/deleting entries. You can store any kind of information you want (within the bounds of appropriateness).

## Requirements

* Choose something you'd like to "track" in a web app. It could be physical objects (beer, books, etc), but doesn't have to be.
* User authentication
  * If not logged in, show a login form at the top (it can double as your create account form, like in the ForumWeb project).
  * If logged in, display the username and a logout button at the top.
* Create: If logged in, display a form to create a new entry.
* Read: Whether logged in or not, list whatever entries were created by the users.
* Update: If logged in, show an edit link next to the entries created by that user. Use a hidden field to specify which item to edit.
* Delete: If logged in, show a delete button next to the entries created by that user. Clicking it should delete the item and refresh the page. Use a hidden field to specify which item to delete.
* Compile the project as a JAR file and upload it to the "Releases" section of your repo.
* Optional: Add paging to your list of entries.
* Optional: Add CSS (served statically via a `public` folder).
