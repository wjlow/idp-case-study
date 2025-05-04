# Seek Online Identity Case Study

This is an implementation of the Seek Online Identity Case Study PoC project.

The Frontend Javascript app is in `frontend/` and the Backend API code is in the project root as a Java/Kotlin project.

The Vanilla JS SPA and Java Spring Boot Quickstart projects were used to set up this project.

See [docs](./docs) directory for further discussion.

## Prerequisites

- Docker
- This app is pointing to the Auth0 account: jacklow@gmail.com

## To run Frontend and Backend in Docker

```bash
docker compose up
```

You can now access the Frontend at http://localhost:3000 and the Backend at http://localhost:3010/api/interviews

## To run Frontend and Backend outside of Docker

### Running the Frontend

```bash
cd frontend/
npm install && npm start
```

### Running the Backend

```bash
./gradlew clean bootRun
```

### Running the Backend tests

```bash
./gradlew clean test
```

