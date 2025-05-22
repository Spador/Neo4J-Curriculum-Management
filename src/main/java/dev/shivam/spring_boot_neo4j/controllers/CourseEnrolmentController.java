package dev.shivam.spring_boot_neo4j.controllers;

import dev.shivam.spring_boot_neo4j.models.Course;
import dev.shivam.spring_boot_neo4j.object.CourseDTO;
import dev.shivam.spring_boot_neo4j.object.CourseEnrolmentDTO;
import dev.shivam.spring_boot_neo4j.queryresults.CourseEnrolmentQueryResult;
import dev.shivam.spring_boot_neo4j.requests.CourseEnrolmentRequest;
import dev.shivam.spring_boot_neo4j.services.CourseEnrolmentService;
import dev.shivam.spring_boot_neo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enrolments")
public class CourseEnrolmentController {

    private final CourseEnrolmentService courseEnrolmentService;

    private final LessonService lessonService;

    public CourseEnrolmentController(CourseEnrolmentService courseEnrolmentService, LessonService lessonService) {
        this.courseEnrolmentService = courseEnrolmentService;
        this.lessonService = lessonService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> enrolments(Principal principal) {
        List<Course> courses = courseEnrolmentService.getAllEnrolledCoursesByUsername(principal.getName());

        List<CourseDTO>  responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO responseCourse = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
                    responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
                    responseCourse.setEnrolled(true);
                    return responseCourse;
                }
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseEnrolmentDTO> enrollIn(@RequestBody CourseEnrolmentRequest request, Principal principal) {
        CourseEnrolmentQueryResult enrolmentQueryResult = courseEnrolmentService.enrollIn(principal.getName(), request.getCourseIdentifier());

        CourseEnrolmentDTO responseEnrolment = new CourseEnrolmentDTO(
                enrolmentQueryResult.getUser().getUsername(),
                enrolmentQueryResult.getUser().getName(),
                enrolmentQueryResult.getCourse()
                );

        return new ResponseEntity<>(responseEnrolment, HttpStatus.OK);
    }
}
