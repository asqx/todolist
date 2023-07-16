package com.javarush.todolist.rantsev.controller;

import com.javarush.todolist.rantsev.domain.Status;
import com.javarush.todolist.rantsev.dto.TaskDTO;
import com.javarush.todolist.rantsev.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@AllArgsConstructor

@Slf4j
@Controller
@RequestMapping({"/","/tasks"})
public class TaskController {

    public static final int PAGE_NUMBER_DEFAULT = 0;
    public static final int PAGE_SIZE_DEFAULT = 10;

    private final TaskService taskService;

    @GetMapping
    public String index(@RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer size,
                        @ModelAttribute("task") TaskDTO taskDTO,
                        Model model) {
        page = Objects.isNull(page) ? PAGE_NUMBER_DEFAULT : page;
        size = Objects.isNull(size) ? PAGE_SIZE_DEFAULT : size;

        Page<TaskDTO> taskPage = taskService.findAll(PageRequest.of(page, size));
        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("pages", taskPage.getTotalPages());
        model.addAttribute("statusElements", Status.values());
        return "task/index";
    }

    @PostMapping
    public String createTask(@ModelAttribute("task") TaskDTO taskDTO) {
        taskService.save(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable Integer id,
                           Model model) {
        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statusElements", Status.values());
        return "task/show";
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable Integer id,
                             @ModelAttribute("task") TaskDTO taskDTO) {
        taskDTO.setId(id);
        taskService.update(taskDTO);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Integer id,
                             @ModelAttribute("task") TaskDTO taskDTO) {
        taskDTO.setId(id);
        taskService.delete(taskDTO);
        return "redirect:/tasks";
    }
}

