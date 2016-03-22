## File I/O

Open `HelloWorld` and create a `ReadWriteFile` class:

```java
public class ReadWriteFile {
    public static void main(String[] args) throws IOException {
        File f = new File("names.txt");
        FileWriter fw = new FileWriter(f);
        fw.write("Alice\n"); // overwrites file
        fw.append("Bob\n");
        fw.append("Charlie\n");
        fw.close();

        // read line-by-line
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }

        // read entire file
        scanner = new Scanner(f);
        scanner.useDelimiter("\\Z");
        String contents = scanner.next();
        System.out.println(contents);
    }
}
```

This is how to read and write a simple list of data. But what about more complicated data? When we want to store structured data, we need a more complicated format to serialize to and parse from. One common format is called JSON. Let's add a JSON library to this project:

1. File -> Project Structure...
2. Click "Libraries" and then the plus button
3. Click "From Maven..."
4. Type `org.jodd:jodd-json:3.6.7` and press OK

Now, we can create `ReadWriteJson` to learn how to use it:

```java
public class ReadWriteJson {
    public static void main(String[] args) throws IOException {
        Person p = new Person();
        p.name = "Alice Smith";
        p.age = 30;
        
        File f = new File("person.json");

        // write json
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(p);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();

        // read json
        Scanner s = new Scanner(f);
        s.useDelimiter("\\Z");
        String contents = s.next();
        JsonParser parser = new JsonParser();
        Person p2 = parser.parse(contents, Person.class);

        System.out.println(p2);
    }
}
```

## Text Adventure

Now we can use the aforementinoed JSON library to add a save/load feature to our game. First, we will write our `saveGame` and `loadGame` methods:

```java
public class Player {
    ...
    
    public static void saveGame() throws IOException {
        JsonSerializer s = new JsonSerializer();
        String json = s.include("*").serialize(player);

        File f = new File("game.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    public static Player loadGame() throws FileNotFoundException {
        File f = new File("game.json");
        Scanner s = new Scanner(f);
        s.useDelimiter("\\Z");
        String contents = s.next();

        JsonParser p = new JsonParser();
        return p.parse(contents, Player.class);
    }
}
```

Make sure to add getters to the `Player` class! The JSON library requires them in order to serialize the fields.

Next, we add a `/save` command in `nextLine`:

```java
public class Player {
    ...
    
    public static String nextLine() {
        String line = scanner.nextLine();
        while (line.startsWith("/")) {
            switch (line) {
                ...
                case "/save":
                    try {
                        saveGame();
                        System.out.println("Saved game.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                ...
            }
            line = scanner.nextLine();
        }
        return line;
    }
    
    ...
}
```

Then we call `loadGame` in the main method:

```java
public class Player {
    ...
    
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome, traveller.");

        try {
            player = loadGame();
            System.out.println("Loaded saved game.");
        } catch (Exception e) {
            System.out.println("Starting new game.");
        }
        
        ...
    }
    
    ...
}
```

Notice, though, that it still forces you to go through all the initial questions. We need to add conditionals to each one, which asks whether we already have that information. That way, it can skip over anything that we've already done:

```java
public class Player {
    ...
    
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome, traveller.");

        ...
        
        if (player.name == null) player.chooseName();
        if (player.weapon == null) player.chooseWeapon();
        if (player.location == null) player.chooseLocation();

        if (player.items.isEmpty()) {
            player.findItem("shield");
            player.findItem("boots");
            player.findItem("belt");
        }
        
        ...
    }
    
    ...
}
```
