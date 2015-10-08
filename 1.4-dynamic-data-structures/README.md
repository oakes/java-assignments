# Dynamic Data Structures

![balamb garden](balambgarden.jpg)

## Description

Extend your ATM code to allow adding and removing new bank accounts. Obviously, this is not typically a feature of ATMs, but we are innovators.

## Requirements

* Create a `HashMap` to store everyone's balances. It should map names (`String`) to balances (`double`).
* When the user types a name that isn't recognized (i.e., isn't in the `HashMap`), offer to create an account for them.
* Keep looping back to the beginning until the user cancels.
