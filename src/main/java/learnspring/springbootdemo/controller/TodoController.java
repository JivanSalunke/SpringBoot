package learnspring.springbootdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    List<Task> tasks= new ArrayList<>();
    private int newTaskId = 1;

    @GetMapping("")
    public List<Task> getTasks(){
        return tasks;
    }

    @GetMapping("{id}")
    public Task getTaskById(@PathVariable("id") Integer id){
        return tasks.stream().filter(t->t.getTaskId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task){
        task.setTaskId(newTaskId++);
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") Integer id) throws Exception {
        int taskIndex=-1;
        for( int i=0;i<tasks.size();i++){
            if(tasks.get(i).getTaskId().equals((id))){
                taskIndex=i;
                break;
            }
        }
        if(taskIndex==-1){
            throw new Exception("Task not found");
        }
        tasks.remove(taskIndex);
        return "Task Deleted Successfully";
    }

}
