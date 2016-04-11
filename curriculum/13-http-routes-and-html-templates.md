## Anonymous Classes and Functions

Defining classes and methods is a fundamental activity, but sometimes the normal syntax for doing so is too verbose for what you're trying to achieve. There is a special way of defining both that is more terse and can be embedded inside a block of code. We call them "anonymous" because they don't even need a name like normal classes and methods do. Open the Zoo project so we can try them out.

Let's say we want to make an object representing an alligator. Using what we know so far, we would define a class called `Alligator` which extends `Reptile`. It would override the `makeSound` method to output something like "Croak". If we only intend to use this object once, however, defining a new class is a bit of overkill.

Make a new class called `AnonymousClassExample`. Here, we will make a small main method that defines an alligator object using an anonymous class. It essentially is defining a class and instantiating it at the same time:

```java
public class AnonymousClassExample {
    public static void main(String[] args) {
        Reptile alligator = new Reptile() {
            @Override
            public void makeSound() {
                System.out.println("Croak");
            }
        };
        alligator.name = "Alligator";

        alligator.makeSound();
    }
}
```

It turns out that there is an equivalent feature for methods, called anonymous functions (also known as lambdas). A lambda is sort of like defining a method inside some larger block of code rather than in its own separate code block. Like anonymous classes, it isn't given an explicit name.

Make a new class called `AnonymousFunctionExample`. First make a normal method called `sayHello` and run it. Then define a lambda with the type of `Runnable` and immediately run it. Notice that they both achieve the same thing, but the lambda has a few advantages: it allows simple methods to be defined inline rather than requiring you to look for it elsewhere, and it allows you to pass a method around as an object.

```java
public class AnonymousFunctionExample {
    public static void main(String[] args) {
        // Run code from a separate method
        sayHello();

        // Run code from a lambda
        Runnable r2 = () -> {
            System.out.println("Hello from a lambda!");
        };
        r2.run();
    }

    static void sayHello() {
        System.out.println("Hello from a method!");
    }
}
```
