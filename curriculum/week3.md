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
* Install [Scene Builder](http://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-1x-archive-2199384.html)
* Create a project
  * New Project -> JavaFX template
  * Run blank project
  * Look at the three files you start with
  * Edit Main.java to set the title and window size
  * Edit sample.fxml via Scene Builder
    * Make all buttons set min size to USE_PREF_SIZE
  * Edit Controller.java
    * Implement `Initializable`
    * Implement an action method
    * Bring in controls using `@FXML`
  * To-do app
    * Create an `ObservableList`
    * Set the `ListView` to use it
    * Wire up the "Add", "Toggle", and "Remove" buttons
  * Web browser
    * Wire up the "Go" button
    * Allow hitting enter in URL textfield
    * Implement `ChangeListener` to update the URL textfield
    * Wire up the back and forward buttons
* Build as a JAR file
  * File -> Project Structure...
  * Click on "JavaFXApp.jar"
  * Click on "Create Manifest..."
  * Click OK
  * Build -> Build Artifacts...
  * Click on "Build"

### Day 3

* Review assignment (javafx - contacts)
* Install [Android Studio](https://developer.android.com/sdk/index.html)
* Build a to-do app and a web browser
* Download the latest SDK
  * At the main screen, go to: Configure -> SDK Manager
  * Download the SDK for Android 4.0.3
* Install Intel HAXM (allows Android emulators to run faster)
  * Go to `~/Library/Android/sdk/extras/intel/Hardware_Accelerated_Execution_Manager`
  * Double-click "IntelHAXM.dmg" and run the installer
* Create a project
  * At the main screen, go to: Start a new Android Studio project
  * Give it a name and type "theironyard.com" as the company domain, and click "Next"
  * Select Android 4.0.3 and click "Next"
  * Select "Empty Activity" and click "Next"
  * Click "Finish"
* Configure an emulator
  * In your project, click the "Android Virtual Device Manager" button
  * Click on "Create Virtual Device..."
  * Select a device and click "Next..."
  * Select an Android version and click "Next..."
  * Make sure "Use Host GPU" is checked
  * Click "Show Advanced Settings"
  * Set the RAM to 1GB (so Intel HAXM will work)

### Day 4

* Games with libGDX
  * Download and run [the setup app](https://libgdx.badlogicgames.com/download.html)
  * Build Super Koalio
