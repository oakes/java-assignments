## String Formatting

While it is okay to combine strings together using `+`, it can get ugly. For example, look at this gnarly line from yesterday's `ToDo` project:

```java
System.out.println(checkbox + i + ". " + item.text);
```

Even if you know what those variables contain, it can be hard to picture what will be printed. A simpler way to do this is string formatting. Fire up JREPL and try the following:

* `String name = "Alice";`
* `int age = 30;`
* `String.format("Hello, %s! You are %s years old.", name, age);`
* `System.out.printf("Hello, %s! You are %s years old.", name, age);`

Now let's rewrite that line in the `ToDo` project:

```java
System.out.printf("%s %s. %s\n", checkbox, i, item.text);
```

## Zoo

Create a project called `Zoo`. We'll use this to demonstracte a fundamental idea in Java called inheritance by extending animal classes. We'll start with a simple `Animal` class:

```java
public class Animal {
    String name;
}
```

Now make broad animal categories that inherit from it:

```java
public class Mammal extends Animal {
    public Mammal() {
        this.name = "Mammal";
    }
}
```

```java
public class Reptile extends Animal {
    public Reptile() {
        this.name = "Reptile";
    }
}
```

```java
public class Bird extends Animal {
    public Bird() {
        this.name = "Bird";
    }
}
```

Finally, make specific classes that inherit from those categories:

```java
public class Dog extends Mammal {
    public Dog() {
        this.name = "Dog";
    }
}
```

```java
public class Snake extends Reptile {
    public Snake() {
        this.name = "Snake";
    }
}
```

```java
public class Hawk extends Bird {
    public Hawk() {
        this.name = "Hawk";
    }
}
```

Next, let's add a method to the top-most `Animal` class:

```java
public class Animal {
    public String name;
    
    public void makeSound() {
        System.out.println("Animal sound!");
    }
}
```

There isn't really any specific sound we can make it "say", so this is an opportunity to "override" the method in the `Dog`, `Snake`, and `Hawk` class to make it say a specific sound. We'll also use the `@Override` annotation, which, while not strictly necessary, will let us catch typos:

```java
public class Dog extends Mammal {
    public Dog() {
        this.name = "Dog";
    }
    
    @Override
    public void makeSound() {
        System.out.println("Bark!");
    }
}
```

```java
public class Snake extends Reptile {
    public Snake() {
        this.name = "Snake";
    }
    
    @Override
    public void makeSound() {
        System.out.println("Sssssssss!");
    }
}
```

```java
public class Hawk extends Bird {
    public Hawk() {
        this.name = "Hawk";
    }
    
    @Override
    public void makeSound() {
        System.out.println("Cawwww!");
    }
}
```

We can also override built-in methods. For example, in the `Animal` class, we can override `toString` so it will print out the name of the animal when passed to `System.out.println`. We only need to do it there, because all the classes that inherit it will receive the funtionality automatically:

```java
public class Animal {
    public String name;
    
    public void makeSound() {
        System.out.println("Animal sound!");
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
```

## Text Adventure

Start by using string formatting everywhere it makes sense to in the project. Now let's work on a battle system. We'll start by adding health and damage, as well as a constructor, to our `Player`:

```java
public class Player {
    String name;
    String weapon;
    String location;
    int health;
    int damage;
    
    public Player() {
        this.health = 20;
        this.damage = 20;
    }
    
    ...
}
```

Now let's create a class to represent our enemy:

```java
public class Enemy {
    String name;
    int health;
    int damage;
}
```

Notice how the `Player` and `Enemy` classes have some of the same fields. We can reduce this duplication by creating a class that they both extend:

```java
public class Character {
    String name;
    int health;
    int damage;
}
```

```java
public class Enemy extends Character {
}
```

```java
public class Player extends Character {
    String weapon;
    String location;
    
    ...
}
```

Since both the player and enemy extend the `Character` class, we can create a `battle` method there so both can use it:

```java
public class Character {
    ...
    
    public void battle(Character enemy) {
        System.out.printf("%s appears!\n", enemy.name);

        while (health > 0 && enemy.health > 0) {
            health -= enemy.damage;
            enemy.health -= damage;
            System.out.printf("%s's health: %d\n", name, health);
            System.out.printf("%s's health: %d\n", enemy.name, enemy.health);
        }

        String message = "%s has died.\n";

        if (health <= 0) {
            System.out.printf(message, name);
        }

        if (enemy.health <= 0) {
            System.out.printf(message, enemy.name);
        }
    }
}
```

Now we can call it at the end of the main method:

```java
public class Game {
    ...
    
    public static void main(String[] args) throws Exception {
        ...
        
        Enemy ogre = new Enemy("Ogre", 10, 10);
        player.battle(ogre);
    }
    
    ...
}
```
