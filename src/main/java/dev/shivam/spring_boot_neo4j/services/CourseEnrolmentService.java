package dev.shivam.spring_boot_neo4j.services;

import dev.shivam.spring_boot_neo4j.models.Course;
import dev.shivam.spring_boot_neo4j.queryresults.CourseEnrolmentQueryResult;
import dev.shivam.spring_boot_neo4j.repositories.CourseRepository;
import dev.shivam.spring_boot_neo4j.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEnrolmentService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseEnrolmentService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

     public Boolean getEnrolmentStatus(String username, String identifier){
        return userRepository.findEnrolmentStatus(username, identifier);
     }

     public CourseEnrolmentQueryResult enrollIn(String username, String identifier){
       // TODO: make sure that the user has not been enrolled in the course already.
        return userRepository.createEnrolmentRelationship(username, identifier);
     }

     public List<Course> getAllEnrolledCoursesByUsername(String username){
        return courseRepository.findAllEnrolledCoursesByUsername(username);
     }

}
