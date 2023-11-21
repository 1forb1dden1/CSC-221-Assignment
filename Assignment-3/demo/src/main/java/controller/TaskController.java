/*
Purpose of Controller: Handle HTTP Requests to act as an interface between the application and client
Achieves this by:
- defining request mapping endpoints and handle incoming requests by the client
*/

package controller;

import domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) {
        Task task = taskRepository.getTaskById(taskId);
        if (task != null) {
            return task;
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskRepository.addTask(task);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        task.setId(taskId);
        Task updatedTask = taskRepository.updateTask(task);
        if (updatedTask != null) {
            return updatedTask;
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteTask(taskId);
    }
}
