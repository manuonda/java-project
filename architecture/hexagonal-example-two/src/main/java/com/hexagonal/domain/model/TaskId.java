package com.hexagonal.domain.model;

import org.springframework.util.Assert;

public record TaskId(Long id)  {

    public TaskId{
        Assert.notNull(id, "Id must be not null");
    }

}
