## Major Security Topics

* SQL injection prevention
* Cross-site scripting prevention
* SSL encryption
* Secure password storage

## Secure Password Storage

Let's make a few changes to the project to make it better organized and more secure. We'll start with organization. So far, we've made all our files under the `com.theironyard` package. Most Spring projects will organize different kinds of classes into subpackages. In your IDE, refactor your classes so they fall under the following subpackages:

* `controllers`
  * `src/main/java/com/theironyard/controllers/GameTrackerController.java`
* `entities`
  * `src/main/java/com/theironyard/entities/Game.java`
  * `src/main/java/com/theironyard/entities/User.java`
* `services`
  * `src/main/java/com/theironyard/services/GameRepository.java`
  * `src/main/java/com/theironyard/services/UserRepository.java`

Now let's work on secure password storage. Thus far, we've been saving passwords in plaintext, which is a big mistake. If your server ever gets compromized, the intruder now has everyone's password. The solution to this is to hash the passwords before inserting them into the database, and when the user attempts to login you can simply hash the password they send and compare it to the hash in the database.

There are a few things to keep in mind about hashing passwords. If an attacker gets a database full of hashes, they can attempt to find the plaintext passwords through a brute force attack. There are two primary ways we can thwart this:

1. Use a hashing algorithm that takes a relatively long time. This will make it take longer for an attacker to brute force the hashes.
2. Use a salt -- i.e., a random number that you add to the password before hashing and store alongside the hash. This prevents an attacker from pre-generating hashes based on common passwords (known as rainbow tables).

A common hashing algorithm is SHA1. One problem, though, is that it is designed to be fast. To securely store it, we should run SHA1 at least a thousand times by taking the output of the previous hashing and running it through SHA1 again. A standardized way of doing this is called [PBKDF2](https://en.wikipedia.org/wiki/PBKDF2).

## GameTrackerSpring

Instead of trying to implement these features ourselves, we'll use a ready-made class that implements PBKDF2 as well as salting. Download [PasswordStorage.java](https://raw.githubusercontent.com/defuse/password-hashing/master/PasswordStorage.java) and move it into the following path in your project: `src/main/java/com/theironyard/utilities/PasswordStorage.java`

You may need to add the correct package name, `com.theironyard.utilities`, at the top of the file if your IDE doesn't do it for you. Then modify the `/login` route to use this class:

```java
@Controller
public class GameTrackerController {
    ...
    
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
    
    ...
}
```

Also modify your `init` method to use it as well:

```java
@Controller
public class GameTrackerController {
    ...
    
    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            User user = new User();
            user.name = "Zach";
            user.password = PasswordStorage.createHash("hunter2");
            users.save(user);
        }
    }
    
    ...
}
```

Before trying it out, go to `psql` and run `DROP DATABASE gametracker;` and then `CREATE DATABASE gametracker;` again. Since the old `users` table wasn't using hashes, it will no longer work, so we need to start fresh to try it out.
