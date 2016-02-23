# Microblog Continued

![screenshot](screenshot.jpg)

## Description

Add functionality to your microblog.

## Requirements

* Add a password field to the login form. If it's incorrect, throw an error.
* In the `/create-user` route, save the username into the session.
* Add multi-user support by storing your users in a `HashMap<String, User>` and putting your `ArrayList<Message>` inside the `User` object.
* Show a logout button when the user is logged in. It should invalidate the session and refresh the page so you can log in again with a new user.
* Add a form in `messages.html` which lets you delete a message by entering its number.
* Add a form in `messages.html` which lets you edit a message by entering its number and the text you want to replace it with.

![screenshot 1](screenshot1.png)
![screenshot 2](screenshot2.png)
