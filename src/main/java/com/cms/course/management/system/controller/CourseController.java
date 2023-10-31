package com.cms.course.management.system.controller;

import com.cms.course.management.system.contract.CourseRequest;
import com.cms.course.management.system.model.Courses;
import com.cms.course.management.system.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public  CourseController(CourseService courseService){
        this.courseService =courseService;
    }

    @GetMapping
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCourseById(@PathVariable Long id) {
        Courses course = courseService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Courses> addCourse(@RequestBody Courses course) {
        Courses newCourse = courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }


    @PutMapping("/{id}")
    public @ResponseBody Boolean updateProduct(@PathVariable long id,@RequestBody CourseRequest courseRequest){
        return courseService.updateCourse(id,courseRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}

