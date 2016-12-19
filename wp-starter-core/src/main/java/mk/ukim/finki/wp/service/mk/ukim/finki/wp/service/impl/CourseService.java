package mk.ukim.finki.wp.service.mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.persistence.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bojan on 12/19/2016.
 */
@Service
public class CourseService implements ICourseRepository {

    @Autowired
    private ICourseRepository courseRepository;

    public Course save(Course course) {
        courseRepository.save(course);
        return course;
    }

    public List<Course> findAll() {
        System.out.println("FIIIIIND AAAAAL 2");
        return courseRepository.findAll();
    }

    public void update(Integer id, Course course) {
        courseRepository.update(id, course);
    }

    public void delete(Integer id) {
        courseRepository.delete(id);
    }
}
