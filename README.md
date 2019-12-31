# Getting Started

## Introduction
The app stores shapes (currently only unrotated rectangles) in a MySql database and exposes a CRUD REST API. Validation is in place to ensure no two shapes can be added to the databas if they overlap.

## Prerequisites

Docker and Docker Compose: https://docs.docker.com/compose/install/

## Build instructions

$ cd shapes-service

$ ./gradlew build

$ docker-compose up

## Tests

Due to time contraints no automated tests have been written but the following can be ran manually to show functionality:-

### Create a new shape

$ curl -i -X POST -H "Content-Type:application/json" -d '{"name":"shape1","x1":"10","y1":"10","x2":"20","y2":"40"}' http://localhost:8080/shapes

### Find all shapes

$ curl http://localhost:8080/shapes

### Verify overlapping shapes are rejected

$ curl -i -X POST -H "Content-Type:application/json" -d '{"name":"shape2","x1":"15","y1":"15","x2":"25","y2":"45"}' http://localhost:8080/shapes



