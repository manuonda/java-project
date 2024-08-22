package com.lambda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lambda.dto.Course;

@Service
public class CourseService {

    private final List<Course> courses = new ArrayList<>();


    public void addCourse(Course course){
        courses.add(course);
    }

    public List<Course> getAll(){
        return courses;
    }

    public Optional<Course> getCourseById(int id){
        return courses.stream().filter(x -> x.getId() == id)
        .findFirst();
    }

    public boolean updateCourse(int id , Course course){
        return getCourseById(id).map(existingCourse -> {
             courses.remove(existingCourse);
             courses.add(course);
             return true;
        }).orElse(false);
    }

      // Delete a course by id
      public boolean deleteCourse(int id) {
        return courses
                .removeIf(course -> course.getId() == id);
    }


    
}
