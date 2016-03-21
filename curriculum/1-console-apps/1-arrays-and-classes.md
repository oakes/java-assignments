## Install Party

The following things should be installed (ideally before class starts):

* Install [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)
* Install the JREPL IntelliJ plugin
  * Preferences -> Browse repositories… -> Search for “JREPL”
* Install the Xcode command line tools
  * Type `git` in the terminal. If a dialog appears, click `Install` and go through the steps.

## Intro to Java

Start up IntelliJ and create a project called `HelloWorld` using the Java template. This will serve as a scratchpad throughout the course when we need to make one-off files to demonstrate language features. For now, we'll just start up JREPL to experience the language without the boilerplate needed in a real project. Start JREPL by going to `Tools -> Start JREPL`.

Create scalar variables:

* `int score = 0;`
* `score = score + 1;`
* `score += 1;`
* `double health = 100;`
* `health = health * 0.75;`
* `health *= 0.75;`
* `boolean didWin = false;`
* `didWin = score > 0;`

Sometimes we want to store multiple things together. A variable that holds multiple pieces of data is called a data structure. We'll start with two simple ones: An array, which holds data in a list (sequential), and a class, which holds data as key-value pairs (associative).

Create arrays and classes:

* `double[] position = {1.0, 1.5};`
  * Alternatively:
  * `double[] position = new double[2];`
  * `position[0] = 1.0;`
  * `position[1] = 1.5;`
* `class Position {double x; double y;}`
  * Then create an object (an instance of the Position class):
  * `Position p = new Position();`
  * `p.x = 1.0;`
  * `p.y = 1.5;`

The difference between a class and an object is much like a blueprint vs a house. You can have many houses built from the same basic blueprint. There are many built-in classes, like the `String` class for holding text:

* `String s = “Hello, world!”;`

Arrays and classes have different advantages. Arrays are great for storing lists of things, while classes are great for storing things with a finite number of known fields.

* Array examples
  * To-do list
  * Web browser history
* Class examples
  * A form (in a doctor's office)
  * A tweet

Now let's try to write a class in a file. Create a `Person` class like this:

```java
public class Person {
    public String name;
    public int age;
    public boolean isAlive;
}
```

Make sure the fields are public! JREPL can't access non-public fields. Now, to load this into JREPL, we need to do two things:

1. Go to `Built -> Rebuild Project`
2. Click the restart button in JREPL

Now you have access to it:

* `Person p = new Person();`
* `p.name = "Alice";`
* `p.age = 30;`
* `p.isAlive = true;`

## Intro to Git

We track changes with a VCS for many reasons. Firstly, because it makes collaboration easier. Secondly, because it allows us to revert mistakes. Thirdly, because it acts as an offsite backup for our code.

VCSes have been around for decades. The first popular open source VCS as called CVS (1990). It suffered from corruption issues in some cases. It was eventually replaced by SVN (2000), which followed the same centralized model but without the corruption problems. Then came Git (2005), which introduced distributed versioning, allowing people to commit incrementally and push only when they are ready (or when they have an available internet connection).

Create a repo in the HelloWorld project. Commit the files and push to a Github repository.
