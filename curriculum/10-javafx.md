## ToDoDesktop

JavaFX is a UI library built into the Java runtime. It provides a strict separation between logic and presentation. The former is written in Java, while the latter is written in XML. While you could write the interface's XML manually, it is more common to use a visual tool called [Scene Builder](http://gluonhq.com/open-source/scene-builder/), so let's download it and get started.

In IntelliJ, create a new project called `ToDoDesktop` and use the JavaFX template. Run it right away and you should see a blank window appear. Now open `Main.java` and modify the code so the title is "ToDo Desktop" and the scene is set to 800 by 600. Now the window should default to a more reasonable size.

Launch Scene Builder and open the `sample.fxml` file located in the IntelliJ project. Start by deleting the GridPane from the hierarchy. Most UIs can be created through a combination of HBox and VBox, so that's what we will do. Begin by dragging VBox into the hierarchy. Inside of it, place an HBox and a ListView. Select the ListView, and under Layout (on the right) set its VGrow attribute to "ALWAYS".

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/javafx-1.png)

Now add a TextField and three Button controls in the HBox. Set the TextField's Hgrow attribute to "ALWAYS". Set the Text property of the buttons to "Add", "Remove", and "Toggle". Also, set the Min Width of each button to "USE_PREF_SIZE" to ensure the text doesn't get clipped.

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/javafx-2.png)

First, make sure the UI knows where the controller is by going to the bottom left corner and opening the Controller section. Set the controller class to `sample.Controller` (or whatever your controller's full class name is).

Then we should give our ListView and TextField IDs in Scene Builder. To do so, click on them and open the Code section, where you will write `list` and `text` for them respectively.

Now, let's bring them into the controller using the `@FXML` annotation, which works by locating controls with IDs of the same name:

```java
public class Controller {
    @FXML
    ListView list;

    @FXML
    TextField text;
}
```

We should now write the code to make our buttons run methods in this controller. To do so, open Scene Builder, click on them and open the Code section. In the On Action input, write `addItem`, `removeItem`, and `toggleItem` respectively. Now save it write the equivalent methods in the controller:

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
    
    @Override
    public String toString() {
        return text + (isDone ? " (done)" : "");
    }
}
```

Notice that we are overriding `toString` here. This is because our ListView is going to call it to display appropriate text. This particular method will show the text and will also show "(done)" if it is marked as done.

From here, we can create an `ObservableList<ToDoItem>` in our controller to hold them. It behaves similarly to an `ArrayList` except that it will automatically update the ListView when it is updated:

```java
public class Controller {
    ObservableList<ToDoItem> items = FXCollections.observableArrayList();
    
    ...
}
```

When the app first starts, we need to connect it to the ListView. To get a method that runs right when the app starts, we must make the controller implement `Initializable`:

```java
public class Controller implements Initializable {
    ...

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(items);
    }
}
```

Now we can fill in the methods:

```java
public class Controller implements Initializable {
    ...

    public void addItem() {
        items.add(new ToDoItem(text.getText()));
        text.setText("");
    }

    public void removeItem() {
        ToDoItem item = (ToDoItem) list.getSelectionModel().getSelectedItem();
        items.remove(item);
    }

    public void toggleItem() {
        ToDoItem item = (ToDoItem) list.getSelectionModel().getSelectedItem();
        if (item != null) {
            item.isDone = !item.isDone;
            list.setItems(null);
            list.setItems(items);
        }
    }
    
    ...
}
```

## BrowserDesktop

In IntelliJ, create a new project called `BrowserDesktop` and use the JavaFX template. Run it right away and you should see a blank window appear. Now open `Main.java` and modify the code so the title is "Browser Desktop" and the scene is set to 800 by 600. Launch Scene Builder and open the `sample.fxml` file located in the IntelliJ project. Start by deleting the GridPane from the hierarchy. Begin by dragging VBox into the hierarchy. Inside of it, place an HBox and a WebView. Select the WebView, and under Layout (on the right) set its VGrow attribute to "ALWAYS".

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/javafx-3.png)

Now add two Button controls (for back/forward), a TextField (the address bar), and one more Button (to initiate address loading) in the HBox. Then click the TextField and set its HGrow attribute to "ALWAYS", and click each button and change their Text property to `<`, `>`, and `Go` respectively. Also, set the Min Width of each button to "USE_PREF_SIZE" to ensure the text doesn't get clipped.

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/javafx-4.png)

First, make sure the UI knows where the controller is by going to the bottom left corner and opening the Controller section. Set the controller class to `sample.Controller` (or whatever your controller's full class name is).

Then we should give our TextField and WebView IDs in Scene Builder. To do so, click on them and open the Code section, where you will write `addressBar` and `webView` for them respectively.

Now, let's bring them into the controller using the `@FXML` annotation, which works by locating controls with IDs of the same name:

```java
public class Controller {
    @FXML
    TextField addressBar;

    @FXML
    WebView webView;
}
```

We should now write the code to make our buttons run methods in this controller. To do so, open Scene Builder, click on them and open the Code section. In the On Action input, write `goBack`, `goForward`, and `goToUrl` respectively. Now save it write the equivalent methods in the controller:

```java
public class Controller {
    ...
    
    public void goBack() {
        System.out.println("goBack");
    }

    public void goForward() {
        System.out.println("goForward");
    }

    public void goToUrl() {
        System.out.printf("goToUrl");
    }
}
```

Try running the project and make sure that you see the print statements in IntelliJ's console after clicking the buttons.

Now we can fill in the methods:

```java
public class Controller {
    ...
    
    public void goBack() {
        try {
            webView.getEngine().getHistory().go(-1);
        } catch (Exception e) {

        }
    }

    public void goForward() {
        try {
            webView.getEngine().getHistory().go(1);
        } catch (Exception e) {

        }
    }

    public void goToUrl() {
        String url = addressBar.getText();
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        webView.getEngine().load(url);
    }
}
```

We should now have basic web browser functionality. There are some enhancements we can add. First, let's go to Scene Builder and click on the TextField. In the Code section, set On Key Pressed to a method called "onKeyPressed". That will allow us to write a method in our controller like this:

```java
public class Controller {
    ...
    
    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            goToUrl();
        }
    }
}
```

We should also provide a way to update the address bar when we click on a link to a new page. To do this, we must first tell the browser's "load worker" to use this controller as its change listener by implementing `Initializable`:

```java
public class Controller implements Initializable {
    ...
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Worker worker = webView.getEngine().getLoadWorker();
        worker.stateProperty().addListener(this);
    }
}
```

Then we need to implement `ChangeListener`, which will provide the method in which we will update the address bar's text:

```java
public class Controller implements Initializable, ChangeListener {
    ...
    
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        addressBar.setText(webView.getEngine().getLocation());
    }
}
```

Now you can see that clicking links will update the address bar.
