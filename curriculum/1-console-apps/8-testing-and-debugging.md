## Debugging

Open `TextAdventure`, set a breakpoint in the main method and run in debug mode. Observe that you can see inside the `player` object in IntelliJ's debugger interface.

Now try creating a bug somewhere, like removing `new Player();` from the top of the `Game` class so the player is never instantiated. This should lead to a `NullPointerException`. Place a breakpoint on the line with the error and observe that you can see `player` is null in the debugger.

The debugger can also hotswap code while the program is running. For example, set a breakpoint on the `Enemy ogre` line in the main method and run in debug mode. Then go to the `Character` class and change the text that is printed in some way. Then go to `Run -> Reload Changed Classes`. Then continue the game until it is over. Observe that the text was updated, even though you didn't restart the program! Check out [Notch using this feature](https://www.youtube.com/watch?v=BES9EKK4Aw4) for game development.

## Testing

It would be useful to write a test to make sure the `battle` method works. First, we need to create a `test` folder in our `TextAdventure` project:

1. File -> Project Structure...
2. Click "Modules"
3. Right-click the "src" folder and click "New Folder"
4. Call it "test"
5. Right-click the "test" folder and click "Tests" to mark it as a test folder

Then we must add JUnit 4 to our project:

1. File -> Project Structure...
2. Click "Libraries" and then the plus button
3. Click "From Maven..."
4. Type in "junit:junit:4.12" and press OK

Finally, we must create our test class:

1. Open a class you want to write tests for
2. Navigate -> Test 

Our test will look like this:

```java
public class CharacterTest {

    @Test
    public void testBattle() throws Exception {
        Player player = new Player();
        player.name = "Test Player";
        Enemy enemy = new Enemy("Test Enemy", 5, 5);
        player.battle(enemy);
        assertTrue(player.health > 0);
        assertTrue(enemy.health <= 0);
    }
}
```
