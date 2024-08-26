package com.todolist.todo.model;

import java.sql.Timestamp;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a To-Do task entity in the database.
 */
@Entity 
@Table(name="tasks")


public class ToDo {
	
	/**
	 * Unique identifier for the To-Do task.
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="task_id")
	private Long id;

	/**
	 * Title of the To-Do task.
	 */
	@Column
	private String title;

	/**
	 * Description of the To-Do task.
	 */
	@Column
	private String description;

	/**
	 * Due date of the To-Do task.
	 * Stored as a Date.
	 */
	@Column(name="due_date")
	private Date dueDate;

	/**
	 * Status of the To-Do task.
	 * This replaces the old isCompleted boolean.
	 */
	@Column(name="status")
	private String status;

	/**
	 * Timestamp of when the task was created.
	 */
	@Column(name="created_date")
	private Timestamp createdDate;
	
	/**
	 * Timestamp of when the task was last modified.
	 */
	@Column(name="modified_date")
	private Timestamp modifiedDate;
	
	@Column(name="user_id")
	private Long userId;

	/**
	 * Default constructor.
	 */
	public ToDo() {
		// Default constructor
	}
	
	// Getters and Setters for each field
	// ...

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getUserId() {
	    return userId;
	}

	public void setUserId(Long userId) {
	    this.userId = userId;
	}
	
	
}
