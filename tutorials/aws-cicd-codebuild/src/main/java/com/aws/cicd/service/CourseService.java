package com.aws.cicd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aws.cicd.dto.Course;

@Service
public class CourseService {

    //RDS DB
    private final List<Course> courses = new ArrayList<>();

    //Create a new course 
    public void addCourse(Course course){
        this.courses.add(course);
    }

    //Retrieve all courses 
    public List<Course> getAll(){
        return courses;
    }

    //Retrive course by id
    public Optional<Course> findById(int id){
        return this.courses.stream().filter(x -> x.id() == id )
        .findFirst();
    }

    //update a course 
    public boolean updateCourse(int id, Course newCourse){
        return this.findById(id)
        .map(existingCourse -> {
           courses.remove(existingCourse);
           courses.add(newCourse);
           return true; 
        }).orElse(false);
    }


    public boolean deleteCourse(int id){
        return this.courses.removeIf( course -> course.id() == id);
    }


    
}
