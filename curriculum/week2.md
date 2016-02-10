## Week 2 - Console programs

### Day 1

* Review assignment (dynamic data structures - ATM)
* Review
  * Arrays and classes
  * Methods
  * Control flow (if, while, for, throw)
  * ArrayLists and HashMaps
* Create a [ToDo project](../projects/ToDo)
* Jar files
  * File -> Project Structure...
  * Click "Artifacts" and then the plus button
  * JAR -> From modules with dependencies...
  * Choose the main class and click OK
  * Build -> Build Artifacts...

### Day 2

* Review assignment (review - inventory)
* HelloWorld project
  * Create `PersonFinder.java`
  * Start with some `Person` objects
  * Add them to an `ArrayList<Person>`
  * Search an `ArrayList<Person>` for someone with a particular name
* Use `System.out.printf` in the ToDo project
* Create a [Zoo project](../projects/Zoo)
  * Animal
    * Mammal
      * Dog
      * Human
    * Reptile
      * Alligator
      * Turtle
    * Bird
      * Hawk
      * Parrot
  * Add fields and init them in constructors to demonstrate inheriting fields
  * Override `toString` to demonstrate inheriting methods
* Text adventure
  * Use `System.out.printf` when possible
  * Add health and damage to `Player`
  * Create a `Player` constructor
  * Create an `Enemy` class
  * Create a `Character` class that they extend
  * Create a `battle` method in `Character`

### Day 3

* Review assignment (object-oriented programming - inventory)
* File I/O
  * Writing a `File` with `FileWriter`
  * Reading a `File` with `Scanner`
    * Read line by line with a `while (scanner.hasNext())` loop and `scanner.nextLine()`
    * Read entire file with `scanner.useDelimiter("\\Z")` and `scanner.next()`
  * Reading and writing JSON
* Text adventure
  * Add save feature
* Dependency management
  * Maven
  * Pull in the [JSON library](http://jodd.org/doc/json/)
  * Add to project
    * File -> Project Structure...
    * Click "Libraries" and then the plus button
    * Click "From Maven..."
    * Search for "jodd json"

### Day 4

* Review assignment (file I/O - save JSON)
* Exercise
  * Keep asking for input until valid input is received
  * As a group, create a `HashMap<String, ArrayList<String>>`
* Text adventure
  * Save file name constant as `static final`
  * Set breakpoint in `main` to see contents of objects
  * Create bug in `findItem` and set breakpoint
  * Hotswap values
  * Write test for `battle` method
* Debugging with IntelliJ
  * Primitive form of debugging: printing variables
  * Better form of debugging: IntelliJ's debugger
    * Set breakpoint
    * See what variables contain at that moment in time
    * Reload changed classes
      * Can't reload method if it's in the middle of execution
      * Can't reload method if its signature has changed
      * [Notch using this feature](https://www.youtube.com/watch?v=BES9EKK4Aw4)
* Testing with IntelliJ
  * Create test folder
    * File -> Project Structure...
    * Click "Modules"
    * Right-click the "src" folder and click "New Folder"
    * Call it "test"
    * Right-click the "test" folder and click "Tests" to mark it as a test folder
  * Add JUnit 4 to project
    * File -> Project Structure...
    * Click "Libraries" and then the plus button
    * Click "From Maven..."
    * Type in "junit:junit:4.12"
  * Create a test
    * Select a class you want to write tests for
    * View -> Test 
* Create a [Forum project](../projects/Forum)
  * Create console-based forum with custom file format
  * Create `Post` and `ArrayList<Post>`
  * Create file and read its contents into the arraylist
  * Loop over posts and print the top ones out
  * Break the loop out into `printPosts`
  * Create an infinite loop that listens for a post # to jump to
