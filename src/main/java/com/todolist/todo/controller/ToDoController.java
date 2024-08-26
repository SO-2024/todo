package com.todolist.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todolist.todo.model.InputErrors;
import com.todolist.todo.model.ToDo;
import com.todolist.todo.service.ToDoService;
import com.todolist.todo.util.JWTTokenUtil;

import jakarta.servlet.http.HttpServletRequest;


/**
 * Controller for handling ToDo operations.
 * This class provides endpoints for CRUD operations on ToDo items.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/todoapi")
public class ToDoController {

    @Autowired
    private JWTTokenUtil jwttu;
    
    private final ToDoService service;

    @Autowired
    public ToDoController(ToDoService service) {
        this.service = service;
    }

    /**
     * Retrieves all ToDo items.
     * 
     * @return a list of all ToDo items.
     */
    @GetMapping(value = "/getAllToDoList", produces = "application/json;charset=UTF-8")
    public Object getAllToDoList(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (jwttu.validateToken(token)) {
            try {
                List<ToDo> todos = service.getAllToDoList(token);
                return ResponseEntity.status(200).body(todos);
            } catch (Exception ex) {
                return ResponseEntity.status(500).body(new InputErrors("service", "error occurred").getAllErrors());
            }
        }
        return ResponseEntity.status(401).body(new InputErrors("auth", "token expired").getAllErrors());
    }

    /**
     * Retrieves a specific ToDo item by its ID.
     * 
     * @param id the ID of the ToDo item.
     * @return the ToDo item if found, otherwise a 404 Not Found response.
     */
    @GetMapping(value = "/getToDoById/{id}", produces = "application/json;charset=UTF-8")
    public Object getToDoById(@PathVariable Long id, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (jwttu.validateToken(token)) {
            try {
                Optional<ToDo> todo = service.getToDoById(id);
                if (todo.isPresent()) {
                    return ResponseEntity.status(200).body(todo.get());
                } else {
                    return ResponseEntity.status(404).body(new InputErrors("service", "ToDo not found").getAllErrors());
                }
            } catch (Exception ex) {
                return ResponseEntity.status(500).body(new InputErrors("service", "error occurred").getAllErrors());
            }
        }
        return ResponseEntity.status(401).body(new InputErrors("auth", "token expired").getAllErrors());
    }

    /**
     * Creates a new ToDo item.
     * 
     * @param todo the ToDo item to be created.
     * @return the created ToDo item.
     */
    @PostMapping(value = "/saveToDo", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public Object saveToDo(@RequestBody ToDo todo, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (jwttu.validateToken(token)) {
            HashMap<String, String> res = new HashMap<>();
            try {
                ToDo newToDo = service.saveToDo(todo, token);
                res.put("operation", "ToDo saved successfully");
                res.put("todoId", newToDo.getId().toString());
                return ResponseEntity.status(200).body(res);
            } catch (Exception ex) {
                return ResponseEntity.status(500).body(new InputErrors("service", "error occurred").getAllErrors());
            }
        }
        return ResponseEntity.status(401).body(new InputErrors("auth", "token expired").getAllErrors());
    }


    /**
     * Updates an existing ToDo item by its ID.
     * 
     * @param id the ID of the ToDo item to be updated.
     * @param updatedToDo the updated ToDo item.
     * @return the updated ToDo item if successful, otherwise a 404 Not Found response.
     */
    @PutMapping(value = "/updateToDo/{id}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public Object updateToDoById(@PathVariable Long id, @RequestBody ToDo updatedToDo, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (jwttu.validateToken(token)) {
            try {
                ToDo updated = service.updateToDoById(id, updatedToDo);
                return ResponseEntity.status(200).body(updated);
            } catch (Exception ex) {
                return ResponseEntity.status(500).body(new InputErrors("service", "error occurred").getAllErrors());
            }
        }
        return ResponseEntity.status(401).body(new InputErrors("auth", "token expired").getAllErrors());
    }
    
    @GetMapping("/search") 
    public List<ToDo> searchRecords( @RequestHeader("Authorization") String authorizationHeader, @RequestParam("key") String searchKey) { 
    	// Extract token from Authorization header 
    	//String token = authorizationHeader; // Remove "Bearer " prefix 
    	
    	return service.getRecordsBySearchKey(authorizationHeader, searchKey); 
    }

    /**
     * Deletes a ToDo item by its ID.
     * 
     * @param id the ID of the ToDo item to be deleted.
     * @return a 204 No Content response if successful, otherwise a 404 Not Found response.
     */
    @DeleteMapping(value = "/deleteToDo/{id}", produces = "application/json;charset=UTF-8")
    public Object deleteToDoById(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id) {
        // Extract token from Authorization header, if needed
        // String token = authorizationHeader; // remove "Bearer " prefix if necessary
        
        if (jwttu.validateToken(authorizationHeader)) {
            HashMap<String, String> res = new HashMap<>();
            try {
                service.deleteToDoById(id);
                res.put("operation", "Your request added successfully!!");
                return ResponseEntity.status(200).body(res);
            } catch (Exception ex) {
                return ResponseEntity.status(500).body(new InputErrors("service", "error occurred").getAllErrors());
            }
        }
        return ResponseEntity.status(401).body(new InputErrors("auth", "token expired").getAllErrors());
    }

}
