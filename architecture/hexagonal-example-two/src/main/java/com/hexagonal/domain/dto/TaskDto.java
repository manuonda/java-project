package com.hexagonal.domain.dto;

import java.time.LocalDateTime;


public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private boolean completed;


    TaskDto(){}
    

}
