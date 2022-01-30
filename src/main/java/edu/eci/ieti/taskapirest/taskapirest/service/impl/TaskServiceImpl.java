package edu.eci.ieti.taskapirest.taskapirest.service.impl;

import edu.eci.ieti.taskapirest.taskapirest.data.Task;
import edu.eci.ieti.taskapirest.taskapirest.service.TaskService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{

    private final HashMap<String, Task> hashTask;
    private static final AtomicInteger counter = new AtomicInteger(1);

    public TaskServiceImpl(HashMap<String, Task> tasksHash) {
        this.hashTask = new HashMap<>();
    }

    @Override
    public Task create(Task task) {
        for (Task t: hashTask.values()) {
            if(t.getName()==task.getName() && t.getDescription()==task.getDescription() && t.getAssignedTo()==task.getAssignedTo() && t.getDueDate()==task.getDueDate() && t.getCreated()==task.getCreated()){
                throw new RuntimeException("Create task error.");
            }else{
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                task.setId(String.valueOf(counter.getAndIncrement()));
                task.setCreated(format.format(new Date()));
                hashTask.put(task.getId(), task);
            }
        }

        return task;
    }

    @Override
    public Task findById(String id) {
        return hashTask.get(id);
    }

    @Override
    public List<Task> all() {
        return new ArrayList<>(hashTask.values());
    }

    @Override
    public void deleteById(String id) {
        hashTask.remove(id);
    }

    @Override
    public Task update(Task task, String id) {
        if (hashTask.containsKey(id))
        {
            task.setId(id);
            task.setCreated(hashTask.get(id).getCreated());
            hashTask.replace(id, task);
            return task;
        }else
        {
            return null;
        }
    }

}