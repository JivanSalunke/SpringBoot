package learnspring.springbootdemo.TODO.model;

import learnspring.springbootdemo.TODO.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public class TaskBuilder {
    public static List<TaskDTO> convertTaskListToDTOList(List<Task> tasks) {
        List<TaskDTO> taskDTOS=new ArrayList<>();
        for(Task task:tasks){
            taskDTOS.add(convertTaskToDTO(task));
        }
        return taskDTOS;
    }

    public static TaskDTO convertTaskToDTO(Task task) {
        TaskDTO taskDTO= new TaskDTO(task.getId(),task.getTaskName(), task.getTaskDescription(),task.getStatus());
        return taskDTO;
    }
}
