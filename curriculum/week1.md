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
  * Create HelloWorld IntelliJ project
  * Start the REPL
    * Tools -> Start JREPL
  * Scalar variables
    * int
    * double
    * boolean
  * Compound variables (data structures)
    * Sequential vs associative
    * Arrays and classes
      * Position array
      * Position class
    * Class vs object (blueprint vs house)
    * Built-in classes
      * Essentially the entire language consists of classes
      * `String` is one of the most common
    * Array examples
      * To-do list
      * Web browser history
    * Class examples
      * A form (ex. in a doctor's office)
      * A tweet
  * Writing code in a file
    * Create `Person` class in a file
    * Use the class in JREPL
    * You need to add "public" to access it from JREPL
* Intro to Git
  * Why track changes?
    * Collaboration
    * Revert mistakes
    * Offsite backup
  * Interesting comments from [Tarn Adams](https://www.reddit.com/r/IAmA/comments/1avszc/im_tarn_adams_of_bay_12_games_cocreator_of_dwarf/c919fo8) on version control
    * Find a middle ground between good practice and getting things done
  * History of VCSes
    * CVS (1990)
    * SVN (2000)
    * Git (2005)
  * Create a repo in the HelloWorld project via IntelliJ
  * Write code, commit, and push
  * Show diff

### Day 2

* Review assignment (arrays and classes)
* Methods
  * A class is more than just a data structure; it can contain code
  * All code must exist inside a class
  * Create `setName` and `getName` methods
    * Can be useful over direct field access if you want to enforce constraints
    * Interesting comments from [Notch](http://notch.tumblr.com/post/15782716917/coding-skill-and-the-decline-of-stagnation) on getters and setters
  * Create a constructor that takes a name
* Work through examples of using string methods in the REPL
  * Look up the Java doc for `String`
  * Call methods
    * `charAt`
    * `contains`
    * `indexOf`
    * `split`
    * `startsWith`
    * `substring`
    * `toUpperCase`
    * `trim`
  * Mutation
    * Strings are immutable; their methods return a new string if necessary
    * Mutation is a common source of bugs; a variable can contain something you didn't expect
  * Create `getFirstName` and `getLastName`
* Static methods and fields
  * They're stored in the class rather than in each object
  * Use static methods if it is "standalone" (no need to access fields)
  * Static methods and fields (look at the ones in `Math` and `System`)

### Day 3

* Review assignment (methods)
* Create a [text adventure game](../projects/TextAdventure)
  * Demonstrate use of arrays/classes, static methods, and control flow
* Use `Scanner` to read console input
  * Unlike output, input requires creating an object
  * Book analogy: Reading requires a bookmark, but writing is simply appending at the end
* Control flow
  * Conditionals
  * Loops
  * Recursion
  * Exceptions

### Day 4

* Review assignment (control flow - ATM)
* Data structures
  * Review arrays and classes
  * Static vs dynamic
  * Create ArrayLists and HashMaps
  * Contrast the four data structures
    * Array (static sequential)
    * Class (static associative)
    * ArrayList (dynamic sequential)
    * HashMap (dynamic associative)
* Text adventure
  * Refactor code into `Player` class
  * Add command system
  * Use `ArrayList` to store inventory
* How do static and dynamic data structures work?
  * Stack vs Heap
