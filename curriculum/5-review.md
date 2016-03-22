## Review

Last week, we covered four main topic areas:

1. Arrays and classes
2. Methods and constructures
3. Control flow (if, while, for, throw)
4. ArrayLists and HashMaps

## ToDo

We are going to make a console app to track to-do items. Create an IntelliJ project called `ToDo`. We should always start by thinking about the data we want to store. In this case, it's a class called `ToDoItem`:

```java
public class ToDoItem {
    public String text;
    public boolean isDone;

    public ToDoItem(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }
}
```

Now that we have that, we can write a program that constantly loops and provides an option to add, toggle, and list the to-do items. For now, we'll write it all in the main method. This may seem ugly, but don't get too hung up on code organization right now. It is difficult to break code into separate methods before you've written it.

```java
public class ToDo {
    public static void main(String[] args) {
        ArrayList<ToDoItem> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create to-do item");
            System.out.println("2. Toggle to-do item");
            System.out.println("3. List to-do items");

            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.println("Enter your to-do item:");
                String text = scanner.nextLine();

                ToDoItem item = new ToDoItem(text, false);
                items.add(item);
            }
            else if (option.equals("2")) {
                System.out.println("Enter the number of the item you want to toggle:");
                int itemNum = Integer.valueOf(scanner.nextLine());
                ToDoItem item = items.get(itemNum-1);
                item.isDone = !item.isDone;
            }
            else if (option.equals("3")) {
                int i = 1;
                for (ToDoItem item : items) {
                    String checkbox = "[ ] ";
                    if (item.isDone) {
                        checkbox = "[x] ";
                    }
                    System.out.println(checkbox + i + ". " + item.text);
                    i++;
                }
            }
            else {
                System.out.println("Invalid option");
            }
        }
    }
}
```

## JAR Files

One of the best things about the Java ecosystem is the simplicity of JAR files. In a single file, we can package all our code and assets to make it very easy to distribute. We can build a JAR file of our ToDo project like this:

1. File -> Project Structure...
2. Click "Artifacts" and then the plus button
3. JAR -> From modules with dependencies...
4. Choose the main class and click OK
5. Build -> Build Artifacts...
