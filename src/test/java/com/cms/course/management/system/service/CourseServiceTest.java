package com.cms.course.management.system.service;

import com.cms.course.management.system.contract.CourseRequest;
import com.cms.course.management.system.model.Courses;
import com.cms.course.management.system.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseServiceTest {
 private   CourseService courseService;
 private CourseRepository courseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        courseRepository=Mockito.mock(CourseRepository.class);
        courseService = new CourseService(courseRepository);
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
        assertEquals(id.getId(),1L);
    }




    @Test
    void testDeleteCourse(){
        Courses courses = new Courses();
        courses.setId(1L);
        courses.setFirstName("Maths");
        courses.setLastName("Engineering");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(courses));
        Boolean item = courseService.deleteCourse(1L);
        Mockito.verify(courseRepository).deleteById(1L);
    }

    @Test
    void testUpdateCourse(){
        Long id=1L;
        CourseRequest courseRequest=new CourseRequest();
        courseRequest.setFirstName("Maths");
        Courses courses=new Courses();
        courses.setFirstName("Maths");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(courses));
        when(courseRepository.save(courses)).thenReturn(courses);
        Boolean item = courseService.updateCourse(1L,courseRequest);
        Mockito.verify(courseRepository).save(courses);




    }

}
