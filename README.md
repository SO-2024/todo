ToDo Application

Project Description
This is a full-stack ToDo application built with RESTful APIs using Spring Boot framework for the backend and MySQL for the database, and the frontend uses Angular. The application allows users to manage their tasks, including creating, updating, and deleting tasks. Users can also sign up, log in, and manage their accounts.
Features
•	Signup and login for the user.
•	User authentication with JWT.
•	CRUD operations for ToDo tasks.
•	REST API for interacting with tasks and user data.
•	Search functionality for filtering tasks by title.

Installation
Prerequisites
•	Node js 20 or higher
•	Angular cli 18
•	Java 11 or higher
•	Maven
•	MySQL
•	Git


Steps
Clone the repository:
Clone the repo: https://github.com/SO-2024/to-do
cd to-do
Clone the backend repository:
git clone https://github.com/SO-2024/todo
cd todo


Usage
•	After setting up the project, the application will be running on http://localhost:4200/.
•	Use tools like Postman or your frontend to interact with the APIs.


API Endpoints

Authentication
•	POST /usertodo/signup: Sign up a new user.
•	POST /usertodo/login: Log in a user and get a JWT.

ToDo Tasks
•	GET /todoapi/getAllToDoList: Retrieve all ToDo items.
•	GET /todoapi/getToDoById/{id}: Retrieve a ToDo item by its ID.
•	POST /todoapi/saveToDo: Create a new ToDo item.
•	PUT /todoapi/updateToDoById/{id}: Update an existing ToDo item.
•	DELETE /todoapi/deleteToDoById/{id}: Delete a ToDo item by its ID.
Search
•	GET /todoapi/search: Search ToDo items by title.

Database Schema
•	ToDo Table (tasks):
o	task_id: Unique identifier for the task.
o	title: Title of the task.
o	description: Description of the task.
o	due_date: Due date of the task.
o	status: Status of the task.
o	created_date: Timestamp of when the task was created.
o	modified_date: Timestamp of when the task was last modified.
o	user_id: ID of the user who created the task.

•	User Table (users):
o	user_id: Unique identifier for the user.
o	name: Name of the user.
o	email: Email address of the user.
o	password: Password of the user.
o	modified_date: Timestamp of when the user data was last modified.


ERD (Entity Relationship Diagram):
![erd](https://github.com/user-attachments/assets/bd0bfcf5-f47b-479a-9a0d-6b3d7de0b3aa)


