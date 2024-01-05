package learnspring.springbootdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    List<Task> tasks= new ArrayList<>();
    private int newTaskId = 1;

    @GetMapping("")
    public List<Task> getTasks(@RequestParam(required = false) String sort){
        if( sort!=null && sort.equals("asc")){
            Collections.sort(tasks,(a,b)->a.getTaskId()-b.getTaskId());
        }else if(sort!=null && sort.equals("desc")){
            Collections.sort(tasks,(a,b)->b.getTaskId()-a.getTaskId());
        }
        return tasks;
    }

    @GetMapping("{id}")
    public Task getTaskById(@PathVariable("id") Integer id){
        return tasks.stream().filter(t->t.getTaskId().equals(id)).findFirst().orElse(null);
    }
    @GetMapping("/task")
    public Task getTaskByParamsId(@RequestParam Integer id) {
        return tasks.stream().filter(t->t.getTaskId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task){
        task.setTaskId(newTaskId++);
        tasks.add(task);
        return task;
    }

    @PutMapping("/updateStatus")
    public Task updateTaskStatus(@RequestBody Task task){
        Task updatedTask=tasks.stream().filter(t->t.getTaskId().equals(task.getTaskId())).findFirst().orElse(null);
        updatedTask.setTaskStatus(task.getTaskStatus());
        return updatedTask;
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
