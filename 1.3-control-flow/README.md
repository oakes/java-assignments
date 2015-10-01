# Control Flow

## Description

Create a program that mimics an ATM. From the REPL, it should be possible to start it up using `ATM.run()`.

![earthbound](earthbound.png)

## Requirements

* Prompt me for my name
  * If I enter nothing, then it should continue to prompt me for my name
* Prompt me for what I would like to do
  * 1. Check my balance, 2. Withdraw Funds or 3. Cancel
  * If I enter a number that is not within this list, it needs to continue to prompt me to enter either 1, 2 or 3
  * Check Balance
    * Print to the console `"Your balance is $__"`
  * Cancel
    * Print to the console `"Thank you and please come again."`
  * Withdraw Funds
    * Prompt for how much money to withdraw
      * If the amount is greater than the amount of money in the machine, throw an exception with a message saying the machine doesn't have that much money
      * If the amount is greater than the amount of money the user has available, throw an exception with a message saying the user has insufficient funds
      * If all is well, tell them to please take their money and then tell them their new balance
