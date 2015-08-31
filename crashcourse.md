### History of Java

* Sun started working on it in 1991.
* James Gosling was the technical lead.
* Released to the public in 1995.
* Initially meant for embedded devices, but focus shifted to the internet.
* Why was it made? To solve a false dichotomy: You could write fast code slowly, or write slow code quickly.
* How does it work?
  * Languages like C and C++ will compile your code into machine code.
  * Scripting languages read the code, compile it into machine code in memory and execute it.
  * Java compiles to bytecode, which is compiled into machine code in memory and executed.

### Writing Java in a REPL

* Go to [Java REPL](http://www.javarepl.com/console.html).
* In English, we have nouns and verbs. In Java, we have variables and methods.
* We'll start by making the basic components of a game.
* Create scalar variables.
  * `int score = 0;`
  * `score = score + 1;`
  * `score += 1;`
  * `double health = 100;`
  * `health = health * 0.75;`
  * `health *= 0.75;`
* Create an array to represent position.
  * `double[] position = {0, 0};`
  * `position[0]`
  * `position[0] = 1;`
  * `position[1] = 1;`
* Create a class to bundle all these values.
  * `class Entity { int score = 0; double health = 100; double[] position = {0, 0}; }`
  * `Entity p1 = new Entity();`
* Contrast arrays with classes (indexing by number or by name).
  * `class Position { double x = 0; double y = 0; }`
  * `Position p = new Position();`
  * `p.x = 1;`
  * `p.y = 1;`
* There are many built-in Java classes.
  * `String s = “Hello, world!”;`
* You can make arrays of classes too.
  * `String[] names = {“Alice”, “Bob”, “Charlie”};`
* Classes don't just hold variables; they also hold methods.
  * `s.toUpperCase()`
  * `s.startsWith("Hello")`
* Create our own method.
  * `class Score { int value = 0; public void inc() { value += 1; } }`
  * `Score s = new Score();`
  * `s.inc();`
  * `s.value`
* There are also static methods.
  * `Math.min`, `Math.max`, and `Math.random`
  * `System.out.println(“HELLO!”);`

### Writing Java in a file

* Go to [IDEOne](http://ideone.com/).
* Make a simple text-based game.

```java
import java.util.*;
import java.lang.*;
import java.io.*;

class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Entity player = new Entity();
		Scanner r = new Scanner(System.in);
		
		System.out.println("What is your name?");
		String name = r.nextLine();
		System.out.println("Greetings, " + name);
		
		String[] weapons = {"sword", "mallet"};
		System.out.println("What shall your weapon be? [sword/mallet]");
		String weapon = r.nextLine();
		if (Arrays.binarySearch(weapons, weapon) < 0) {
			System.out.println("Invalid weapon!");
			return;
		}
		System.out.println(weapon + " is a fine choice!");
		
		Entity ogre = new Entity();
		ogre.damage = Math.random() * 2 + 9;
		while (ogre.health > 0 && player.health > 0) {
			ogre.health -= player.damage;
			player.health -= ogre.damage;
		}
		
		if (ogre.health <= 0) {
			System.out.println("The ogre has been killed!");
		}
		if (player.health <= 0) {
			System.out.println("You have been killed!");
		}
	}
}

class Entity
{
	double health = 100;
	double damage = 10;
}
```
