package com.thing.TaskEntity.mapper;

import com.thing.TaskEntity.Tasks.Task;
import com.thing.TaskEntity.dto.TaskRequestDTO;
import com.thing.TaskEntity.dto.TaskResponseDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mapper {
    public static Task maptoEntity(TaskRequestDTO taskRequestDTO){
        Task entityTask = new Task();
        entityTask.setTaskName(taskRequestDTO.getTaskName());
        entityTask.setDescription(taskRequestDTO.getDescription());
        entityTask.setDate(taskRequestDTO.getDate());
        entityTask.setCompleted(false);
        entityTask.setCreatedAt(LocalDateTime.now());
        entityTask.setUpdatedAt(LocalDateTime.now());

        return entityTask;
    }

    public static TaskResponseDTO mapToDTO(Task task){
        TaskResponseDTO responseDTO = new TaskResponseDTO();
        responseDTO.setTaskName(task.getTaskName());
        responseDTO.setDescription(task.getDescription());
        responseDTO.setDate(task.getDate());
        responseDTO.setCompleted(task.getCompleted());
        responseDTO.setUpdatedAt(task.getUpdatedAt());

        return responseDTO;
    }

    public static List<TaskResponseDTO> mapToListDTO(List<Task> tasks){
        if(tasks == null){
            return Collections.emptyList();
        }
        return tasks.stream()
                .map(Mapper::mapToDTO)
                .toList();
    }
}
