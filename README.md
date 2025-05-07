# Furia Chat Bot

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green.svg)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-19-red.svg)](https://angular.io/)

## Overview

A chat bot with simple commands to fetch information about matches, teams, and players related to Furia's CS2 teams, allowing queries by name, teams tag, region, and matches status.

## Key Features

### Match Commands
Query scheduled, completed, and live matches:
- `/ScheduledMatches` - Get upcoming matches
- `/ClosedMatches` - Get completed matches
- `/LiveMatches` - Get matches currently in progress

### Team Commands
Retrieve teams-related data:
- `/TagTeam <Team Tag>` - Find teams by tag name
- `/NameTeam <Team Name>` - Find teams by full name
- `/RegionTeams <Region>` - List teams by region

### Player Commands
Access players information:
- `/NamePlayer <Name>` - Find players by name
- `/TeamPlayers <Team Name>` - List all players on a specific teams

### RoadMap Features

- WebScrap to collect real data
- WebSockets to update real time matches results
- Improve data models to more details

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 21
- Gradle (dependency management and build)
- Docker (for RabbitMQ containerization)
- PostgreSQL database (H2 is used with dev profile)
- Angular CLI (for frontend)

### Installation

1. Clone the repositories:
   ```bash
   # Backend
   git clone https://github.com/Hyuse98/furia-chat-bot-backend
   
   # Frontend
   git clone https://github.com/Hyuse98/furia-chat-bot-frontend
   ```

2. Environment Variables:
   ```
   JWT_EXPIRATION=3600000
   JWT_SECRET=<your-SHA256-key>
   SPRING_PROFILES_ACTIVE=dev
   ```

### Running the Application

1. Start Backend (Java 21 with Gradle):
   ```bash
   cd backend
   ./gradlew bootRun
   ```

2. Start Frontend (Angular 19):
   ```bash
   cd frontend
   ng serve -o
   ```

## API Usage

The API is consumed by the frontend which listens to `http://localhost:8080/api/chat` for users commands.

### Available Endpoints
All available endpoints can be viewed via OpenAPI Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Diagrams

### UML Class Diagram
````mermaid
classDiagram
  class User {
    +id: UUID
    +username: String
    +email: String
    +password: String
    +createdAt: LocalDateTime
  }
  class Coach {
    +id: Long
    +name: String
    +username: String
    +teams: String
    +hltvUrl: String
  }
  class Players {
    +id: Long
    +name: String
    +username: String
    +teams: String
    +hltvUrl: String
  }
  class Team {
    +id: Long
    +teamName: String
    +teamTag: String
    +teamRegion: String
    +players: List<Players>
    +hltvUrl: String
  }
  class Matchs {
    +id: Long
    +teamA_Teams: Team
    +teamB_Teams: Team
    +dateHour: LocalDateTime
    +matchesStatus: MatchStatus
    +teamAScore: int
    +teamBScore: int
    +matchResult: matchResult
    +mapPool: List<Maps>
  }
  class Maps {
    +id: Long
    +mapName: String
    +teamAMapScore: int
    +teamBMapScore: int
  }

  Coach --|> Team : belongs to
  Players --|> Team : belongs to
  Team "1" -- "*" Matchs : has >
  Maps "*" -- "1..*" Matchs : included in
````

### Architecture

```mermaid
graph LR
    A[Frontend] --> B(Backend);
    B --> C(Database);
```

## Data Flow

### Authentication Flow
```mermaid
sequenceDiagram
    participant Frontend
    participant Backend
    participant Database

    Note over Frontend,Backend: Authentication

    Frontend->>Backend: POST /auth/register (Registration data)
    Backend->>Database: Save new users
    Database-->>Backend: Success/Failure
    Backend-->>Frontend: 200 OK / 400 Bad Request

    Frontend->>Backend: POST /auth/login (Credentials)
    Backend->>Database: Verify credentials
    Database-->>Backend: User found/not found
    Backend-->>Frontend: 200 OK (with token) / 401 Unauthorized

    Note over Frontend,Backend: Chat

    Frontend->>Backend: GET http://localhost:8080/api/chat (with token)
    Backend->>Database: Fetch chat messages
    Database-->>Backend: Chat messages
    Backend-->>Frontend: 200 OK (Chat data)
```

### User Interaction Flow
```mermaid
sequenceDiagram
    participant User
    participant Frontend
    participant Backend
    participant ChatService
    participant Database

    User->>Frontend: Types and sends message/command
    Frontend->>Backend: POST /api/chat (Message/Command, Token)
    Backend->>ChatService: Process message/command
    alt Command (e.g., /list_users)
        ChatService->>Database: Fetch data (e.g., users list)
        Database-->>ChatService: Data found
        ChatService->>Backend: Command response
    else Normal message
        ChatService->>Database: Save message
        Database-->>ChatService: Success
        ChatService->>Backend: Message saved
    end
    Backend->>Frontend: 200 OK (Chat response)
    Frontend->>User: Display chat response
```

## Technologies

- Java 21 SDK
- Spring Boot 3
- Angular 19
- PostgreSQL
- H2 Database (development)

## Project Structure

```
furia-chat-bot/
├── src/
│   ├── main/
│   │   ├── java/com/hyuse/chatbot/
│   │   │   ├── auth/
│   │   │   ├── chat/
│   │   │   ├── core/
│   │   │   │   ├── constants/
│   │   │   │   └── exceptions/
│   │   │   ├── config/
│   │   │   ├── matches/
│   │   │   ├── players/
│   │   │   ├── teams/
│   │   │   ├── user/
│   │   │   ├── maps/
│   │   │   ├── coach/
│   │   │   └── FuriaChatBotApplication.java
│   │   └── resources/
│   │       ├── db/migration
│   │       ├── application-prod.yml
│   │       └── application-dev.yml
│   └── test/
└── build.gradle
  
frontend/
    ├── src/
    │   ├── app/
    │   │   ├── auth/
    │   │   │   ├── components/
    │   │   │   ├── guards/
    │   │   │   ├── interceptor/
    │   │   │   ├── models/
    │   │   │   ├── pages/
    │   │   │   └── services/
    │   │   └── pages/
    │   │       ├── chat/
    │   │       └── services/
    │   ├── assets/
    │   └── environments/
    └── angular.json
```

## Security

- JWT-based authentication is implemented
- Tokens expire after 1 hour (configurable via `JWT_EXPIRATION`)
- All API endpoints except login/register require authentication

## Error Handling

The application includes comprehensive error handling:
- Invalid commands return helpful error messages
- Network errors are displayed to the users
- Database connection issues are logged and reported

## Development

### Running in Development Mode
```bash
# Backend with H2 in-memory database
./gradlew bootRun --args='--spring.profiles.active=dev'

# Frontend with auto-reload
ng serve --configuration=development
```

### Running Tests
```bash
# Backend tests
./gradlew test

# Frontend tests
ng test
```

## Deployment

### Building for Production
```bash
# Backend
./gradlew clean build

# Frontend
ng build --configuration=production
```

### Docker Support
A Docker Compose file is available for easy deployment:
```bash
docker-compose up -d
```