# simple_library_app
A simple library application with CRUD operations for books available.

Download the code and build it as a Spring Boot App.

Either create a MySQL database according to the application.properties params, or change them.

A user with all necessary permissions is required.

Run the app and a table called 'library' will be automatically created.

The endpoints are all of the form '/books/[...]', and are as described below.

GET localhost:8080/books/ - fetch all of the books

GET localhost:8080/books/<id> - fetch the book with ID value <id>. 404 if not found

POST localhost:8080/books/ {"title": "book_title","author": "book_author","genre": "book_genre","status": "READ or UNREAD"} - create a new book with the given details. The ID will be autogenerated. Any status other than READ or UNREAD will cause an error

PUT localhost:8080/books/<id> {"title": "book_title","author": "book_author","genre": "book_genre","status": "READ or UNREAD"} - modify the details of the book with ID value <id>

DELETE localhost:8080/books/<id> - delete the book with ID value <id>. 404 if not found

The UI web page requires some more work at the moment, but is currently in 'LibraryBackend/src/main/resources'.
