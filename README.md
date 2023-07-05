

A maven spring boot application with four restful operations.

The four operations are put, post, get, delete.

1. Put - The put function  takes a user object as a @RequestBody with the following fields - firstName, lastName, contactNumber and write this user object into a file 2. Post - The post function should take a user object as a @RequestBody and update the file with with new user details.

3. Get - The get function  returns the user object from the file.

4. Delete - The delete function deletes the  user file.



## Setup Instructions

- Clone the repository:
   https://github.com/Lerato-sebothoma/RESTFULOPERATIONS.git
- mvn spring-boot:run
  Open your web browser and access the application at http://localhost:8080
