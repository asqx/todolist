package com.javarush.todolist.rantsev.service;

import com.javarush.todolist.rantsev.dao.TaskDAO;
import com.javarush.todolist.rantsev.domain.Task;
import com.javarush.todolist.rantsev.dto.TaskDTO;
import com.javarush.todolist.rantsev.exception.TaskNotFoundException;
import com.javarush.todolist.rantsev.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;
    private final TaskMapper taskMapper;

    @Override
    public TaskDTO findById(Integer id) {
        return taskDAO.findById(id)
                .map(taskMapper::mapToTaskDTO)
                .orElseThrow(() -> new TaskNotFoundException("Not found task with ID - " + id));
    }

    @Override
    public Page<TaskDTO> findAll(PageRequest pageRequest) {
        return taskDAO.findAll(pageRequest)
                .map(taskMapper::mapToTaskDTO);
    }

    @Transactional
    @Override
    public void save(TaskDTO taskDTO) {
        Task task = taskMapper.mapToTask(taskDTO);
        taskDAO.save(task);
    }

    @Transactional
    @Override
    public void update(TaskDTO taskDTO) {
        Task task = taskMapper.mapToTask(taskDTO);
        taskDAO.save(task);
    }

    @Transactional
    @Override
    public void delete(TaskDTO taskDTO) {
        Task task = taskMapper.mapToTask(taskDTO);
        taskDAO.delete(task);
    }
}
