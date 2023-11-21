/*
Purpose: Acts as a bridge between the controller and application
 */

package service;

import domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.getTaskById(taskId);
    }

    public Task addTask(Task task) {
        return taskRepository.addTask(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.updateTask(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteTask(taskId);
    }
}
