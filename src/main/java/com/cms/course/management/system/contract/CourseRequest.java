package com.cms.course.management.system.contract;

import lombok.Data;

@Data
public class CourseRequest {

    private String firstName;
    private String lastName;
    private Long id;

    public CourseRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public CourseRequest() {

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setId(Long id) {
        this.id = id;
    }



}
