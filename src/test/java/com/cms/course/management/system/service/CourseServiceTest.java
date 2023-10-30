package com.cms.course.management.system.service;

import com.cms.course.management.system.contract.CourseRequest;
import com.cms.course.management.system.model.Courses;
import com.cms.course.management.system.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CourseServiceTest {
 private CourseService courseService;
  private CourseRepository courseRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        courseRepository = Mockito.mock(courseRepository.class);
        courseRepository = new courseService(courseRepository);
    }

    @Test
    void testGetAllCourses(){
        Courses courses = new Courses();
        courses.setId(1L);
        courses.setFirstName("Maths");
        courses.setLastName("Engineering");
        List<Courses> coursesList = new ArrayList<Courses>();
        coursesList.add(courses);
        when(courseRepository.findAll()).thenReturn(coursesList);
        List<Courses> items = courseService.getAllCourses();
        assertEquals(items.get(0).getId(),1L);
    }

    @Test
    void testGetCourseById(){
        Courses courses = new Courses();
        courses.setId(1L);
        courses.setFirstName("Maths");
        courses.setLastName("Engineering");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(courses));
        Courses courses1 = courseService.getCourseById(1L);
        assertEquals(courses1.getId(),1L);
    }


    @Test
    void testAddCourse(){
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setId(1L);
        courseRequest.setFirstName("Maths");
        courseRequest.setLastName("Engineering");
        Courses courses = new Courses();
        courses.setId(1L);
        courses.setFirstName("Maths");
        courses.setLastName("Engineering");
        when(courseRepository.save(courses)).thenReturn(courses);
        Courses id = courseService.addCourse(courses);
        assertEquals(id,1L);
    }




    @Test
    void testDeleteProduct(){
        Courses courses = new Courses();
        courses.setId(1L);
        courses.setFirstName("Maths");
        courses.setLastName("Engineering");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(courses));
        Boolean item = courseService.deleteCourse(1L);
        Mockito.verify(courseRepository).deleteById(1L);
    }
}
