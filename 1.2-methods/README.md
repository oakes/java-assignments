### Methods

Write a few classes that allow the following to work in the REPL. The purpose of this assignment is to help illustrate the difference between methods and static methods. Note that the comments represent what that line should output.

```java
Position pos = new Position();
pos.x = 1.5;
pos.y = 0.5;

Player p = new Player(pos);
p.movePosition(-0.5, 0.5);
p.printPosition(); // 1.0 1.0

Player.movePosition(pos, 0.5, -0.5);
Player.printPosition(pos); // 1.5 0.5
```
