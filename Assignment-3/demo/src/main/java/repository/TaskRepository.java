/*
Purpose of Repository: Responsible for data accessing and storage operations
Containing Methods for CRUD(Create, Read, Update, and Delete) operations for interacting with JSON file
 */

package repository;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import domain.Task;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks;
    private ObjectMapper objectMapper;// Serialization & Deserialization
    private File jsonFile;

    //constructor
    public TaskRepository(String jsonFilePath) {
        objectMapper = new ObjectMapper();
        jsonFile = new File(jsonFilePath);
        tasks = loadTasksFromFile();
    }

    //load tasks from the file
    private List<Task> loadTasksFromFile() {
        try {
            if (!jsonFile.exists()) {
                return new ArrayList<>(); // Return an empty list if the file doesn't exist yet.
            }
            return objectMapper.readValue(jsonFile, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace(); // Log the error
            throw new RuntimeException("Error loading the tasks from the file", e); // Throw a runtime exception with the error message and the original exception.
        }
    }

    //save the tasks to the file
    private void saveTasksToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, tasks);
        } catch (IOException e) {
            e.printStackTrace(); // Log the error
            throw new RuntimeException("Error saving tasks to the file", e); // Throw a runtime exception with the error message and the original exception.
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    //retreive a task by the task id
    public Task getTaskById(Long taskId) {
        for(Task x: tasks){
            if(x.getId().equals(taskId)){
                return x;
            }
        }
        return null;
    }

    //create a new task
    public Task addTask(Task task) {
        tasks.add(task); // Use the add method to add a task to the list.
        saveTasksToFile(); // Save the updated task list to the JSON file.
        return task; // Return the added task.
    }

    //update the tasks
    public Task updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(task.getId())) {
                tasks.set(i, task); // Update the task in the list.
                saveTasksToFile(); // Save the updated task list to the JSON file.
                return task;
            }
        }
        return null; // Return null if the task with the specified ID is not found.
    }
    
    //delete a task
    public void deleteTask(Long taskId) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId().equals(taskId)) {
                iterator.remove(); // remove the task if iterator finds a task with the same id as the input.
            }
        }
        saveTasksToFile(); // Save the updated task list to the JSON file.
    }
}
