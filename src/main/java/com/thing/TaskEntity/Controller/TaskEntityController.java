package com.thing.TaskEntity.Controller;

import com.thing.TaskEntity.Service.TaskService;
import com.thing.TaskEntity.Tasks.Task;
import com.thing.TaskEntity.dto.TaskRequestDTO;
import com.thing.TaskEntity.dto.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskEntityController {

    private TaskService taskService;

    public TaskEntityController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO requestDTO){
        TaskResponseDTO responseDTO = taskService.createTask(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id){
        Task createdTask = taskService.getTask(id);

        if(createdTask == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> geAlltTask(){
        List<Task> receivedALlTask = taskService.getAllTask();

        if(receivedALlTask.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(receivedALlTask);
    }

    @GetMapping("/showCompletedTask")
    public ResponseEntity<List<Task>> getCompletedTask(){
        List<Task> receivedCompletedTask = taskService.showCompletedTask();

        if(receivedCompletedTask.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(receivedCompletedTask);
    }

    @GetMapping("/showPendingTask")
    public ResponseEntity<List<Task>> getPendingTask(){
        List<Task> receivedPendingTask = taskService.showPendingTask();

        if(receivedPendingTask.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(receivedPendingTask);
    }

    @PutMapping
    public ResponseEntity<String> modifyTask(@RequestParam Long id, @RequestBody Task task){
        boolean modifiedTask = taskService.modifyTask(id,task);
        if(modifiedTask){
            return ResponseEntity.ok("Modified");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTask(@RequestParam Long id){
        boolean deletedTask = taskService.deleteTask(id);
        if(deletedTask){
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<String> markTaskDone(@RequestParam Long id){
        boolean valResp = taskService.markTaskDone(id);
        System.out.println(valResp);
        if(valResp){
            return ResponseEntity.ok("Marked done");
        }
        return ResponseEntity.notFound().build();
    }
}
