## Week 4 - Web apps

### Day 1

* Review assignment (libgdx - minicraft)
* Exercise (parse a sentence and calculate word frequency)
* Review language features
  * Anonymous classes
    * We made one in the BrowserAndroid project
    * They let you extend a class and instantiate it all at once
    * Open the Zoo project and add alligator to the switch statement as an anonymous class
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
  * Project Structure -> Libraries -> Add the Mustache library
    * `com.sparkjava:spark-template-mustache:2.3`
  * Create `resources/templates/login.html` and a GET route
  * Create `resources/templates/home.html` and a GET route
  * Add create account form to `login.html`
  * Create a POST route that saves the username into an object
  * Display the name on the account page

### Day 2

* Review assignment (spark - microblog)
* Exercise (parse string, remove duplicate words, build new string)
* Review mustache
  * `String name;`
    * Welcome, {{name}}!
  * `ArrayList<String> names;`
    * {{#names}} {{.}} {{/names}}
  * `Message msg;`
    * {{#message}} {{text}} by {{username}} {{/message}}
  * `ArrayList<Message> messages;`
    * {{#messages}} {{text}} by {{username}} {{/messages}}
  * Conditional (truthy)
    * {{#name}}Welcome, {{name}}!{{/name}}
  * Conditional (falsey)
    * {{^name}}Please login.{{/name}}
* Add multi-user support to HelloSpark
  * Store users in `HashMap<String, User>`
* Cookies
  * The `Session` works by storing a cookie with a session ID
  * Cookies are small pieces of data sent from a web server
  * Browsers send them back each time they talk to the server afterwards
* Create [GameTracker](../projects/GameTracker)
  * Add the Spark and Mustache libraries
  * Create `resources/templates`
  * Create `resources/templates/home.html`
  * Create `/` route
  * Create `resources/templates/login.html`
  * Create `/login` route
  * Create `Game` and the `/create-game` route
  * Add games to the `home.html` template

### Day 3

* Review assignment (spark - microblog continued)
* Exercise (with arraylist of names, take the first 5, map uppercase, filter those starting with "A")
* Streams
  * map
  * filter
* Create [ForumWeb](../projects/ForumWeb)
  * Set up the libraries and `resources` directory
  * Create `Message` (id, replyId, username and text) with a constructor
  * Create `User` (name and password)
  * Create `HashMap<String, User>` and `ArrayList<Message>`
  * Write `addTestUsers` and `addTestMessages` method and make the aforementioned variables static
  * Create `home.html` which lists messages
  * Create `/` route
    * Get username from session
    * Create `HashMap` and add username/threads to it
  * Turn the thread text into links that show replies to the given message
    * GET routes can receive parameters too, but they are passed via the URL rather than via an HTML form
    * In the `/` route, get the replyId from `request.queryParams`
  * Use the mustache if-else to determine whether to show the login form
    * Create `/login` and `/logout` route

### Day 4

* Review assignment (spark - paging)
* Exercise (reverse array, turn array into hashmap)
* Layers of the Internet
  * Physical layer (hardware)
    * Wireless
    * Electrical (ethernet, coaxial)
    * Fiber optics
  * Internet layer (packets)
    * IPv4
    * IPv6
  * Transport layer (reliability)
    * UDP (checksum)
    * TCP (checksum and retransmission)
  * Application layer (end users)
    * ASCII
    * UTF-8
    * HTTP
    * HTTP/2
* ForumWeb
  * Create create-message form in `home.html`
    * Use `<input type="hidden" name="replyId" value="{{replyId}}" />` to pass the reply id to the server
  * Create `/create-message` route
    * Get username from session
    * If username is null, run `Spark.halt(403)`
    * Get replyId and text from `request.queryParams`
    * Create `Message` and add it to the `ArrayList<Message>`
    * Redirect to `/`
  * You can make it refresh the current page rather than take you home
    * `response.redirect(request.headers("Referer"))`
* Build as a JAR file
  * File -> Project Structure...
  * Click "Artifacts" and then the plus button
  * JAR -> From modules with dependencies...
  * Choose the main class and click OK
  * Build -> Build Artifacts...
* Upload JAR file to the "Releases" section on Github
* CRUD (create, read, update, delete)
