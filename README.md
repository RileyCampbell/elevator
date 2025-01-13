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

## Build/Compile

1. **Navigate to the `src` directory**:
    ```bash
    cd Elevator/src
    ```
2. **Compile the code**:*
    ```bash
    javac elevator/*.java
    ```

## Run

1. **Navigate to the `src` directory**:
  ```bash
  cd Elevator/src
  ```
2. **Run the program**:
  ```bash
  java elevator.Main
  ```

## Clean

1. **Navigate to the `src` directory**:
  ```bash
  cd Elevator/src
  ```
2. **Delete the class files**:
  ```bash
  rm -rf elevator/*.class
  ```
