package com.todolist.todo.repository;
import com.todolist.todo.model.ToDo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByUserIdAndTitleStartingWith(Long userId, String searchKey);
    List<ToDo> getAllToDoListByUserId(Long userId);
}


