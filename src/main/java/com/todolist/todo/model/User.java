package com.todolist.todo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "modified_date", nullable = false)
    private java.sql.Timestamp modifiedDate;

    public User() {
        this.modifiedDate = java.sql.Timestamp.from(java.time.Instant.now());
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.setPassword(password);
        this.modifiedDate = java.sql.Timestamp.from(java.time.Instant.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
      
    	this.password = password;
    }

    public java.sql.Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(java.sql.Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
