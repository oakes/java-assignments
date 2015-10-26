## Week 4 - Web apps

### Day 1

* Review assignment (libgdx - minicraft)
* Exercise (parse a sentence and calculate word frequency)
* Review terminology
  * Libraries vs frameworks
  * Functions vs methods
* Review language features
  * Inheritance
    * Extending a class
    * Implementing an interface
  * Anonymous classes
    * We made one in the BrowserAndroid project
    * They let you extend a class and instantiate it all at once
    * Open the Zoo project and create add alligator to the switch statement as an anonymous class
    * Create an AnonymousClassExample file
      * Extend `Reptile` in a separate class
      * Extend `Reptile` in an anonymous class
  * Anonymous functions (aka lambdas)
    * Create an AnonymousFunctionExample file
      * Run code in a separate method
      * Run code in a `Runnable`
      * Run code in an anonymous function
* HTTP
  * GET vs POST requests
* Create a web app
  * Create a new project and use the command line template
  * Project Structure -> Libraries -> Add [Spark](http://sparkjava.com/)
    * `com.sparkjava:spark-core:2.3`
  * Project Structure -> Modules -> New Folder...
    * Call it "resources" and mark it as such
  * Create `resources/public/index.html` and write the code to start it
  * Create a GET route
  * Difference between serving static files and creating routes
  * Project Structure -> Libraries -> Add the Mustache library
    * `com.sparkjava:spark-template-mustache:2.3`
  * Create `resources/templates/account.html`
  * Return the template in the GET route
  * Add create account form to `index.html`
  * Create a POST route that saves the username/password into an object
  * Display the name on the account page
* Build as a JAR file
  * File -> Project Structure...
  * Click "Artifacts" and then the plus button
  * JAR -> From modules with dependencies...
  * Choose the main class and click OK
  * Build -> Build Artifacts...

### Day 2

* Spark (continued)

### Day 3

* Spark (continued)

### Day 4

* Spark (continued)
