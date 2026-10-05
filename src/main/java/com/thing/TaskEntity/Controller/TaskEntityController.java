package com.thing.TaskEntity.Controller;

import com.thing.TaskEntity.Service.TaskService;
import com.thing.TaskEntity.Tasks.Task;
import com.thing.TaskEntity.dto.TaskRequestDTO;
import com.thing.TaskEntity.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskEntityController {

    private final TaskService taskService;

    public TaskEntityController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO requestDTO){
        TaskResponseDTO responseDTO = taskService.createTask(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable Long id){
        TaskResponseDTO responseDTO = taskService.getTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> geAllTask(){
        List<TaskResponseDTO> receivedALlTask = taskService.getAllTask();
        return ResponseEntity.status(HttpStatus.OK).body(receivedALlTask);
    }

    @GetMapping("/showCompletedTask")
    public ResponseEntity<List<TaskResponseDTO>> getCompletedTask(){
        List<TaskResponseDTO> receivedCompletedTask = taskService.showCompletedTask();
        return ResponseEntity.status(HttpStatus.OK).body(receivedCompletedTask);
    }

    @GetMapping("/showPendingTask")
    public ResponseEntity<List<TaskResponseDTO>> getPendingTask(){
        List<TaskResponseDTO> receivedPendingTask = taskService.showPendingTask();
        return ResponseEntity.status(HttpStatus.OK).body(receivedPendingTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> modifyTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO requestDTO){
        TaskResponseDTO responseDTO = taskService.modifyTask(id,requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> markTaskDone(@PathVariable Long id){
        TaskResponseDTO responseDTO = taskService.markTaskDone(id);
        return ResponseEntity.ok(responseDTO);
    }
}
