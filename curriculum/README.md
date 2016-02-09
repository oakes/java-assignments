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

## Week 1 - Intro

* Data
  * Create scalar and compound variables in a REPL
  * Scalar (int, double)
  * Compound (Array, Class, ArrayList, HashMap)
    * Sequential data vs associative data
      * Array (sequential)
      * Class (associative)
    * Static vs dynamic data
      * ArrayList (dynamic sequential)
      * HashMap (dynamic associative)
* Code
  * Class is not just a data structure; it can contain code (methods)
  * Create a blank project in the IDE
  * Create methods that initialize data the same way we did in the REPL
  * Static vs non-static, public vs private
  * Control flow (conditionals, loops, recursion, exceptions)

## Week 2 - Console programs

* The main method
* Packages
* Imports
* Inheritance
  * @Overload
  * Casting
  * Interfaces
* File I/O and JSON
* Maven
* Debugging
* Testing (JUnit)

## Week 3 - Graphical programs

* Desktop: [JavaFX](http://docs.oracle.com/javase/8/javase-clienttechnologies.htm)
* Mobile: [Android](http://developer.android.com/index.html)
* Games: [libGDX](https://libgdx.badlogicgames.com/)

## Week 4 - Web apps

* [Spark](http://sparkjava.com/)
* Basic web apps using Mustache to deliver server-generated HTML pages
* Use in-memory data structures (primarily `ArrayList` and `HashMap`) to store data for now

## Week 5 - Databases

* [H2](http://www.h2database.com/html/main.html)
* Send raw SQL queries over JDBC, not a SQL wrapper, so they learn the manual way
* Using an embedded database makes the setup process much easier than an external one

## Week 6-7 - Spring

* [Spring Boot](http://projects.spring.io/spring-boot/)
* [PostgreSQL](http://postgresapp.com/)
* Use Hibernate instead of raw SQL now

## Week 8-9 - Clojure

* See the [Clojure curriculum](https://github.com/oakes/clojure-assignments)
