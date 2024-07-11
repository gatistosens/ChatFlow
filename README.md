
# ChatEngine - A Conversational User Input Processor

## Overview

ChatEngine is a simple Java-based conversational user input processor designed to simulate a chatbot for collecting user information. It leverages various input validators to ensure the correctness of user-provided data. This project is intended to be integrated into larger systems, and its main class demonstrates how you can add new questions and utilize the ChatEngine.

## Features

- **Input Validation**: Includes various validators (e.g., text, email, date, choice) to ensure the inputs are valid.
- **Flexible Question Types**: Supports different types of questions including text input, single choice, and no input required.
- **Mock Input Provider for Testing**: Includes a mock input provider for unit testing purposes.
- **Demonstration Main Class**: Shows how to add new questions and use the ChatEngine in a simple console-based example.

## Project Structure

```plaintext
io.codelex
├── input
│   ├── ConsoleInputProvider.java    // Provides console input for demonstration purposes
│   └── InputProvider.java           // Interface for input providers
├── question
│   ├── Question.java                // Represents a question
│   ├── QuestionResponse.java        // Represents a question response
│   ├── QuestionType.java            // Enum for question types
├── validation
│   ├── ChoiceValidator.java         // Validates single choice inputs
│   ├── DateValidator.java           // Validates date inputs
│   ├── EmailValidator.java          // Validates email inputs
│   └── TextValidator.java           // Validates text inputs
├── ChatEngine.java                  // Core engine handling the chat logic
├── Answers.java                     // Stores and manages user answers
└── Main.java                        // Main entry point for demonstrating how to use the ChatEngine
tests
├── ChatEngineTest.java              // Unit tests for ChatEngine
└── MockInputProvider.java           // Mock input provider for testing
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Gradle

### Running the Demonstration Application

1. **Clone the Repository**

    ```sh
    git clone https://github.com/gatistosens/ChatFlow.git
    cd ChatFlow
    ```

2. **Run the Application**

    ```sh
    ./gradlew run
    ```


