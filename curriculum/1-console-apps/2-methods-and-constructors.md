## Methods and Constructors

A class is more than just an associative data structure; it can contain code. In fact, in Java, all code must exist inside a class. Modify the `Person` class from yesterday to create getters and setters. Then create a constructor.

It may seem silly to create getters and setters. Why not access and set the fields directly like we did yesterday? They can be useful when you want to enforce constraints. For example, don't set the name unless it has a space.

Now let's look at some built-in methods in the `String` class. Open JREPL:

* `String s = “Hello, world!”;`
* `s.charAt(0);`
* `s.contains("Hello");`
* `s.equals("Hello, world!");`
* `s.indexOf("world");`
* `s.split(" ");`
* `s.startsWith("Hello");`
* `s.substring(0, 5);`
* `s.toUpperCase();`

Look at all the methods available in [the JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html).

Let's modify the `Person` class to add constraints.

* Add constraint in `setAge` so it only sets the age if it is a positive number
* Add constraint in `setName` so it only sets the name if there is a space in it
* Refector the conditional check into `isValidName` (this is an example of breaking code out into a separate method)
