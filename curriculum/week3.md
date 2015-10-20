## Week 3 - Graphical programs

### Day 1

* Review assignment (file I/O continued - countries)
  * Loops
  * Nested data structures
* Exercise
  * Print name combos
  * Create `HashMap` that maps names to lists of `Email` objects
* More review
  * Casting
    * Cast a `long` to an `int`
    * Cast an `Object` (from a raw `ArrayList`) to a `String`
  * Inheritance
    * The `Character` class in the text adventure
* Interfaces
  * Sort an `ArrayList<String>` in JREPL using `Collections.sort`
  * In the `Contacts` class, sort `ArrayList<Contact>` by implementing `Comparable`
  * Definition
    * A list of one or more methods that a class must have
    * Allows code to call methods on objects even if they don't know what those objects are
    * Used all the time in Java itself as well as libraries/frameworks

### Day 2

* Review assignment (review - people)
* Desktop UI with Java FX
  * Install [Scene Builder](http://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-1x-archive-2199384.html)
  * Build a to-do app and a web browser
    * New Project -> Java FX template
    * Run blank project
    * Look at the three files you start with
    * Edit title and window size in Main.java
    * Use Scene Builder to change sample.fxml
    * Edit Controller.java
      * Implement `Initializable`
      * Implement an action method
      * Bring in controls using `@FXML`
      * To-do app
        * Create an `ObservableList`
        * Set the `ListView` to use it
        * Wire up the "Add" and "Remove" buttons
      * Web browser
        * Wire up the "Go" button
        * Make all buttons set min size to USE_PREF_SIZE
        * Allow hitting enter in URL textfield
        * Create a `ChangeListener` to update the URL textfield
        * Wire up the back and forward buttons
  * Build as a JAR file
    * File -> Project Structure...
    * Click on "JavaFXApp.jar"
    * Click on "Create Manifest..."
    * Click OK
    * Build -> Build Artifacts...
    * Click on "Build"

### Day 3

* Android
  * Install [Android Studio](https://developer.android.com/sdk/index.html)
  * Build a to-do app and a web browser

### Day 4

* Games with libGDX
  * Download and run [the setup app](https://libgdx.badlogicgames.com/download.html)
  * Build Super Koalio
