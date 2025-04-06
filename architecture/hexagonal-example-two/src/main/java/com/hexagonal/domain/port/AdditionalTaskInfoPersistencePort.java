package com.hexagonal.domain.port;

import java.util.List;

import com.hexagonal.domain.model.AdditionalTaskInfo;

public interface AdditionalTaskInfoPersistencePort {

    AdditionalTaskInfo create(AdditionalTaskInfo entity);
    AdditionalTaskInfo getById(Long id);
    List<AdditionalTaskInfo> getAll();
    void deleteById(Long id);
    AdditionalTaskInfo update( AdditionalTaskInfo request);
    List<AdditionalTaskInfo> getByIds(List<Long> ids); 
    
}
