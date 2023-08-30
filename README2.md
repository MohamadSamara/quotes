# Random Quotes From JSON File And API

This is a simple Java application that fetches quotes from an external API and updates a local JSON file with the quotes, avoiding duplicates. It also provides the ability to display a random quote from the local file.

## Features
- Loads a collection of quotes from a JSON file.
- Displays a random quote along with its author's name.

## Getting Started

To run the Random Quotes Application on your local machine, follow these steps:

1. Clone this repository to your local machine.
2. Make sure you have Java and a Java development environment set up on your machine.
3. Navigate to the root directory of the cloned repository.

### Dependencies

The application uses Gson library for parsing JSON files. To add this dependency, ensure you have the following in your `build.gradle`:

```
dependencies {
    implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.9'
}
```

## Running the Application

1. Compile the code using the following command:

   ```
   ./gradlew build
   ```

2. Run the application using the following command:

   ```
   ./gradlew run
   ```
   This will load quotes from the `recentquotes.json` file, choose a random quote, and display it along with the author's name.

## Outputs

When you run the application, you will see the following outputs:

   * The author and quote fetched from the external API (if available).
   * The author and text of a random local quote (if available).

## Testing

The application is thoroughly tested using unit tests. You can run the tests using the following command:

```
./gradlew test
```
