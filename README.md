# Elevator

## Description
A simple Java-based simulation of an elevator system with a graphical user interface (GUI). The user can press buttons for different floors, and the elevator will move accordingly.

## Features
- A GUI that allows users to select floors.
- Simple elevator movement between floors.
- Status updates after each floor change.

## Unimplemented Features
- Advanced scheduling (shortest-path algorithm, priority scheduling, etc.).
- Multiple elevators or prioritization logic.
- Fault detection or power failure handling.
- Capacity tracking

## Assumptions
- There are a minimum of 2 floors, maximum of 163.
- The elevator starts at floor 0 (ground floor).
- The elevator processes requests sequentially. It will complete a request before moving to the next one.
- The elevator moves either "Up" or "Down" to reach the requested floor. It doesn’t have complex scheduling, so it doesn't optimize the path between multiple requests.

## Requirements

- **Java 21** (or compatible version)
- **Gradle** (version 8.0 or higher)
- **JUnit 5** for testing

## Building the Project

1. **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd elevator
    ```

2. **Build the project**:
    Gradle will automatically download and set up dependencies for you. To build the project, run the following command:
    ```bash
    ./gradlew build
    ```

    This will compile the code, resolve dependencies, and create an executable JAR file.

## Running the Application

To run the application, use the following Gradle command:
```bash
./gradlew run
```

## Testing the Application

JUnit 5 is used for testing. To run the tests, execute:
```bash
./gradlew test
```

## Clean the build artifacts

Gradle generates various build artifacts during the build process. You can remove these generated files using Gradle’s clean task:
```bash
./gradlew clean
```
