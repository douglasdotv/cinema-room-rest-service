## Cinema Room REST Service

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/cinema-room-rest-service/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/cinema-room-rest-service/blob/master/README.pt-br.md)

### Project description

This project is a REST service that allows one to manage a cinema room.

### API endpoints

- GET /seats — returns all seats in the cinema room

#### Example
```json 
    {
    "total_rows": 9,
    "total_columns": 9,
    "seats": [
        {
        "row": 1,
        "column": 1,
        "price": 10
        },
        {
        "row": 1,
        "column": 2,
        "price": 10
        },
        {
        "row": 1,
        "column": 3,
        "price": 10
        },
        {
        "row": 1,
        "column": 4,
        "price": 10
        },
        {
        "row": 1,
        "column": 5,
        "price": 10
        },
        {
        "row": 1,
        "column": 6,
        "price": 10
        },
        {
        "row": 1,
        "column": 7,
        "price": 10
        },
        {
        "row": 1,
        "column": 8,
        "price": 10
        },
        {
        "row": 1,
        "column": 9,
        "price": 10
        },
        {
        "row": 2,
        "column": 1,
        "price": 10
        },
        {
        "row": 2,
        "column": 2,
        "price": 10
        },
        {
        "row": 2,
        "column": 3,
        "price": 10
        },
        {
        "row": 2,
        "column": 4,
        "price": 10
        },
        {
        "row": 2,
        "column": 5,
        "price": 10
        },
        ...
        {
       "row": 9,
        "column": 9,
        "price": 8
        }
    ]   
    }
```

- POST /purchases — purchases a ticket (if the requested seat is available)

#### Example
```json 
request
{
  "row": 1,
  "column": 1
}
```

```json 
response
{
    "token": "UUID",
        {
            "row": 1,
            "column": 1,
            "price": 10
        }
}
```

- POST /return — returns a purchased ticket (if the sent ticket token is valid)

#### Example
```json 
request
{
    "token": "UUID"
}
```

```json 
response
{
    "returned_ticket":
        {
            "row": 1,
            "column": 1,
            "price": 10
        }
}
```

- POST /stats — returns stats regarding the current income, the number of available seats and the number of purchased tickets (if the sent password is correct)

#### Example
request: @ParamRequest 123456

```json 
response
{
  "total_income": 0,
  "number_of_available_seats": 81,
  "number_of_purchased_tickets": 0
}
```

### Exception handling

The exception handling is done in the CinemaRoomControllerAdvice class, which is annotated with @RestControllerAdvice.

The program may throw the following exceptions when performing requests:
- UnavailableSeatException — when trying to purchase a ticket and the seat is already taken or the row or column number is out of bounds
- InvalidTicketException — when trying to return a ticket and the token is invalid
- InvalidPasswordException — when trying to get statistics and the provided password parameter is incorrect or missing

In such cases, the program will return a JSON object with the following structure:
```json 
{
  "error": "error message"
}
```

### What I used

- Java 17
- Spring Boot 2.5.6
- Lombok
  
### What I learned

While doing this project, I learned how to handle HTTP requests in controllers and respond with JSON objects and proper status codes.


### How to run

1. Open a terminal and clone this repository: _git clone https://github.com/douglasdotv/cinema-room-rest-service.git_
2. Navigate to the project's root folder: _cd cinema-room-rest-service_
3. Build the project: _./gradlew build_
4. Run the application: _./gradlew bootRun_
5. Access the available endpoints using a client (like Postman or Insomnia, for example)


### Additional notes

- The cinema room has 9 rows and 9 columns, with a total of 81 seats.
- The first 4 rows have a price of 10, and the last 5 rows have a price of 8.
- The ticket token is a UUID object, which is generated when a ticket is purchased.
- The password to get statistics is "super_secret" and it's visible in the CinemaService class. However, it could be moved to a properties file or environment variable.
- No database was used in this project, but we could use a framework like Spring Data JPA to connect to a database and persist the data.


### Contact
If you have any questions or suggestions, feel free to contact me at [LinkedIn](https://www.linkedin.com/in/douglasdotv) or via email (douglas16722@gmail.com).