## JavaFX

JavaFX is a UI library built into the Java runtime. It provides a strict separation between logic and presentation. The former is written in Java, while the latter is written in XML. While you could write the interface's XML manually, it is more common to use a visual tool called [Scene Builder](http://gluonhq.com/open-source/scene-builder/), so let's download it and get started.

In IntelliJ, create a new project called `ToDoDesktop` and use the JavaFX template. Run it right away and you should see a blank window appear. Now open `Main.java` and modify the code so the title is "ToDo Desktop" and the scene is set to 800 by 600. Now the window should default to a more reasonable size.

Launch Scene Builder and open the `sample.fxml` file located in the IntelliJ project. Start by deleting the GridPane from the hierarchy. Most UIs can be created through a combination of HBox and VBox, so that's what we will do. Begin by dragging VBox into the hierarchy. Inside of it, place an HBox and a ListView. Select the ListView, and under Layout (on the right) set its VGrow property to "ALWAYS".

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/javafx-1.png)
