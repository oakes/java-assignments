# Microblog Continued

![screenshot](screenshot.jpg)

## Description

Add functionality to your microblog.

## Requirements

* Add multi-user support by storing your users in a `HashMap<String, User>` and putting your `ArrayList<Message>` inside the `User` object.
  * Make sure to check the password when they log in, and throw an error if it's incorrect.
  * Show a logout button when the user is logged in.
* In the `/create-user` route, get the `Session` object and save the username there using the `attribute` method.
* Add a form in `messages.html` which lets you delete a message by entering its number.
* Add a form in `messages.html` which lets you edit a message by entering its number and the text you want to replace it with.

![screenshot 1](screenshot1.png)
![screenshot 2](screenshot2.png)
