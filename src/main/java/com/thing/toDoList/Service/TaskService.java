package com.thing.toDoList.Service;

import com.thing.toDoList.Repository.TaskRepository;
import com.thing.toDoList.Tasks.Task;
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

}
