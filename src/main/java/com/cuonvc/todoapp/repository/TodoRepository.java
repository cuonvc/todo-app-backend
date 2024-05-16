package com.cuonvc.todoapp.repository;

import com.cuonvc.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("SELECT t FROM Todo t " +
            "WHERE LOWER(t.title) LIKE %:keyword%")
    List<Todo> findAllByTitle(String keyword);
}
