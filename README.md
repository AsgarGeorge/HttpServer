# Java HTTP Server

This project is a simple HTTP server implemented in Java. It allows you to configure the server's port and webroot via a JSON configuration file and handles requests using multithreading.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

This Java HTTP server reads configuration data from a file and starts a multithreaded server to handle HTTP requests. It is designed to serve web resources from a specified directory (webroot) and can be extended to handle additional functionality such as routing, file handling, or other application logic.

## Features
- HTTP server with customizable configurations (port, webroot)
- Multithreaded request handling
- Logger integration for monitoring server activity

## Technologies Used
- **Java**
- **SLF4J** for logging
- **JSON** configuration files

## Installation

### Prerequisites
- Java JDK 8 or later
- Maven (optional for managing dependencies)

### Steps
1. Clone this repository:
    ```bash
    git clone https://github.com/AsgarGeorge/HttpServer.git
    cd HttpServer
    ```

2. Compile the project using `javac` or your preferred IDE:
    ```bash
    javac -d out src/main/java/httpserver/*.java
    ```

3. Run the project:
    ```bash
    java -cp out httpserver.HttpServer
    ```

## Usage

- The server will start based on the configurations provided in the `http.json` configuration file.
- You can modify the `http.json` file located in `src/main/resources/` to change the server port and webroot directory.

## Configuration

The server uses a configuration file named `http.json` located in `src/main/resources/`. It contains two main settings:

- `port`: The port on which the server will listen for incoming requests.
- `webroot`: The directory where web files (HTML, CSS, JS, etc.) are served from.

Example `http.json`:
```json
{
  "port": 8080,
  "webroot": "/path/to/webroot"
}
```

## Project Structure

```
├── src
│   └── main
│       └── java
│           └── httpserver
│               ├── HttpServer.java         # Main server class
│               ├── config
│               │   ├── Configuration.java  # Holds configuration data
│               │   └── ConfigurationManager.java # Loads and manages configurations
│               └── core
│                   └── ServerThread.java   # Handles server thread and request management
└── resources
    └── http.json                           # Configuration file
```

## Future Enhancements
- Implement more advanced routing for different HTTP methods (GET, POST, etc.)
- Add support for serving dynamic content.
- Integrate a templating engine for serving HTML views.
- Add middleware for logging, security, etc.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the MIT License.

---

This `README.md` provides an overview of the project, instructions for installation, usage, configuration, and possible future improvements. Let me know if you need further adjustments!
