package com.example.taskclass_ex.Controller;

import com.example.taskclass_ex.API.ApiResponse;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    final private ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTodos(@RequestBody Task task){
        tasks.add(task);
        // return task;
        return new ApiResponse(" Task Added" );
    }


    @PutMapping("/update/{index}") // Update
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task updatetask) {
        tasks.set(index, updatetask);
        return new ApiResponse(" Updated Succseeful");
    }

    @DeleteMapping("/delet/{index}")
    public ApiResponse deleteTask(int index) {
        tasks.remove(index);
        return new ApiResponse(" deleted Succseeful");
    }

    @PutMapping("/change/status/{id}")
    public ApiResponse updateTaskStatus(@PathVariable int id, @RequestParam String status) {
        if (status.equals("not done")){
            status = "done";
        }
        return new ApiResponse(" Sucsseful ");
    }
//        for (Task task : tasks) {
//            if (task.getId() == id) {
//                if (status.equalsIgnoreCase("not done")) {
//                    task.setStatus("done");
//                } else {
//                    task.setStatus(status);
//                }
//                return new Api("Task status updated to: " + task.getStatus());
//            }
//        }
//        return new Api("Task not found");




    @GetMapping("/serch/title")
    public String getSerachTitle(String title) {
        for (Task task : tasks) {
            if (task.equals(title)) {
                return title;
            }
        }
        return title;
    }




}

