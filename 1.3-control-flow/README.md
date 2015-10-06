# Control Flow

![earthbound](earthbound.png)

## Description

Create a program that mimics an ATM. From the REPL, it should be possible to start it up using `ATM.run()`.

## Requirements

* Prompt me for my name
  * If I enter nothing, throw an exception
* Prompt me for what I would like to do
  * 1. Check my balance, 2. Withdraw Funds or 3. Cancel
  * If I enter a number that is not within this list, throw an exception
  * Check Balance
    * Print to the console `"Your balance is $__"`
  * Cancel
    * Print to the console `"Thank you and please come again."`
  * Withdraw Funds
    * Prompt for how much money to withdraw
      * If the amount is greater than the amount of money in the machine, throw an exception
      * If the amount is greater than the amount of money the user has available, throw an exception
      * If all is well, tell them to please take their money and then tell them their new balance
