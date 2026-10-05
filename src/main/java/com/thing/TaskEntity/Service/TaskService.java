package com.thing.TaskEntity.Service;

import com.thing.TaskEntity.Repository.TaskRepository;
import com.thing.TaskEntity.Tasks.Task;
import com.thing.TaskEntity.dto.TaskRequestDTO;
import com.thing.TaskEntity.dto.TaskResponseDTO;
import com.thing.TaskEntity.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;

    }

    public TaskResponseDTO createTask(TaskRequestDTO tasksRequest){
        return Mapper.mapToDTO(taskRepository
                .save(Mapper.maptoEntity(tasksRequest)));
    }

    public TaskResponseDTO getTask(Long id){
        Optional<Task> taskResp = taskRepository.findById(id);

        if(taskResp.isPresent()){
            return Mapper.mapToDTO(taskResp.get());
        }
        return null;
    }

    public List<TaskResponseDTO> getAllTask(){
        List<Task> taskResp = taskRepository.findAll();
        return Mapper.mapToListDTO(taskResp);
    }

    public List<TaskResponseDTO> showCompletedTask(){
        List<Task> taskResp = taskRepository.findByIsCompletedTrue();
        return Mapper.mapToListDTO(taskResp);
    }

    public List<TaskResponseDTO> showPendingTask(){
        List<Task> taskResp = taskRepository.findByIsCompletedFalse();
        return Mapper.mapToListDTO(taskResp);
    }

    public boolean modifyTask(Long id,TaskRequestDTO requestDTO){
        Optional<Task> receivedVal = taskRepository.findById(id);

        if(receivedVal.isEmpty()){
            return false;
        }
        Task modifiedTask = receivedVal.get();
        modifiedTask.setTaskName(requestDTO.getTaskName());
        modifiedTask.setDate(requestDTO.getDate());
        taskRepository.save(modifiedTask);
        return true;
    }

    public boolean deleteTask(Long id){
        boolean isExist = taskRepository.existsById(id);

        if(!isExist){
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }

    public boolean markTaskDone(Long id){
        Optional<Task> reveivedVal = taskRepository.findById(id);
        if(reveivedVal.isEmpty()){
            return false;
        }
        Task changeTaskCompletion = reveivedVal.get();
        changeTaskCompletion.setCompleted(true);
        taskRepository.save(changeTaskCompletion);
        return true;
    }

}
