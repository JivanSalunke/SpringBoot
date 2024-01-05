package learnspring.springbootdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    List<Task> tasks= new ArrayList<>();
    private int newTaskId =0;

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

}
