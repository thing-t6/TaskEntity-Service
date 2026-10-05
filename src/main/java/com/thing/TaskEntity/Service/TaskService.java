package com.thing.TaskEntity.Service;

import com.thing.TaskEntity.Repository.TaskRepository;
import com.thing.TaskEntity.Tasks.Task;
import com.thing.TaskEntity.dto.TaskRequestDTO;
import com.thing.TaskEntity.dto.TaskResponseDTO;
import com.thing.TaskEntity.exception.ResourceNotFoundException;
import com.thing.TaskEntity.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;

    }

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO tasksRequest){
        return Mapper.mapToDTO(taskRepository
                .save(Mapper.maptoEntity(tasksRequest)));
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO getTask(Long id){
        Task taskResp = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with this id " + id + " not found"));

        return Mapper.mapToDTO(taskResp);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAllTask(){
        List<Task> taskResp = taskRepository.findAll();
        return Mapper.mapToListDTO(taskResp);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> showCompletedTask(){
        List<Task> taskResp = taskRepository.findByIsCompletedTrue();
        return Mapper.mapToListDTO(taskResp);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> showPendingTask(){
        List<Task> taskResp = taskRepository.findByIsCompletedFalse();
        return Mapper.mapToListDTO(taskResp);
    }

    @Transactional
    public TaskResponseDTO modifyTask(Long id,TaskRequestDTO requestDTO){
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with this id " + id + " not found")
        );

        task.setTaskName(requestDTO.getTaskName());
        task.setDate(requestDTO.getDate());
        return Mapper.mapToDTO(taskRepository.save(task));
    }

    @Transactional
    public void deleteTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with this id " + id + " not found")
        );

        taskRepository.deleteById(id);
    }

    @Transactional
    public TaskResponseDTO markTaskDone(Long id){
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with this id " + id + " not found")
        );

        task.setCompleted(true);
        return Mapper.mapToDTO(taskRepository.save(task));
    }

}
