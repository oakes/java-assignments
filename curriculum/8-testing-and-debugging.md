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

## Forum

Now we will create a console-based forum project called `Forum`. It will simply read posts from a flat file and display them in a way that lets you navigate threads. We'll start, as always, with our data:

```java
public class Post {
    int replyId;
    String author;
    String text;

    public Post(int replyId, String author, String text) {
        this.replyId = replyId;
        this.author = author;
        this.text = text;
    }
}
```

Then, we will create our own custom file format to store the posts. Create a file called `posts.txt` in the root of your project that contains this:

```
-1|alice|Hey guys!
-1|bob|This is a new discussion
0|charlie|Hi alice
2|alice|Hi charlie
1|charlie|Hey bob
```

Finally, write the code to parse the file and allow the use to drill down a given thread:

```java
public class Forum {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Post> posts = new ArrayList<>();

        // read all the posts into memory
        File f = new File("posts.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Post post = new Post(Integer.valueOf(columns[0]), columns[1], columns[2]);
            posts.add(post);
        }

        Scanner consoleScanner = new Scanner(System.in);

        int replyId = -1;
        while (true) {
            // loop over posts and print the ones with the right reply id
            int id = 0;
            for (Post post : posts) {
                if (post.replyId == replyId) {
                    System.out.printf("(%d) %s by %s\n", id, post.text, post.author);
                }
                id++;
            }
            // ask the user to type a new reply id
            System.out.println("Type the id you want to see replies to:");
            replyId = Integer.valueOf(consoleScanner.nextLine());
        }
    }
}
```
