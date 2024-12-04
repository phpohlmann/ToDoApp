package com.todoapp.myapp.repository;

import com.todoapp.myapp.model.ToDoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends CrudRepository<ToDoItem, Integer> {
}
