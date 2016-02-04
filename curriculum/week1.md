## Week 1 - Intro

### Install Party

* Install [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)
* Install the JREPL IntelliJ plugin
  * Preferences -> Browse repositories… -> Search for “JREPL”
* Install the Xcode command line tools
  * Type `git` in the terminal. If a dialog appears, click `Install` and go through the steps.

### Day 1

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
      * A form (in a doctor's office)
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
  * Modify the `Person` class
    * Create getters and setters
    * Create a constructor
  * Why getters and setters?
    * Can be useful over direct field access if you want to enforce constraints
    * Interesting comments from [Notch](http://notch.tumblr.com/post/15782716917/coding-skill-and-the-decline-of-stagnation)
* Work through examples of using `String` methods in the REPL
  * Call methods
    * `charAt`
    * `contains`
    * `equals`
    * `indexOf`
    * `split`
    * `startsWith`
    * `substring`
    * `toUpperCase`
  * Look up the Java doc for `String`
* Modify the `Person` class
  * Add constraint in `setAge` so it only sets the age if it is a positive number
  * Add constraint in `setName` so it only sets the name if there is a space in it
  * Refector the conditional check into `isValidName`

### Day 3

* Review assignment (methods)
* Static methods and fields
  * They're stored in the class rather than in each object
  * Use static methods if it is "standalone" (no need to access fields)
  * Make `isValidName` static
  * Static methods and fields (look at the ones in `Math` and `System`)
* Main method
  * Running Java code the normal way
* Create [TextAdventure](../projects/TextAdventure)
  * Use `Scanner` to read console input
    * Unlike output, input requires creating an object
    * Book analogy: Reading requires a bookmark, but writing is simply appending at the end
  * Control flow
    * Conditionals
    * Exceptions

### Day 4

* Review assignment (control flow - ATM)
* Converting from a `String` using `Integer.valueOf`, `Double.valueOf`, etc
* Boxed vs unboxed values
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
  * Using generics for compile-time safety
* Text adventure
  * Use `ArrayList` to store inventory
  * Add command system
