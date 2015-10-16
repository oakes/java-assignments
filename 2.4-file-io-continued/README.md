# File I/O Continued

![screenshot](screenshot.jpg)

## Description

Create a project and put "countries.txt" in its root. Parse each line and store the contents into a data structure. Ask the user to type a letter and save a file that lists only the countries starting with it.

## Requirements

* Create a `Country` class to store both the name and abbreviation
* Read and parse the "countries.txt" file into an `HashMap<String, ArrayList<Country>>` where the key is a single letter and the value is a list of countries whose names start with that letter.
* Ask the user to type a letter
* Save an "X_countries.txt" file, where X is the letter they typed, which only lists the countries starting with that letter
