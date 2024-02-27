package com.cuonvc.todoapp.service;

import com.cuonvc.todoapp.entity.Todo;
import com.cuonvc.todoapp.payload.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoService {
    ResponseEntity<BaseResponse<Todo>> create(Todo todo);
    ResponseEntity<BaseResponse<Todo>> update(String id, Todo todo);
    ResponseEntity<BaseResponse<Todo>> detail(String id);
    ResponseEntity<BaseResponse<List<Todo>>> list();
    ResponseEntity<BaseResponse<String>> delete(String id);
}
