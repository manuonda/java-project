package com.hexagonal.domain.port;

import java.util.List;

import com.hexagonal.domain.model.Task;

public interface TaskPersistencePort {

    Task create(Task request);
    Task getById(Long id);
    List<Task> getAll();
    void deleteById(Long id);
    Task update(Task request);
    List<Task> getByIds(List<Long> tasksIds);

}
