package edu.eci.ieti.taskapirest.taskapirest.controller;
import edu.eci.ieti.taskapirest.taskapirest.data.Task;
import edu.eci.ieti.taskapirest.taskapirest.dto.TaskDto;
import edu.eci.ieti.taskapirest.taskapirest.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping( "/v1/task" )
public class TaskController {
    @Autowired
    private final TaskService taskService;

    public TaskController( TaskService taskService )
    {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> all()
    {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.all());
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Task> findById( @PathVariable String id )
    {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Task> create( @RequestBody TaskDto taskDTO )
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.create(new Task(taskDTO)));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Task> update( @RequestBody TaskDto taskDTO, @PathVariable String id )
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.update(new Task(taskDTO), id));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id )
    {
        try
        {
            taskService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}