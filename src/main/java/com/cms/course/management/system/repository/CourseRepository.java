package com.cms.course.management.system.repository;

import com.cms.course.management.system.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

    List<Courses> getAllCourses();
}
