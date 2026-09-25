package com.thing.TaskEntity.Service;

import com.thing.TaskEntity.Repository.TaskRepository;
import com.thing.TaskEntity.Tasks.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;

    }

    public Task createTask(Task task){
        task.setCompleted(false);
        Task taskResp = taskRepository.save(task);
        return taskResp;
    }

    public Task getTask(Long id){
        Optional<Task> taskResp = taskRepository.findById(id);

        if(taskResp.isPresent()){
            return taskResp.get();
        }
        return null;
    }

    public List<Task> getAllTask(){
        List<Task> taskResp = taskRepository.findAll();
        return taskResp;
    }

    public boolean modifyTask(Long id,Task task){
        Optional<Task> receivedVal = taskRepository.findById(id);

        if(receivedVal.isEmpty()){
            return false;
        }
        Task modifiedTask = receivedVal.get();
        modifiedTask.setTaskName(task.getTaskName());
        modifiedTask.setDate(task.getDate());
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
