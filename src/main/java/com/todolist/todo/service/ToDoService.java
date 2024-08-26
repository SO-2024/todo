package com.todolist.todo.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.todo.model.ToDo;
import com.todolist.todo.model.User;
import com.todolist.todo.repository.TaskRepository;
import com.todolist.todo.util.JWTTokenUtil;

/**
 * Service class for managing ToDo tasks. This service provides methods to
 * retrieve, save, update, and delete tasks.
 */
@Service
public class ToDoService {

	@Autowired
	private JWTTokenUtil jwttu;
	private final TaskRepository taskRepository;
	@Autowired
	private UserService userService;

	/**
	 * Constructs a new ToDoService with the specified TaskRepository.
	 *
	 * @param taskRepository the repository used for task persistence
	 */
	@Autowired
	public ToDoService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	/**
	 * Retrieves all ToDo tasks from the repository.
	 *
	 * @return a list of all ToDo tasks
	 */
	public List<ToDo> getAllToDoList(String token) {

		String email = jwttu.getEmailFromToken(token);
		// Get User by Email
		Optional<User> user = userService.findUserByEmail(email);
		// Get Records by UserId and SearchKey
		if (user.isPresent()) {
			return taskRepository.getAllToDoListByUserId(user.get().getId());
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Retrieves a ToDo task by its ID.
	 *
	 * @param id the ID of the task to retrieve
	 * @return an Optional containing the task if found, or an empty Optional if not
	 *         found
	 */
	public Optional<ToDo> getToDoById(Long id) {
		return taskRepository.findById(id);
	}

	/**
	 * Saves a new ToDo task to the repository.
	 *
	 * @param todo the task to save
	 * @return the saved ToDo task
	 */
	public ToDo saveToDo(ToDo todo, String token) {
		String email = jwttu.getEmailFromToken(token);
		Optional<User> user = userService.findUserByEmail(email);
		todo.setUserId(user.get().getId());
		return taskRepository.save(todo);
	}

	/**
	 * Updates an existing ToDo task by its ID.
	 *
	 * @param id          the ID of the task to update
	 * @param updatedToDo the task with updated information
	 * @return the updated ToDo task
	 * @throws RuntimeException if no task is found with the specified ID
	 */
	public ToDo updateToDoById(Long id, ToDo updatedToDo) {
		return taskRepository.findById(id).map(todo -> {
			todo.setTitle(updatedToDo.getTitle());
			todo.setDescription(updatedToDo.getDescription());
			todo.setDueDate(updatedToDo.getDueDate());
			todo.setStatus(updatedToDo.getStatus()); // Updated to use status
			todo.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			return taskRepository.save(todo);
		}).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
	}

	/**
	 * Retrieves a list of ToDo tasks based on a search key.
	 *
	 * @param token     the JWT token for the current user session
	 * @param searchKey the key to search tasks by their title
	 * @return a list of ToDo tasks that match the search key
	 */

	public List<ToDo> getRecordsBySearchKey(String token, String searchKey) {
		// Extract email from JWT token
		String email = jwttu.getEmailFromToken(token);
		// Get User by Email
		Optional<User> user = userService.findUserByEmail(email);
		// Get Records by UserId and SearchKey
		return taskRepository.findByUserIdAndTitleStartingWith(user.get().getId(), searchKey);
	}

	/**
	 * Deletes a ToDo task by its ID.
	 *
	 * @param id the ID of the task to delete
	 * @throws RuntimeException if no task is found with the specified ID
	 */
	public void deleteToDoById(Long id) {
		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
		} else {
			throw new RuntimeException("Task not found with id " + id);
		}
	}
}
