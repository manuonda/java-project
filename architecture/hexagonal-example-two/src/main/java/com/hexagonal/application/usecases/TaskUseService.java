package com.hexagonal.application.usecases;

import java.util.List;

import com.hexagonal.domain.dto.TaskDto;
import com.hexagonal.domain.dto.request.TaskRequest;

public interface TaskUseService {
    TaskDto createNew(TaskRequest request);
    TaskDto getById(Long id);
    List<TaskDto> getAll();
    void deleteById(Long id);
    TaskDto update(TaskRequest request, Long id);
}
