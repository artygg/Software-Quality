# Software Quality Project

This project demonstrates software quality principles through a presentation software application (JabberPoint). The focus is on code quality, testing practices, and maintainable architecture.

## Project Overview

The project implements a presentation software with the following quality aspects:
- Clean architecture with separation of concerns
- Comprehensive unit testing
- Design patterns (Factory method, Command, Strategy)
- Code quality metrics
- Continuous Integration

### Testing Strategy
1. Unit Testing
   - Focus on business logic
   - No GUI testing
   - Headless environment support
   - Temporary file handling

2. Test Categories
   - Model Tests
   - Factory Tests
   - Controller Tests
   - Accessor Tests
   - Command Tests

## Project Structure

```
src/
├── main/java/           # Source code
│   ├── model/          # Data models
│   ├── view/           # UI components
│   ├── controller/     # Controllers
│   └── util/           # Utilities
└── test/java/          # Test code
```

## Key Components

### Models
- Presentation management
- Slide handling
- Content items (Text, Bitmap)

### Factories
- Object creation
- Type management
- Extensibility

### Controllers
- Navigation
- Slide management
- User input handling

### Accessors
- File operations
- XML handling
- Data persistence

### Commands
- Command pattern implementation
- User action handling
- Operation encapsulation

## Quality Guidelines

### Code Quality
1. Clean Code Principles
   - Meaningful names
   - Small functions
   - Clear comments
   - DRY principle

2. Design Patterns
   - Factory Pattern
   - Command Pattern
   - MVC Architecture
   - Observer Pattern

3. Testing Principles
   - Single Responsibility
   - Dependency Injection
   - Interface Segregation
   - Open/Closed Principle

### Testing Guidelines
1. No GUI Testing
   - Focus on business logic
   - Mock UI dependencies
   - Test controllers instead

2. Headless Testing
   - Use TestUtils
   - Mock GUI components
   - Temporary files

3. Test Organization
   - Clear test names
   - Single assertion focus
   - Proper setup/teardown
   - Error case coverage

## Build and Test

### Prerequisites
- Java 11+
- Maven 3.6+
- JUnit 5

### Commands
```bash
# Build
mvn clean compile

# Test
mvn test

# Package
mvn package
```

## Continuous Integration

The project uses GitHub Actions for:
- Automated testing
- Code quality checks
- Build verification
- Coverage reporting

## Best Practices

1. Code Organization
   - Clear package structure
   - Logical component grouping
   - Consistent naming

2. Testing
   - Write tests first
   - Cover edge cases
   - Mock external dependencies
   - Clear test documentation

3. Documentation
   - Clear class purpose
   - Method documentation
   - Test descriptions
   - Architecture overview

## Contributing

1. Follow quality guidelines
2. Write tests for new code
3. Maintain coverage standards
4. Document changes
5. Create pull requests

## License

MIT License - See LICENSE file
