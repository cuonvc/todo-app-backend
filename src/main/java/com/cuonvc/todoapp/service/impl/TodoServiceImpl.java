package com.cuonvc.todoapp.service.impl;

import com.cuonvc.todoapp.entity.Todo;
import com.cuonvc.todoapp.exception.APIException;
import com.cuonvc.todoapp.payload.BaseResponse;
import com.cuonvc.todoapp.payload.ResponseFactory;
import com.cuonvc.todoapp.repository.TodoRepository;
import com.cuonvc.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.cuonvc.todoapp.util.Constant.ErrorMessage.TODO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final ResponseFactory responseFactory;
    private final TodoRepository repository;

    @Override
    public ResponseEntity<BaseResponse<Todo>> create(Todo todo) {
        LocalDateTime now = LocalDateTime.now();
        todo.setCreatedAt(now);
        todo.setUpdatedAt(now);
        return responseFactory.success(repository.save(todo));
    }

    @Override
    public ResponseEntity<BaseResponse<Todo>> update(String id, Todo request) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, TODO_NOT_FOUND));
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setUpdatedAt(LocalDateTime.now());
        return responseFactory.success(repository.save(todo));
    }

    @Override
    public ResponseEntity<BaseResponse<Todo>> changeIsDone(String id, Boolean isDone) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, TODO_NOT_FOUND));

        todo.setIsDone(isDone);
        return responseFactory.success(repository.save(todo));
    }

    @Override
    public ResponseEntity<BaseResponse<Todo>> detail(String id) {
        return responseFactory.success(repository.findById(id)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, TODO_NOT_FOUND)));
    }

    @Override
    public ResponseEntity<BaseResponse<List<Todo>>> list(String keyword) {
        return (!keyword.isBlank() && !keyword.isEmpty())
                ? responseFactory.success(repository.findAllByTitle(keyword.toLowerCase()))
                : responseFactory.success(repository.findAll());
    }

    @Override
    public ResponseEntity<BaseResponse<String>> delete(String id) {
        try {
            repository.deleteById(id);
            return responseFactory.success("Deleted");
        } catch (Exception e) {
            throw new APIException(HttpStatus.NOT_FOUND, TODO_NOT_FOUND);
        }
    }
}
