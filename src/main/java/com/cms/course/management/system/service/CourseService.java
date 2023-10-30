package com.cms.course.management.system.service;

import com.cms.course.management.system.contract.CourseRequest;
import com.cms.course.management.system.model.Courses;
import com.cms.course.management.system.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private  final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }


    public Courses getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Courses addCourse(Courses course) {
        return courseRepository.save(course);
    }


    public boolean updateCourse(long id, CourseRequest courseRequest) {
        Optional<Courses> courses = courseRepository.findById(id);

        if (!courses.isPresent()) {
            return false;
        }
        else {
            Courses course = courses.get();
            course.setFirstName(courseRequest.getFirstName());
            course.setLastName(courseRequest.getLastName());
            courseRepository.save(course);
            return true;
        }
    }

    public Boolean deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return null;
    }
}
