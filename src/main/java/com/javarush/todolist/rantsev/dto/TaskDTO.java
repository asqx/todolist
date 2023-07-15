package com.javarush.todolist.rantsev.dto;

import com.javarush.todolist.rantsev.domain.Status;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TaskDTO {

    private Integer id;
    private String description;
    private Status status;

}
