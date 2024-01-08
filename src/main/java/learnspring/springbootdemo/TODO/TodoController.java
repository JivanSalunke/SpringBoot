package learnspring.springbootdemo.TODO;

import learnspring.springbootdemo.TODO.dto.TaskDTO;
import learnspring.springbootdemo.TODO.exception.IncorrectTaskDetailsException;
import learnspring.springbootdemo.TODO.exception.TaskNotFoundException;
import learnspring.springbootdemo.TODO.model.Task;
import learnspring.springbootdemo.TODO.model.TaskBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDTO>> getTasks(@RequestParam(required = false) String sort){
        List<Task> tasks= todoService.getAllTasks();
        return ResponseEntity.ok(TaskBuilder.convertTaskListToDTOList(tasks));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") Integer id) throws TaskNotFoundException {
        Task task=todoService.getTaskById(id);
        return ResponseEntity.ok(TaskBuilder.convertTaskToDTO(task));
    }
    @PostMapping("/addTask")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO task) throws IncorrectTaskDetailsException {
        Task t= todoService.createTask(task.getTaskName(),task.getTaskDescription());
        return ResponseEntity.ok(TaskBuilder.convertTaskToDTO(t));
    }
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable("id") Integer id) throws TaskNotFoundException {
        Boolean isTaskDeleted= todoService.deleteTask((id));
        return ResponseEntity.ok(isTaskDeleted);
    }
    @PatchMapping("/updateTask")
    public ResponseEntity<TaskDTO> updateTask(Task task) throws TaskNotFoundException {
        Task t=todoService.updateTask(task.getId(),task.getStatus());
        return ResponseEntity.ok(TaskBuilder.convertTaskToDTO(t));
    }
    @ExceptionHandler(TaskNotFoundException.class)
    ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e){
        return  ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IncorrectTaskDetailsException.class)
    ResponseEntity<String> handleIncorrectTaskDetailsException(IncorrectTaskDetailsException e){
        return  ResponseEntity.badRequest().body(e.getMessage());
    }

}
