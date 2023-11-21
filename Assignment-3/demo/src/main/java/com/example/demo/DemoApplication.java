/*
Purpose: Initialize Spring Boot Application
*/
package com.example.demo;
import repository.TaskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        //String jsonFilePath = "src/main/resources/task.json";
        //TaskRepository taskRepository = new TaskRepository(jsonFilePath);
        SpringApplication.run(DemoApplication.class, args);
    }
}
