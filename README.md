# Random Quotes From JSON File

The Quotes Application is a simple Java application that reads quotes from a JSON file and displays a random quote along with its author.

## Features
- Loads a collection of quotes from a JSON file.
- Displays a random quote along with its author's name.

## Dependency added

the following dependency was added to the file, build.gradle, during the build of the project, in order to use gson:

``` implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.9' ``` : From (https://mvnrepository.com/artifact/com.google.code.gson/gson/2.7)

## Setup
* Create a new repository for this partnered lab; call it quotes. In that repo, add a .gitignore file from the course repo, and then run gradle init --type java-application.

## Feature Tasks

* Use the file recentquotes.json to show random popular quotes
* Your program should use GSON to parse the .json file
* In the build.gradle put this in the dependencies: { implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.9'}
* To check if the Gson is working type in Gson gson = new Gson(); into the App class
* The app needs no functionality other than showing the quote and the author when it is run. The app should choose one quote each time it is run
* Ensure that you can test your code by running ./gradlew test
