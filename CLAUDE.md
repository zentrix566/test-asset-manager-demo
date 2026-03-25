# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is `test-asset-manager-demo`, a simple demo asset management system written in Java. It uses **Spring Boot** with web interface and in-memory mock data.

## Project Structure

```
/
├── pom.xml                                              - Maven Spring Boot configuration
├── LICENSE                                              - MIT License
├── README.md                                            - Project description
├── CLAUDE.md                                            - This file
└── src/
    └── main/
        ├── java/com/assetmanager/
        │   ├── Asset.java                              - Asset entity class with AssetType enum
        │   ├── AssetService.java                       - Business logic service with mock data
        │   ├── AssetManagerApplication.java            - Spring Boot main application
        │   └── AssetController.java                    - REST API controller
        └── resources/static/
            └── index.html                              - HTML/JavaScript frontend page
```

## Build/Run Commands

*   `mvn clean compile` - Compile the project
*   `mvn clean package` - Build executable JAR file
*   `java -jar target/asset-manager-demo-1.0-SNAPSHOT.jar` - Run the application
*   `mvn spring-boot:run` - Run application directly with Maven
*   `mvn test` - Run tests

After running, open browser at `http://localhost:8080` to access the web interface.

## Code Architecture

*   **Asset**: Entity class representing an asset with ID, name, type, value, purchase date, location, and status.
*   **AssetService**: Service layer that manages assets. Provides CRUD operations, search/filter, and statistics. Uses `HashMap` for in-memory storage with pre-initialized mock data (10 sample assets).
*   **AssetController**: REST API controller that exposes all operations via HTTP endpoints.
*   **AssetManagerApplication**: Spring Boot application entry point.
*   **index.html**: Responsive frontend with HTML/CSS/vanilla JavaScript.

## Features

- List all assets with pagination-free table view
- Search assets by name keyword
- Filter assets by type and status
- View statistics cards (total count, total value)
- Add new assets via modal form
- Delete assets
- All data is in-memory (resets on application restart)

## API Endpoints

- `GET /api/assets` - Get all assets
- `GET /api/assets/search?keyword=xxx` - Search by name
- `GET /api/assets/type/{type}` - Filter by type
- `GET /api/assets/status/{status}` - Filter by status
- `GET /api/assets/statistics` - Get statistics
- `POST /api/assets` - Add new asset
- `DELETE /api/assets/{id}` - Delete asset
