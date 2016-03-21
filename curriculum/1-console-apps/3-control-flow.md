## Static Methods and Fields

If a method or field is `static`, it means that it is stored "globally" in the class rather than in each object. You should only make a method static if it is "standalone". For example, if we want to use the `isValidName` method we wrote yesterday, we have to do two steps in JREPL:

* `Person p = new Person();`
* `p.isValidName("Alice Smith");`

If you think about it, this is a bit silly. Why should we have to create a new `Person` object every time we want to use that method? Let's change the code to make it `static`. Now go to `Build -> Rebuild Project` and restart JREPL. Now we can just do this:

* `Person.isValidName("Alice Smith");`

Much better! The standard library has many static methods and fields. The [System](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html) class has a static field called `out`, which represents your computer's standard out, a facility for printing text to the console. That's why printing text is done this way:

* `System.out.println("Hello, world!");`

You'll find a lot of static methods in the [Math](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html) class. That makes sense, because mathematical operations are "pure" functions that take inputs and return an output. They should almost always be standalone methods, rather than tied to a particular object.

The most important static method, though, is the main method. Let's write our first main method in a class called `HelloWorld`:

```java
public class HelloWorld {
    public static void main(String[] args) {
      System.out.println("Hello, world!");
    }
}
```

You can run that in IntelliJ by right-clicking the file and choosing "Run". You should have enough information by now to understand everything in that code block. We've gone over classes, static methods, arrays, and `System.out.println`.

## Text Adventure

Create a new IntelliJ project called `TextAdventure`. We'll add to it in the coming days. Games are a nice way to learn language features because they are very open-ended, and thus easy to shoehorn contrived features into purely to demonstrate language concepts. For now, let's write a main method that uses `Scanner` to read keyboard input:

```java
public class Game {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome, traveller.");
        System.out.println("What is your name?");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        System.out.println("Welcome, " + name);

        System.out.println("Choose your weapon [sword/mace]");
        String weapon = scanner.nextLine();

        if (weapon.equalsIgnoreCase("sword")) {
            System.out.println("A sword is a fine choice!");
        }
        else if (weapon.equalsIgnoreCase("mace")) {
            System.out.println("A mace is a fine choice!");
        }
        else {
            throw new Exception("Invalid weapon.");
        }

        System.out.println("Choose your location [forest/tunnel]");
        String location = scanner.nextLine();

        if (location.equalsIgnoreCase("forest")) {
            System.out.println("Entering forest...");
        }
        else if (location.equalsIgnoreCase("tunnel")) {
            System.out.println("Entering tunnel...");
        }
        else {
            throw new Exception("Invalid location.");
        }
    }
}
```

While this code works, it's pretty hard to read because it's all in one method. Let's refactor this into a separate `Player` class for better organization:

```java
public class Player {
    String name;
    String weapon;
    String location;

    public void chooseName() {
        System.out.println("What is your name?");
        name = Game.scanner.nextLine();
        System.out.println("Welcome, " + name);
    }

    public void chooseWeapon() throws Exception {
        System.out.println("Choose your weapon [sword/mace]");
        weapon = Game.scanner.nextLine();

        if (weapon.equalsIgnoreCase("sword")) {
            System.out.println("A sword is a fine choice!");
        }
        else if (weapon.equalsIgnoreCase("mace")) {
            System.out.println("A mace is a fine choice!");
        }
        else {
            throw new Exception("Invalid weapon.");
        }
    }

    public void chooseLocation() throws Exception {
        System.out.println("Choose your location [forest/tunnel]");
        location = Game.scanner.nextLine();

        if (location.equalsIgnoreCase("forest")) {
            System.out.println("Entering forest...");
        }
        else if (location.equalsIgnoreCase("tunnel")) {
            System.out.println("Entering tunnel...");
        }
        else {
            throw new Exception("Invalid location.");
        }
    }
}
```

Now the main class can be nice and tidy:

```java
public class Game {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome, traveller.");

        Player player = new Player();
        player.chooseName();
        player.chooseWeapon();
        player.chooseLocation();
    }
}
```
