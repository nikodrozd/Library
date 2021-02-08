Spring Boot "Library" Test Project
---
Java/Gradle/Spring Boot (version 2.2.2.RELEASE) application developed for test task purpose only

About the Service
---
This is simple RESTful project which imitate basic functions of web book library. It uses an in-memory database (H2) to store the data. Please, use port **8889** to call REST endpoints.

You can access DB console on /h2-console endpoint. DB connection details can be found in src/main/resources/application.properties file. 

Here are all REST endpoints you can call (with examples):

### Create USER
```
POST /api/users
{
    "name": "John",
    "surname": "Doe",
    "dateOfBirth": "19.11.1980"
}

RESPONSE: HTTP 201 (Created)
{
    "id": 385,
    "name": "John",
    "surname": "Doe",
    "dateOfBirth": "19.11.1980",
    "bookList": []
}
```
### Read USER
```
GET /api/users/{id}

RESPONSE: HTTP 200 (OK)
{
    "id": 385,
    "name": "John",
    "surname": "Doe",
    "dateOfBirth": "19.11.1980",
    "bookList": []
}
```
### Update USER
```
PUT /api/users/{id}
{
    "name": "John",
    "surname": "Smith",
    "dateOfBirth": "19.11.1980"
}

RESPONSE: HTTP 200 (OK)
{
    "id": 385,
    "name": "John",
    "surname": "Smith",
    "dateOfBirth": "19.11.1980",
    "bookList": []
}
```
### Delete USER
```
DELETE /api/users/{id}

RESPONSE: HTTP 204 (No Content)
```
### Create BOOK
```
POST /api/books
{
    "name": "The Hobbit",
    "author": "J.R.R.Tolkien"
}

RESPONSE: HTTP 201 (Created)
{
    "id": 386,
    "name": "The Hobbit",
    "author": "J.R.R.Tolkien"
}
```
### Read BOOK
```
GET /api/books/{id}

RESPONSE: HTTP 200 (OK)
{
    "id": 386,
    "name": "The Hobbit",
    "author": "J.R.R.Tolkien"
}
```
### Update BOOK
```
PUT /api/books/{id}
{
    "name": "The Hobbit 2",
    "author": "J.R.R.Tolkien"
}

RESPONSE: HTTP 200 (OK)
{
    "id": 386,
    "name": "The Hobbit 2",
    "author": "J.R.R.Tolkien"
}
```
### Delete BOOK
```
DELETE /api/books/{id}

RESPONSE: HTTP 204 (No Content)
```
### USER takes BOOK
```
PUT /api/users/{user_id}/books/{book_id}

RESPONSE: HTTP 200 (OK)
```
### USER returns BOOK
```
DELETE /api/users/{user_id}/books/{book_id}

RESPONSE: HTTP 204 (No Content)
```
### Retrieve a paginated list of USERs with BOOKs
```
GET /api/users?page=0
(page parameter is optional with default value 0)

RESPONSE: HTTP 200 (OK)
{
    "content": [
        {
            "id": 385,
            "name": "John",
            "surname": "Smith",
            "dateOfBirth": "19.11.1980",
            "bookList": [
                {
                    "id": 386,
                    "name": "The Hobbit",
                    "author": "J.R.R.Tolkien"
                }
            ]
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 0,
        "pageSize": 10,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalElements": 1,
    "last": true,
    "totalPages": 1,
    "numberOfElements": 1,
    "first": true,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "empty": false
}
```