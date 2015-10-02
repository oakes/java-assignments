## Week 1 - Intro

### Install Party

* Install [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)
* Install the JREPL IntelliJ plugin
  * Preferences -> Browse repositories… -> Search for “JREPL”

### Day 1

* Overview of the class
* History of Java
  * Low level vs high level
  * Context of Java's birth in the 90's
  * How it works (bytecode, VM, etc)
* Intro to Java
  * Create HelloWorld IntelliJ project inside this repository
  * Start the REPL
    * Tools -> Start JREPL
  * Scalar variables
    * int
    * double
    * boolean
  * Data structures
    * Sequential vs associative
    * Arrays and classes
      * Position array
      * Position class
    * You can make arrays of objects and objects containing arrays
      * To-do array
      * Contact class
      * Two-dimensional array representing pixels
    * Show example of lack of data structure usage
    * Built-in classes
      * Essentially the entire language consists of classes
      * `String` is one of the most common
    * Scenarios - A data structure that holds...
      * A tweet
      * Web browser history
      * Every keyboard code and the character it represents
      * The points scored for each player on a team
      * The departure time for every plane in an airline's fleet
 * GitHub
   * Fork this repository and clone it
   * Walk through how to edit file and submit PR

### Day 2

* Overview of Git
* Writing code in a file
  * Open the HelloWorld project
  * Create the Contact class from yesterday in a file
  * Use the class in JREPL
  * You need to add "public" to access it from JREPL
  * A class is more than just a data structure; it can contain code
  * All code must exist inside a class
* Work through examples of using string methods in the REPL
* Methods in detail
  * Constructor methods
  * Static methods
  * System class (including System.out.println)
  * Create a static method in our project and run it in the REPL
  * Go over examples and decide whether a method should be static or not

### Day 3

* Control flow
  * Conditionals
  * Loops
  * Recursion
  * Exceptions
* Create a text-based game
  * Demonstrate use of arrays/classes, static methods, and control flow

### Day 4

* Data structures
  * Review arrays and classes
  * Static vs dynamic
  * Create ArrayLists and HashMaps
  * Contrast the four data structures
    * Array (static sequential)
    * Class (static associative)
    * ArrayList (dynamic sequential)
    * HashMap (dynamic associative)
* How do static and dynamic data structures work?
  * Stack vs Heap
* Extend the text-based game to use dynamic data structures
  * Maintain inventory
