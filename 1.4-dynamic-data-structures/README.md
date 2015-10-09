# Dynamic Data Structures

![balamb garden](balambgarden.jpg)

## Description

Extend your ATM code to allow adding and removing bank accounts. Obviously, this is not typically a feature of ATMs, but we are innovators.

## Requirements

* Create a `HashMap` to store everyone's balances. It should map names (`String`) to balances (`double`).
* When the user types a name that isn't recognized (i.e., isn't in the `HashMap`), offer to create an account for them. Then show them the screen listing the three options like before.
* Keep looping back to the beginning until the user cancels. The `HashMap` should keep its previously-set values when it loops around.
* Create a fourth option to remove a the current user's bank account.
