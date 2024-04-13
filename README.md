# CodeGenClash2

Second part of the experiment for master thesis using Chat GPT-4 and Github Copilot.

The experiment is to develop a web-based employee management system that will allow users to manage employee records with Github Copilot and with Chat GPT-4 and then compare the results. The application should support basic CRUD (Create, Read, Update, Delete) operations for employee data, authentication, authorization and advanced search functions. The backend should be developed in Java, using Spring Boot to create RESTful services, and the frontend should be built using TypeScript and React.

Funtionalities:
- REST API for managing employee records. The API should support operations for creating, retrieving, updating and deleting employee data, as well as user login/logout.
- validation of incoming data to create or update employee records (both on frontend and backend)
- advanced search functionality to filter employees based on various criteria (e.g., department, position, salary range)
- unit tests in JUnit and Mockito for backend components
- a user-friendly user interface for the application.
    - view for login,
    - employee list + advanced search
    - employee details.
- application state management
- using Spring Data JPA to integrate with a relational database
- role-based authentication and authorization
- global exception handling to manage different types of exceptions and return appropriate responses to the client (bakcend + frontend)
- Spring AOP usage to log details of API requests and responses for debugging and monitoring purposes