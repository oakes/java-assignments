# Object-Oriented Programming

![screenshot](screenshot.jpg)

## Description

Use inheritance in your inventory project. To do so, create at least five different classes for product categories. Each one should set a different value for the "category" field.

## Requirements

* Use `String.format` anywhere you are manually concatenating strings with `+`.
* Create at least five classes that extend your existing inventory item class, each one representing a product category.
* Each category class should have a constructor that sets the "category" field to the appropriate name.
* Create a static `createItem` method in your main class which returns an object using the correct category class (or throws and error for an invalid category name).
  * `static InventoryItem createItem(String category)`
* Use `createItem` to create a new item for option 1.
* When you list the items, list their category as well.

## Example

```java
// parent class

public class InventoryItem {
    String name;
    int quantity;
    String category;
}

// subclasses

public class Shoe extends InventoryItem {
    public Shoe() {
        category = "Shoe";
    }
}

public class Shirt extends InventoryItem {
    public Shirt() {
        category = "Shift";
    }
}
```
