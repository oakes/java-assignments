# libGDX

![screenshot](screenshot.jpg)

## Description

Fork the [Minicraft](../projects/Minicraft) project. Write the necessary code to make the player walk around and stuff.

## Requirements

* Copy in the `move` method from `HelloGame` and define whatever variables it needs.
* Make the game draw the correct sprite (down, up, left, right) based on which direction you are going.
* Prevent the player from walking outside of the screen.
* Optional:
  * When hitting an edge, make the player "teleport" to the opposite edge.
  * Animate the player's movement. When moving up or down, you can just flipping the tile horizontally. When moving left or right, you can switch between the standing and running tiles.
  * Bring in the zombie tiles to draw a bad guy that walks around randomly.
  * Draw a green background, then bring in the tree tile and draw it randomly in the background.
  * Use a `FitViewport` to make the game scale as the window is resized.
