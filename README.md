# Library_API

### End-points
| Request Type | URL                                | Functionality                           | Access  |
|--------------|------------------------------------|-----------------------------------------|---------|
| GET          | /api/books/                        | Lists all books                         | Public  |
| POST         | /api/books/                        | Adds a new book                         | Private |
| GET          | /api/books/{bookId}                | Gets a book by book ID                  | Public  |
| PUT          | /api/books/{bookId}                | Updates book information by book ID     | Private |
| DELETE       | /api/books/{bookId}                | Deletes a book by book ID               | Private |
| GET          | /api/authors/                      | Lists all authors                       | Public  |
| POST         | /api/authors/                      | Adds a new author                       | Private |
| GET          | /api/authors/{authorID}            | Gets an author by author ID             | Public  |
| PUT          | /api/authors/{authorID}            | Updates author information by author ID | Private |
| DELETE       | /api/authors/{authorID}            | Deletes an author by author ID          | Private |
| GET          | /api/reviews/                      | Lists all reviews                       | Public  |
| GET          | /api/reviews/books/{bookID}/       | Lists all reviews by book ID            | Public  |
| POST         | /api/reviews/books/{bookID}/       | Adds a new review by book ID            | Private |
| GET          | /api/reviews/{reviewId}/           | Gets a review by reviewId               | Public  |
| GET          | /api/reviews/users/{userId}        | Lists all reviews by user ID            | Public  |
| PUT          | /api/reviews/{reviewId}            | Updates a review by review ID           | Private |
| DELETE       | /api/reviews/{reviewId}            | Deletes a review by review ID           | Private |
| GET          | /api/loans/                        | Lists all loans                         | Private |
| POST         | /api/loans/                        | Creates a new loan entry                | Private |
| GET          | /api/loans/{loanId}                | Gets a loan entry by loan ID            | Private |
| GET          | /api/loans/{loanId}/users/{userId} | Gets a loan entry by user ID            | Private |
| GET          | /api/loans/{loanId}/books/{bookId} | Gets a loan entry by book ID            | Private |
| PUT          | /api/loans/{loanId}/               | Updates a loan entry by loan ID         | Private |
| DELETE       | /api/loans/{loanId}/               | Deletes a loan entry by loan ID         | Private |
| POST         | /auth/users/register/              | Registers a new user                    | Private |
| POST         | /auth/users/login/                 | Logs a user in                          | Private |
| PUT          | /auth/users/update/                | Updates user information                | Private |
