## Forum

Let's begin by refactoring the Forum project so we use separate methods. We'll begin with the file reading code:

```java
public class Forum {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Post> posts = readFile();

        ...
    }

    public static ArrayList<Post> readFile() throws FileNotFoundException {
        ArrayList<Post> posts = new ArrayList<>();
        File f = new File("posts.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Post post = new Post(Integer.valueOf(columns[0]), columns[1], columns[2]);
            posts.add(post);
        }
        return posts;
    }
}
```

We can do the same for the code that prints the posts:

```java
public class Forum {
    public static void main(String[] args) throws FileNotFoundException {
        ...
        
        Scanner consoleScanner = new Scanner(System.in);

        int replyId = -1;
        while (true) {
            // loop over posts and print the ones with the right reply id
            printPosts(posts, replyId);
            // ask the user to type a new reply id
            System.out.println("Type the id you want to see replies to:");
            replyId = Integer.valueOf(consoleScanner.nextLine());
        }
    }
    
    ...

    public static void printPosts(ArrayList<Post> posts, int replyId) {
        int id = 0;
        for (Post post : posts) {
            if (post.replyId == replyId) {
                System.out.printf("(%d) %s by %s\n", id, post.text, post.author);
            }
            id++;
        }
    }
}
```

## Interfaces

In JREPL, create an `ArrayList` of names:

* `ArrayList<String> names = new ArrayList<>();`
* `names.add("Alice");`
* `names.add("Charlie");`
* `names.add("Bob");`

Now, sort it alphabetically:

* `Collections.sort(names);`

Now create an `ArrayList` of `Person` objects (create the necessary `Person` constructor first):

* `ArrayList<Person> people = new ArrayList<>();`
* `people.add(new Person("Alice"));`
* `people.add(new Person("Charlie"));`
* `people.add(new Person("Bob"));`

Now try to sort it:

* `Collections.sort(people);`
* `ERROR: no suitable method found for sort(java.util.ArrayList<Person>)`

The issue is that Java doesn't know how to sort a `Person` object, since it's a class we designed ourselves. We need a way to tell it how it should sort them. We can do that by implementing an interface called `Comparable` in the `Person` class and writing the necessary `compareTo` method:

```java
public class Person {
    ...
    
    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return name.compareTo(p.name);
    }
}
```

After doing `Build -> Rebuild Project` and restarting JREPL, you can recreate that `ArrayList<Person>` and the sort method should now work.
