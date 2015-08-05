## General

* Use industry-standard tools
  * JDK 8
  * IntelliJ + [JREPL](https://plugins.jetbrains.com/plugin/7892)
* Focus on data structures
  * Emphasize the distinction between code and data
  * Think about the data first, and the code will follow
  * Design projects specifically around use of various data structures
* Start with a Java REPL instead of overwhelming students with a full project structure
  * Starting off with a typical "Hello world" project is problematic, as it assumes the following:
    * Knowing what a class is
    * Knowing what `public static void` is
    * Knowing what `String[] args` is
  * With a REPL, we can introduce these concepts one at a time and jump into a real project later

## Week 1 - Data first, code second

* Data
  * Create scalar and compound variables in a REPL
  * Scalar (int, char, String)
  * Compound (Array, Class, ArrayList, HashMap)
    * Distinguish between sequential vs key-value data
      * Create a primitive array (sequential)
      * Create a class (key-value)
    * Distinguish between static vs dynamic data
      * Create an ArrayList (dynamic sequential)
      * Create a HashMap (dynamic key-value)
* Code
  * Class is not just a data structure; it can contain code (methods)
  * Create a blank project in the IDE
  * Create methods that initialize data the same way we did in the REPL
  * Discuss scope
  * Discuss static vs non-static, public vs private
  * Console I/O
  * Flow control
    * Conditionals
    * Loops

## Week 2 - Writing real programs

* The main method
* Packages
* Imports
* Inheritance
  * @Overload
  * Casting
  * Interfaces and abstract classes

## Week 3 - Exploring the standard library

* java.math
* java.io
* java.time
* java.swing

## Week 4 - Catching errors

* Debugging
* Testing (JUnit)

## Week 5 - Third-party libraries

* Maven
* Make projects using [Jodd](http://jodd.org/)
* Use [Ratpack](http://ratpack.io/)

## Week 6 - Databases

* Database I/O
* [H2](http://www.h2database.com/html/main.html)
* Hibernate

## Week 7 - Putting it all together

* Spring
* Deploying with Tomcat

## Week 8 - The cutting edge

* Threads
* [Quasar](http://docs.paralleluniverse.co/quasar/)
* Functional Java
