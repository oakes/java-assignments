## Week 6 - Spring

### Day 1

* Create HelloSpring
  * Go to [Spring Initializr](https://start.spring.io/)
  * Choose "Gradle Project"
  * Group is `com.theironyard` and artifcat is `HelloSpring`
  * Click "Switch to the full version"
  * Check the following options:
    * Web
    * Mustache
    * PostgreSQL
  * Download and unzip the project
  * Import into IntelliJ
  * Choose "Import project from external model" and select Gradle
  * Click Next and Finish
  * Create `src/main/resources/templates/home.html`
  * Create `src/main/java/com/theironyard/HelloSpringController.java`
* Build JAR file
  * Click the Gradle tab on the right edge of the window
  * HelloSpring -> Tasks -> build -> build
  * It will be in `build/libs`
