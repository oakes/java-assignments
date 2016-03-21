## Boxed vs Unboxed values

We've learn about simple scalar values like `int`, `double`, and `boolean`. You may have wondered why they don't start with a capital letter like `String` or `Scanner`. This is because they are primitive types that are baked more deeply into the language; they are not classes. There are, however, classes for them as well: `Integer`, `Double`, and `Boolean`.

These are known as the "boxed" versions because they literally box the value up into a class. You could theoretically write `Integer score = 1;`, but this isn't common, because all you're doing is reducing performance over using the unboxed version. One purpose of these classes is to provide special conversion methods. Try the following in JREPL:

* `Integer.valueOf("123");`
* `Double.valueOf("1.5");`
* `Boolean.valueOf("true");`

## Dynamic Data Structures

We've been over two fundamental data structures in Java, a sequential one known as an array, and an associative one known as a class. Both of them, however one downside: They cannot be resized at runtime. An array must know how many slots it contains in advance, and a class must know exactly what its fields are in advance (the reflection API notwithstanding).

This is where dynamic data structures come in. The primary dynamic sequential data structure is called an `ArrayList`, while the primary dynamic associative data structure is called a `HashMap`. With these additions, we can think of our data structure choices like this:

| | Sequential | Associative |
| --- | --- | --- |
| Static  | Array | Class |
| Dynamic  | ArrayList | HashMap |

Try creating them in JREPL:

* `ArrayList names = new ArrayList();`
* `names.add("Alice");`
* `names.add("Bob");`
* `names.add("Charlie");`
* `names.get(0);`
* `names.remove(0);`
* `HashMap person = new HashMap();`
* `person.put("name", "Alice");`
* `person.put("address", "17 Princess St");`
* `person.get("name");`
* `person.remove("name");`

Notice, however, that we get an error if we try to get the first name out of the `ArrayList` and store it in a `String` variable:

* `String name = names.get(0);`
* `ERROR: incompatible types: java.lang.Object cannot be converted to java.lang.String`

This is because the `ArrayList` doesn't remember what the types are of its contents unless you tell it to. We can do that by using "generics", a feature that tells this data structure what kinds of objects it can contain:

* `ArrayList<String> names = new ArrayList<>();`
* `HashMap<String, String> = new HashMap<>();`

Dynamic data structures are convenient, but don't use them if you don't need to. If you know your "person" variable needs to contain a name and address, why not just make a class? Static data structures are faster, use less memory, and are often safer at compile-time.
