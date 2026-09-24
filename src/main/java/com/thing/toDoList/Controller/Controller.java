package com.thing.toDoList.Controller;

import com.thing.toDoList.Service.TaskService;
import com.thing.toDoList.Tasks.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class Controller {

    private TaskService taskService;

    public Controller(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task receivedAllTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(receivedAllTask);
    }

    @GetMapping("/get")
    public ResponseEntity<Task> getTask(@RequestParam Long id){
        Task createdTask = taskService.getTask(id);

        if(createdTask == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdTask);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getTask(){
        List<Task> receivedALlTask = taskService.getAllTask();

        if(receivedALlTask.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(receivedALlTask);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modifyTask(@RequestParam Long id, @RequestBody Task task){
        boolean modifiedTask = taskService.modifyTask(id,task);
        if(modifiedTask){
            return ResponseEntity.ok("Modified");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam Long id){
        boolean deletedTask = taskService.deleteTask(id);
        if(deletedTask){
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
