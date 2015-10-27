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
* Create [HelloSpark](../projects/HelloSpark)
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

* Review assignment (spark - microblog)
* Exercise (parse string, remove duplicate words, build new string)
* Add multi-user support to HelloSpark
  * Store users in `HashMap<String, User>`
  * Change the `/create-account` route to login as well
  * Change the `/accounts` route to use `users.values()`
  * Rename `accounts.html` to `logged-in.html`
  * Rename `index.html` to `not-logged-in.html` and move it into `resources/templates`
  * Remove the `/accounts` route
  * Create a `/` route that sends down either template based on whether the user is logged in or not
* Cookies
  * The `Session` works by storing a cookie with a session ID
  * Cookies are small pieces of data sent from a web server
  * Browsers send them back each time they talk to the server afterwards
* Create [BeerTracker](../projects/BeerTracker)
  * Add the Spark and Mustache libraries
  * Create `resources/templates`
  * Create `resources/templates/logged-in.html`
  * Create `/` route
  * Create `resources/templates/not-logged-in.html`
  * Create `/login` route
  * Create `Beer` and the `/create-beer` route
  * Add beers to the `logged-in.html` template
  * Add an `id` to `Beer`
  * Create `/delete-beer` route and form

### Day 3

* Review assignment (spark - microblog continued)
* Exercise (change shape of collection)
* Streams
* Layers of the Internet
  * Physical layer
    * Wireless
    * Electrical (ethernet, coaxial)
    * Fiber optics
  * Internet layer (packet switching)
    * IPv4
    * IPv6
  * Transport layer (reliability)
    * UDP (checksum)
    * TCP (checksum and retransmission)
  * Application layer (end users)
    * HTTP
* Forum
  * Set up the libraries and `resources` directory
  * Create `Message` (id, replyId, username and text) with a constructor
  * Create `User` (password and messages)
  * Create `HashMap<String, User>` and `ArrayList<Message>`
  * Write `addTestData` method and make the aforementioned variables static
  * Create `/` route that returns `threads.html`
  * In `threads.html`
    * Use the mustache if-else to determine whether to show the login form
    * Turn the thread text into links
  * Create `/replies` route that returns `replies.html`
    * GET routes can receive parameters too, but they are passed via the URL rather than via an HTML form

### Day 4

* Review assignment
* Spark (continued)
