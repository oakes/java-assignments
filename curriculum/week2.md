## Week 2 - Console programs

### Day 1

* Running Java code the normal way
  * Main method
  * Jar files
    * File -> Project Structure...
    * Click "Artifacts" and then the plus button
    * JAR -> From modules with dependencies...
    * Choose the main class and click OK
    * Build -> Build Artifacts...
* Review last week's material
  * Arrays and classes
  * Methods
  * Control flow (if, while, for, throw)
  * ArrayLists and HashMaps

### Day 2

* Contact project
  * Create a constructor for `Contact`
  * Create `Contact` objects
  * Change their fields
  * Add them to an `ArrayList<Contact>`
  * Get a contact out of the list
  * Search an `ArrayList<Contact>` for someone with a particular name
* Use `String.format` in the ToDo project
* Text adventure
  * Add health and damage to `Player`
  * Create a `Player` constructor
  * Create an `Enemy` class
  * Create a `Character` class that they extend
  * Create a `battle` method in `Character`
  * Create a `toString` method in `Character`
  * Discuss `@Override`
* Contrived example of inheritance
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
* Topics of discussion
  * Review constructors
  * Extending a class (inheritance)
  * The `Object` class

### Day 3

* Exercise
  * Loop over string array
  * Use `ArrayList` instead of a primitive array
  * Use `Contact` class instead of strings
* Text adventure
  * Store weapon in `Character` and create `Weapon` class
  * Add weapon's damage in `battle` method
  * Add save feature
* File I/O
  * JSON
  * `File`, `FileWriter`, `FileReader`
* Dependency management
  * Maven
  * Pull in the [JSON library](http://jodd.org/doc/json/)
  * Add to project
    * File -> Project Structure...
    * Click "Libraries" and then the plus button
    * Click "From Maven..."
    * Search for "jodd json"

### Day 4

* Debugging with IntelliJ
  * Simplest form of debugging: printing variables
  * Better form of debugging: IntelliJ's debugger
    * Set breakpoint
    * See what variables contain at that moment in time
    * Reload changed classes
      * Can't reload method if it's in the middle of execution
      * Can't reload method if its signature has changed
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
    * Search for "junit"
  * Create a test
    * Select a class you want to write tests for
    * View -> Test
