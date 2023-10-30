package com.cms.course.management.system.controller;
import com.cms.course.management.system.contract.CourseRequest;
import com.cms.course.management.system.model.Courses;
import com.cms.course.management.system.service.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;




    @Test
    public void testAddCourse() {
        Courses course = new Courses("Course Name", "Course Lastname");
        assertNull(course.getId());
        course.setId(1L);
        assertNotNull(course.getId());
        assertEquals("Course Name", course.getFirstName());
        assertEquals("Course Lastname", course.getLastName());
    }

    @Test
    public void testGetCourseById() throws Exception {

        Courses courses = new Courses();
        courses.setId(1L);
        courses.setFirstName("Maths");
        courses.setLastName("Engineering");
        when(courseService.getCourseById(courses.getId())).thenReturn(courses);
        mockMvc.perform(get("/courses/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetAllCourses() throws Exception{
        Courses courses = new Courses();
        courses.setId(1L);
        courses.setFirstName("Maths");
        courses.setLastName("Engineering");
        List<Courses> coursesList=new ArrayList<>();
        coursesList.add(courses);
        when(courseService.getAllCourses()).thenReturn(coursesList);
        mockMvc.perform(get("/courses")).andExpect(status().isOk());


    }

    @Test
    public void testDeleteCourse() throws Exception {

        doNothing().when(courseService).deleteCourse(1L);
        mockMvc.perform(delete("/courses/1"))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testUpdateCourse() throws Exception{
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setId(1L);
        courseRequest.setFirstName("Maths");
        courseRequest.setLastName("Engineering");
        when(courseService.updateCourse(1L, courseRequest)).thenReturn(true);
        mockMvc.perform(put("/products/1"))
                .andExpect(status().isOk());
    }




}
