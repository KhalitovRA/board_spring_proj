package com.digitaldepart.board.controllers;

import com.digitaldepart.board.models.Board;
import com.digitaldepart.board.models.Task;
import com.digitaldepart.board.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("")
public class TasksController {

    private final TaskRepository taskRepository;

    @Autowired
    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks/add")
    public String taskMain(Model model) {
        return "task-add";
    }

    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String author, @RequestParam String executor, @RequestParam LocalDateTime release_start, @RequestParam LocalDateTime release_end, @RequestParam Board board_id) {
        Task task = new Task(author, executor, release_start, release_end, board_id);
        taskRepository.save(task);
        return "task-add";
    }

    @GetMapping("/task/{id}/edit")
    public String taskDetails(@PathVariable(value = "id") long task_id, Model model) {
        if (!taskRepository.existsById(task_id)) {
            return "redirect:/main-page";
        }
        Optional<Task> task = taskRepository.findById(task_id);
        ArrayList<Task> res = new ArrayList<>();
        task.ifPresent(res::add);
        model.addAttribute("task", res);
        return "task-edit";
    }

    @PostMapping("/task/{id}/edit")
    public String projTaskUpdate(@PathVariable(value = "id") long task_id, @RequestParam String author, @RequestParam String executor, @RequestParam LocalDateTime release_start, @RequestParam LocalDateTime release_end, @RequestParam Board board_id) {
        Task task = taskRepository.findById(task_id).orElseThrow();
        task.setAuthor(author);
        task.setExecutor(executor);
        task.setRelease_start(release_start);
        task.setRelease_end(release_end);
        taskRepository.save(task);
        return "redirect:/projects";
//    TODO: продумать редирект
    }

    @PostMapping("/task/{id}/delete")
    public String projTaskDelete(@PathVariable(value = "id") long task_id) {
        Task task = taskRepository.findById(task_id).orElseThrow();
        taskRepository.delete(task);
        return "redirect:/projects";
    }
}
