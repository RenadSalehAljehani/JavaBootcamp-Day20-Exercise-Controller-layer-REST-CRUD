package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.ApiResponse.ApiResponse;
import com.example.tasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/taskTrackerSystem")
public class TaskController {

    // 1. Create a new list of tasks
    ArrayList<Task> tasks = new ArrayList<>();

    // 2. Create a new Task
    @PostMapping("/createTask")
    public ApiResponse createTask(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse("Task created.");
    }

    // 3. Display all tasks
    @GetMapping("/getTasks")
    public ArrayList<Task> getTaskTracker() {
        return tasks;
    }

    // 4. Update a task
    @PutMapping("/updateTask/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return new ApiResponse("Task updated.");
    }

    // 5. Delete a task
    @DeleteMapping("/deleteTask/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("Task deleted.");
    }

    // 6. Change all tasks status as done or not done
    @PutMapping("/changeStatus/{status}")
    public ApiResponse changeStatus(@PathVariable String status) {
        String message="";
        for(Task task : tasks) {
            // 1. If the parameter status was "Not Done"
            // Then all tasks with status not done, will be changed to done
            if(status.equalsIgnoreCase("Not Done")) {
                if (task.getStatus().equalsIgnoreCase(status)) {
                    task.setStatus("Done");
                    message = "All tasks status changed to Done.";
                } else if (task.getStatus().equalsIgnoreCase("Done")) {
                    message = "All tasks status already Done.";
                }
            }
            // 2. If the parameter status was "Done"
            // Then all tasks with status done, will be changed to not done
            else if(status.equalsIgnoreCase("Done")) {
                if (task.getStatus().equalsIgnoreCase(status)) {
                    task.setStatus("Not Done");
                    message = "All tasks status changed to Not Done.";
                } else if (task.getStatus().equalsIgnoreCase("Not Done")) {
                    message = "All tasks status already Not Done.";
                }
            }
        }
        return new ApiResponse(message);
    }

    // 7. Search for a task by given title
    @GetMapping("/search")
    public ApiResponse searchTask(@RequestParam String title) {
        String message = "";
        for(Task task : tasks) {
            if(task.getTitle().equals(title)) {
                message = "Task found.";
            }
            else {
                message = "Task not found.";
            }
        }
        return new ApiResponse(message);
    }
}