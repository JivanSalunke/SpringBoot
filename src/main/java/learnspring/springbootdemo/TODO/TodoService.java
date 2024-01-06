package learnspring.springbootdemo.TODO;

import learnspring.springbootdemo.TODO.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    List<Task> tasks= new ArrayList<>();
    private int newTaskId = 1;

    public List<Task> getAllTasks(){
        return tasks;
    }

    public Task getTaskById (Integer id) throws TaskNotFoundException {
        for(Task t:tasks){
            if(t.getId().equals(id)){
                return t;
            }
        }
        throw new TaskNotFoundException("Not Found");
    }
    public Task createTask(String name, String desc){
        Task task= new Task(newTaskId++,name,desc,"created");
        tasks.add(task);
        return  task;
    }
//    public Task updateTask(Task newTask){
//        Task task=getTaskById(newTask.getId());
//        task.setTaskName(newTask.getTaskName());
//        task.setTaskDescription(newTask.getTaskDescription());
//        task.setStatus(newTask.getStatus());
//
//        return  task;
//    }
    public boolean deleteTask(Integer id) throws TaskNotFoundException {
        try {
            Task task=getTaskById(id);
            tasks.remove(task);
            return true;
        } catch (TaskNotFoundException e) {
            throw e;
        }
    }
}
