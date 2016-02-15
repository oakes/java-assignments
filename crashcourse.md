### About Java

* Sun started working on it in 1991.
* James Gosling was the technical lead.
* Released to the public in 1995.
* Initially meant for embedded devices, but focus shifted to the internet.
* Why was it made? To solve a false dichotomy: You could write fast code slowly, or write slow code quickly.
* How does it work?
  * Languages like C and C++ will compile your code into machine code.
  * Scripting languages read the code, compile it into machine code in memory and execute it.
  * Java compiles to bytecode, which is compiled into machine code in memory and executed.
* Discuss what we're covering in our course and show off projects we've done so far.

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
* Create a class to bundle all these values.
  * `class Player { int score = 0; double health = 100; }`
  * `Player p = new Player();`
* There are many built-in Java classes.
  * `String s = “Hello, world!”;`
* Classes don't just hold variables; they also hold methods.
  * `s.toUpperCase()`
  * `s.startsWith("Hello")`
* One common method lets you print text to the console.
  * `System.out.println(“HELLO!”);`

### Writing Java in a file

* Go to [IDEOne](http://ideone.com/) or [Compile Java](http://www.compilejava.net/).
* Make a mock text-based game.

```java
import java.util.*;
import java.lang.*;
import java.io.*;

class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Entity player = new Entity();
		
		System.out.println("What is your name?");
		String name = "Zach";
		System.out.println("Greetings, " + name);
		
		System.out.println("What shall your weapon be?");
		String weapon = "Sword";
		System.out.println(weapon + " is a fine choice!");
		
		Entity ogre = new Entity();
		
		while (ogre.health > 0) {
			ogre.health -= 10;
		}
		
		System.out.println("The ogre has been killed!");
	}
}

class Entity
{
	double health = 100;
	double damage = 10;
}
```
