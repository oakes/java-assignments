# Review

![screenshot](screenshot.png)

## Description

Expand on the AnonUpload project we made in class.

## Requirements

* Create a way to limit the number of files that can be uploaded. When it hits the limit (say, 10 files), delete the oldest file to make room for the latest one.
* Add a checkbox to the upload form that makes the file permanent. Then modify the aforementioned deletion code so it does not count permanent files when determining if the limit was hit.
* Add an input field to the upload form to let the user type a comment. Store it in the `AnonFile` class, and use it as the link text instead of the `originalName` field.
* Optional: Improve the styling using CSS.
