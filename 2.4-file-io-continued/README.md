# File I/O Continued

![screenshot](screenshot.jpg)

## Description

Fork the [Countries](https://github.com/TIY-Charleston-Back-End-Oct2015/Countries) project. Parse each line and store the contents in an ArrayList of objects. Ask the user to type a letter and save a file that lists only the countries starting with it.

## Requirements

* Create a `Country` class to store both the name and abbreviation
* Read and parse the "countries.txt" file into an `HashMap<String, ArrayList<Country>>` where the key is the first letter of the country name.
* Ask the user to type a letter
* Save an "X_countries.txt" file, where X is the letter they typed, which only lists the countries starting with that letter
