## JavaFX

JavaFX is a UI library built into the Java runtime. It provides a strict separation between logic and presentation. The former is written in Java, while the latter is written in XML. While you could write the interface's XML manually, it is more common to use a visual tool called [Scene Builder](http://gluonhq.com/open-source/scene-builder/), so let's download it and get started.

In IntelliJ, create a new project called `ToDoDesktop` and use the JavaFX template. Run it right away and you should see a blank window appear. Now open `Main.java` and modify the code so the title is "ToDo Desktop" and the scene is set to 800 by 600. Now the window should default to a more reasonable size.

Launch Scene Builder and open the `sample.fxml` file located in the IntelliJ project. Start by deleting the GridPane from the hierarchy. Most UIs can be created through a combination of HBox and VBox, so that's what we will do. Begin by dragging VBox into the hierarchy. Inside of it, place an HBox and a ListView. Select the ListView, and under Layout (on the right) set its VGrow attribute to "ALWAYS".

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/javafx-1.png)

Now add a TextField and three Button controls in the HBox. Set the TextField's Hgrow attribute to "ALWAYS". Set the Text property of the buttons to "Add", "Remove", and "Toggle". Also, set the Min Width of each button to "USE_PREF_SIZE" to ensure the text doesn't get clipped.

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/javafx-2.png)

We'll begin our code by bringing the ListView and TextField into it. To do so, we must first give them both an ID in Scene Builder. To do so, click on them and open the Code section, where you will write `list` and `text` for them respectively. Lastly, in the bottom left corner you need to open the Controller section and set the controller class to `sample.Controller` (or whatever your controller's full class name is).

Now, let's bring them into the controller using the `@FXML` annotation:

```java
public class Controller {
    @FXML
    ListView list;

    @FXML
    TextField text;
}
```

We should first write the code to make our buttons run methods in this controller. To do so, open Scene Builder, click on them and open the Code section. In the On Action input, write `addItem`, `removeItem`, and `toggleItem`. Now save it write the equivalent methods in the controller:

```java
public class Controller {
    ...
    
    public void addItem() {
        System.out.println("addItem");
    }

    public void removeItem() {
        System.out.println("removeItem");
    }

    public void toggleItem() {
        System.out.printf("toggleItem");
    }
}
```

Try running the project and make sure that you see the print statements in IntelliJ's console after clicking the buttons.

Now let's create a class to store a to-do item:

```java
public class ToDoItem {
    String text;
    boolean isDone;

    public ToDoItem(String text) {
        this.text = text;
        this.isDone = false;
    }
}
```

From here, we can create an `ObservableList<ToDoItem>` in our controller to hold them. It behaves similarly to an `ArrayList` except that it will automatically update the ListView when it is updated:

```
public class Controller {
    ObservableList<ToDoItem> items = FXCollections.observableArrayList();
    
    ...
}
```

When the app first starts, we need to connect it to the ListView. To get a method that runs right when the app starts, we must make the controller implement `Initializable`:

```
public class Controller implements Initializable {
    ...

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(items);
    }
}
```
