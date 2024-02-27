package com.cuonvc.todoapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

import static com.cuonvc.todoapp.util.Constant.ValidFieldMessage.TITLE_CANNOT_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GenericGenerator(name = "custom_id", strategy = "com.cuonvc.todoapp.util.CustomIdGenerator")
    @GeneratedValue(generator = "custom_id")
    private String id;

    @Column(name = "title")
    @NotNull(message = TITLE_CANNOT_EMPTY)
    @NotEmpty(message = TITLE_CANNOT_EMPTY)
    @NotBlank(message = TITLE_CANNOT_EMPTY)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_done")
    private Boolean isDone = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
