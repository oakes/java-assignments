# JSON Spring

![screenshot](screenshot.png)

## Description

Turn the IronGram project we made in class into a SnapChat-like service by making it delete the photos briefly after they are first viewed.

## Requirements

* Delete photos from the database and the disk if they were viewed by the `/photos` route 10 or more seconds ago. This requirement is open-ended -- you need to figure out how to do it.
* Create an input box in the upload form to let the user specify how many seconds they want the photo to exist. Store the number in the `Photo` entity. Then use that number instead of 10 seconds in your deletion code.
* Create a checkbox in the upload form to let the user specify whether the photo should be public. Store the boolean in the `Photo` entity.
* Create a JSON route called `/public-photos` which takes a username as an argument. Make it return a list of photos sent by that user which were marked as public.
  * Remember: We're just returning JSON data here, not the actual photos.
