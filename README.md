# todo
 # ToDo Application

## Project Description
This is a full-stack ToDo application built with RESTful APIs using the Spring Boot framework for the backend and MySQL for the database. The frontend uses Angular. The application allows users to manage their tasks, including creating, updating, and deleting tasks. Users can also sign up, log in, and manage their accounts.

## Features
- Signup and login for the user.
- User authentication with JWT.
- CRUD operations for ToDo tasks.
- REST API for interacting with tasks and user data.
- Search functionality for filtering tasks by title.

## Installation

### Prerequisites
- Node.js 20 or higher
- Angular CLI 18
- Java 11 or higher
- Maven
- MySQL
- Git

### Steps

#### Clone the repository:
git clone https://github.com/SO-2024/to-do
cd to-do
git clone https://github.com/SO-2024/todo
cd todo
Usage
After setting up the project, the application will be running on http://localhost:4200/.
Use tools like Postman or your frontend to interact with the APIs.
API Endpoints
Authentication
POST /usertodo/signup: Sign up a new user.
POST /usertodo/login: Log in a user and get a JWT.
ToDo Tasks
GET /todoapi/getAllToDoList: Retrieve all ToDo items.
GET /todoapi/getToDoById/{id}: Retrieve a ToDo item by its ID.
POST /todoapi/saveToDo: Create a new ToDo item.
PUT /todoapi/updateToDoById/{id}: Update an existing ToDo item.
DELETE /todoapi/deleteToDoById/{id}: Delete a ToDo item by its ID.
Search
GET /todoapi/search: Search ToDo items by title.
Database Schema
ToDo Table (tasks):
task_id: Unique identifier for the task.
title: Title of the task.
description: Description of the task.
due_date: Due date of the task.
status: Status of the task.
created_date: Timestamp of when the task was created.
modified_date: Timestamp of when the task was last modified.
user_id: ID of the user who created the task.
User Table (users):
user_id: Unique identifier for the user.
name: Name of the user.
email: Email address of the user.
password: Password of the user.
modified_date: Timestamp of when the user data was last modified.
