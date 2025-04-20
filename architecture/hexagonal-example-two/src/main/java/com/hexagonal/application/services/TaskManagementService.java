package com.hexagonal.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexagonal.application.usecases.TaskUseService;
import com.hexagonal.domain.dto.TaskDto;
import com.hexagonal.domain.dto.request.TaskRequest;    
import com.hexagonal.domain.port.TaskPersistencePort;

@Service
public class TaskManagementService implements TaskUseService{

    private final TaskPersistencePort taskPersistencePort;

    public TaskManagementService( t)

    @Override
    public TaskDto createNew(TaskRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createNew'");
    }

    @Override
    public TaskDto getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<TaskDto> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public TaskDto update(TaskRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
