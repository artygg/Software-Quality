# Presentation Application

A Java application for creating and viewing presentations.

## Features

- Create and edit slides
- Add text and images to slides
- Navigate between slides
- Save and load presentations in XML format
- User-friendly error handling

## SOLID Principles Implementation

This project implements several SOLID principles:

1. **Single Responsibility Principle (SRP)**
   - Created `SlideManager` and `NavigationController` interfaces
   - Implemented `ErrorDisplay` utility class
   - Separated validation logic in `SlideItem` subclasses

2. **Open/Closed Principle (OCP)**
   - Created interfaces that allow extension without modification
   - Implemented factory pattern for slide items
   - Added validation methods that can be extended in subclasses

3. **Liskov Substitution Principle (LSP)**
   - Added validation methods to ensure consistent behavior
   - Improved error handling in subclasses
   - Ensured subclasses can be used interchangeably with parent classes

4. **Interface Segregation Principle (ISP)**
   - Split `SlideItem` into smaller interfaces (`Drawable`, `Bounded`, `Leveled`)
   - Created focused interfaces for specific functionality
   - Separated navigation and slide management interfaces

5. **Dependency Inversion Principle (DIP)**
   - Created abstractions for file operations and slide rendering
   - Used interfaces instead of concrete implementations
   - Implemented factory pattern to depend on abstractions

## Building and Running

### Prerequisites

- Java 8 or higher
- Gradle (optional, for building with Gradle)

### Building with Gradle

```bash
./gradlew build
```

### Running the Application

```bash
./gradlew run
```

Or directly with Java:

```bash
java -jar build/libs/presentation.jar
```

## Running Tests

### Running Tests with Gradle

```bash
./gradlew test
```

### Running Tests with JUnit

```bash
./gradlew test --tests "*.Test"
```

## Project Structure

- `src/main/java`: Main source code
- `src/test/java`: Test code
- `build.gradle`: Gradle build file

## License

This project is licensed under the MIT License - see the LICENSE file for details.
