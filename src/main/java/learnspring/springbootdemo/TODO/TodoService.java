package learnspring.springbootdemo.TODO;

import learnspring.springbootdemo.TODO.exception.IncorrectTaskDetailsException;
import learnspring.springbootdemo.TODO.exception.TaskNotFoundException;
import learnspring.springbootdemo.TODO.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    List<Task> tasks= new ArrayList<>();
    private int newTaskId = 1;
    private  Validator validator=new Validator();

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
    public Task createTask(String name, String desc) throws IncorrectTaskDetailsException {
        if(!validator.validateTaskName(name)){
            throw new IncorrectTaskDetailsException("Length of task name can be 50 only");
        }
        if(!validator.validateTaskDescription(desc)){
            throw new  IncorrectTaskDetailsException("Length of task description can be 200 only");
        }
        Task task= new Task(newTaskId++,name,desc,"created");
        tasks.add(task);
        return  task;
    }
    public Task updateTask(Integer id, String status) throws TaskNotFoundException {
        try {
            Task task=getTaskById(id);
            task.setStatus(status);
            return  task;
        } catch (TaskNotFoundException e) {
            throw e;
        }
    }
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
