package com.cuonvc.todoapp.controller;

import com.cuonvc.todoapp.entity.Todo;
import com.cuonvc.todoapp.payload.BaseResponse;
import com.cuonvc.todoapp.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cuonvc.todoapp.util.Constant.ApiVersion.API_V1;

@RestController
@RequestMapping(API_V1 + "/todo")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody @Valid Todo todo) {
        return todoService.update(id, todo);
    }

    @PutMapping("/action/{id}")
    public ResponseEntity<?> changeIsDoneStatus(@PathVariable("id") String id,
                                                @RequestParam("isDone") Boolean isDone) {
        return todoService.changeIsDone(id, isDone);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") String id) {
        return todoService.detail(id);
    }

    @GetMapping()
    public ResponseEntity<?> all(@RequestParam(name = "keyword") String keyword) {
        return todoService.list(keyword);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return todoService.delete(id);
    }
}
