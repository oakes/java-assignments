## General

* Use industry-standard tools
  * JDK 8
  * IntelliJ + [Java REPL](https://plugins.jetbrains.com/plugin/7215?pr=)
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

## Week 1 - "Data first, code second"

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

## Week 2 - "Writing real programs"

* The main method
* Flow control
  * Conditionals
  * Loops

## Week 3 - "Beyond the main class"

* Packages
* Imports
* Inheritance
  * @Overload
  * Casting
  * Interfaces and abstract classes

## Week 4 - "Catching errors"

* Debugging
* Testing (JUnit)

## Week 5 - "Writing to the disk"

* File I/O
* Database I/O

## Week 6 - "Third-party libraries"

* Maven
* Make projects using [Jodd](http://jodd.org/)

## Week 7 - "Third-party frameworks"

* Frameworks
  * Spring
  * Hibernate
  * Struts

## Week 8 - "Review"

* Combine material from prior weeks
* Interview prep
  * Sorting Algorithms
  * Big-O notation

## Week 9 - "The cutting edge"

* Threads
* Immutable data
* Functional Java
