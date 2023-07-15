package com.javarush.todolist.rantsev.mapper;

import com.javarush.todolist.rantsev.domain.Task;
import com.javarush.todolist.rantsev.dto.TaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper{
    TaskDTO mapToTaskDTO(Task task);
    Task mapToTask(TaskDTO taskDTO);
}
